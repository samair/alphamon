package com.webvidhi.pubsub.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@EnableWebSecurity
public class SecurityJwtConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable()  
		
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().cors().disable()
		.addFilterAfter(new JwtAuthFilter(), UsernamePasswordAuthenticationFilter.class)
		.authorizeRequests()
		.antMatchers("/v1/auth/validate").permitAll()
		.anyRequest().authenticated();
		
		
		
	}
	

}
