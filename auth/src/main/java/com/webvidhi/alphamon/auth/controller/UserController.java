package com.webvidhi.alphamon.auth.controller;



import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webvidhi.alphamon.auth.modal.APIKey;
import com.webvidhi.alphamon.auth.modal.JwtResponse;
import com.webvidhi.alphamon.auth.modal.User;
import com.webvidhi.alphamon.auth.service.UserService;

import io.jsonwebtoken.Claims;



@RestController
@CrossOrigin(origins = "*")


public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/")
	public User getUser(@RequestParam String userId) {
		return userService.getUser(userId);
		 
	}
	@PostMapping("/")
	public boolean createUser(@RequestBody User user) {
		userService.createOrUpdate(user);
		return true;
	}
	
	
	@GetMapping("/key")
	public String generateAPIKey() {
		
		 String generatedString = RandomStringUtils.randomAlphanumeric(30);
		
		return generatedString;
	}
	
	@PostMapping("/apiKey")
	public List<APIKey> saveKey(@RequestBody Map<String, String> keyInfo) { 
		String userID = SecurityContextHolder.getContext().getAuthentication().getName();
		return userService.addKeys(userID, keyInfo.get("keyID"), keyInfo.get("description"));
		 
		
	}
	
	@GetMapping("/keys")
	public List<APIKey> getKeys() { 
		String userID = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println("User ID:"+userID);
		return userService.getAllKeys(userID);
		
		
	}
	@DeleteMapping("/apiKey")
	public List<APIKey> deleteKey(@RequestParam String keyID) {
		String userID = SecurityContextHolder.getContext().getAuthentication().getName();
		return userService.removeKey(userID,keyID);
	}
	
	@PostMapping("/validate")
	public JwtResponse validate(@RequestBody Map<String,String> userInfo) {
		return userService.validate(userInfo.get("user"), userInfo.get("pass"));
	}
	
	@GetMapping("/key/{key}")
	public User getUserBykey(@PathVariable String key) {
		return userService.getUserByKey(key);
		 
	}
	/*@GetMapping("/devices")
	public List<Device> getDevices(@RequestParam String userId) {
		return userService.getAllDevices(userId);
		 
	}*/
	
	
	@GetMapping("/testJwt")
	public Claims getClaims (@RequestParam String token) {
		return userService.getClaims(token);
	}
	
	@PostMapping("/refresh_token")
	public JwtResponse refreshToken (@RequestParam String token) {
		return userService.getRefreshedToken(token);
	}
	
}
