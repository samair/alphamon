package com.webvidhi.pubsub.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.webvidhi.pubsub.modal.APIKey;
import com.webvidhi.pubsub.modal.User;




public interface TokenRepository extends MongoRepository<User, String>{
	
	@Query("{ '_id' : ?0 }")
	User findByUserId(String userId);

}
