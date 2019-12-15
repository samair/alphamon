package com.webvidhi.pubsub.config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.webvidhi.pubsub.modal.JwtResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class JwtUtil {
	
	public static final long JWT_TOKEN_VALIDITY = 5*60;
	
	//@Value("${jwt.secret}")
	private String secret ="javainuse";

	
	
	public JwtResponse generateToken(String userName) {
		Map<String,Object> claims = new HashMap<String, Object>();
		String token = Jwts.builder().setClaims(claims).setSubject(userName).
				setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY*1000)).signWith(SignatureAlgorithm.HS256, secret).compact();
				
		return new JwtResponse(token);
				
	}
	
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
