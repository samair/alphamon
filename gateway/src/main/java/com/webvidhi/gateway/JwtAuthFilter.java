package com.webvidhi.gateway;

import java.io.IOException;
import java.util.List;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthFilter extends OncePerRequestFilter{
	
	@Value("${jwt.header}")
	String header;
	
	@Value("${jwt.prefix}")
	String prefix;
	
	JwtUtil jwtUtil = new JwtUtil();

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		System.out.println("Filter hit!!");
		
		//Get headers
		String authHeader = request.getHeader("Authorization");
		
		// get bearer token
	    if( authHeader != null  && authHeader.startsWith("Bearer")) {
	    	
	    	System.out.println("header: "+authHeader );
	    	try {
	    		String token = authHeader.replace("Bearer ", "");
	    		System.out.println("Token is:"+ token);
	    		String userName = jwtUtil.getUserName(token);
	    		System.out.println("userName: "+userName);
	    		
	    		if( userName != null) {
	    			System.out.println("Valid user..");

					//List<String> authorities = (List<String>) jwtUtil.getClaimsfromToken(token).get("authorities");
	    			 List<GrantedAuthority> grantedAuths =
	    		                AuthorityUtils.commaSeparatedStringToAuthorityList("ADMIN");
					
					// UsernamePasswordAuthenticationToken: A built-in object, used by spring to represent the current authenticated / being authenticated user.
					// It needs a list of authorities, which has type of GrantedAuthority interface, where SimpleGrantedAuthority is an implementation of that interface
					 UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
							 userName, null, grantedAuths);
					 
					 // 6. Authenticate the user
					 // Now, user is authenticated
					 SecurityContextHolder.getContext().setAuthentication(auth);
				}
	    		else
	    		{
System.out.println("Error!");
	    		}
	    	}
	    	catch(Exception exception){
	    		System.out.println("Error exception!"+exception.getLocalizedMessage());
	    		//SecurityContextHolder
	    		SecurityContextHolder.clearContext();
	    		
	    	}
	    }
	
        if ("OPTIONS".equals(request.getMethod())) {
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "authorization, content-type,x-auth-token, " +
                    "access-control-request-headers, access-control-request-method, accept, origin, authorization, x-requested-with");
            response.addHeader("Access-Control-Expose-Headers", "xsrf-token");
            response.setStatus(HttpServletResponse.SC_OK);
        } 
        else {
        	  response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        	  response.setHeader("Access-Control-Allow-Credentials", "true");
        	  response.setHeader("X-Frame-Options", "sameorigin");
	    filterChain.doFilter(request, response);
        }
	}

}
