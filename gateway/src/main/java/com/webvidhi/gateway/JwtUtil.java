package com.webvidhi.gateway;

import java.util.Date;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;


public class JwtUtil {
	
	public static final long JWT_ACCESS_TOKEN_VALIDITY = 5*60;
	public static final long JWT_REFRESH_TOKEN_VALIDITY = 5*60*60*12;
	//@Value("${jwt.secret}")
	private String secret ="javainuse";

	
	

	
	public Boolean validateToken(String token) {
		
		Claims claims = getClaimsfromToken(token);
		Date expiration = claims.getExpiration();
		
	    
		return expiration.before(new Date());
		
	}
	
	public Claims getClaimsfromToken(String token)
	{
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		 
		
	}

	public String getUserName(String token) {
		Claims claims = getClaimsfromToken(token);
		System.out.println("Subject : "+claims.getSubject());
		return claims.getSubject();
	}

}
