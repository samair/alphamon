package com.webvidhi.alphamon.auth.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.webvidhi.alphamon.auth.config.JwtUtil;
import com.webvidhi.alphamon.auth.modal.APIKey;
import com.webvidhi.alphamon.auth.modal.JwtResponse;
import com.webvidhi.alphamon.auth.modal.User;
import com.webvidhi.alphamon.auth.repo.UsersRepository;

import io.jsonwebtoken.Claims;




@Service
public class UserService {
	
	@Autowired
	public UsersRepository usrRepo;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	
	JwtUtil jwt = new JwtUtil();
	
	public void createOrUpdate(User user) {

		
		usrRepo.save(user);
		
	}
	
	public User getUser(String userId) {
		User user = usrRepo.findByUserId(userId);
		return user;
	}

	public JwtResponse validate(String userName, String password) {
		
		User user = usrRepo.findByUserName(userName);
		if (user!= null && user.getPassword().contentEquals(password)) {
			System.out.println("Valid!");
			
			//return user.get_id();
			return jwt.generateToken(user.get_id());
		}
		
			return null;
	}

	public User getUserByKey(String key) {
		return usrRepo.findByKey(key);
	}
	
	public List<APIKey>  addKeys(String userId,String key, String description) {
		
		User user = usrRepo.findByUserId(userId);
		APIKey apiKey = new APIKey();
		apiKey.setKeyID(key);
		apiKey.setDescription(description);

		user.addkey(apiKey);
		usrRepo.save(user);
		return user.getKeyStore();
		
	}
/*
	public List<APIKey> getAllKeys(String userID) {
	
		Query getAllKeysQ = new Query();
		getAllKeysQ.addCriteria(Criteria.where("_id").is(userID));
		getAllKeysQ.fields().include("keyStore").exclude("_id");
		return mongoTemplate.findOne(getAllKeysQ, User.class).getKeyStore();


		
	}
	*/
	public List<APIKey> getAllKeys(String userID) {
		
		return usrRepo.getAPIKeys(userID).getKeyStore();
	
	}



	public List<APIKey>  removeKey(String userID, String keyID) {
		
		User user = usrRepo.findByUserId(userID);
	
		user.removekey(keyID);
		usrRepo.save(user);
		return usrRepo.getAPIKeys(userID).getKeyStore();
		
	}

	public Claims getClaims(String token) {
		return jwt.getClaimsfromToken(token);
		//return null;
	}
	
	@Bean
  	public JwtUtil jwtUtil() {
    	   return new JwtUtil();
  	}

	public JwtResponse getRefreshedToken(String token) {
		return jwt.generateRefreshedToken(token);
	}

}
