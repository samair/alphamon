package com.webvidhi.pubsub.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


import com.webvidhi.pubsub.modal.User;
import com.webvidhi.pubsub.modal.Tokens;




public interface TokenRepository extends MongoRepository<Tokens, String>{
	
	@Query("{ '_id' : ?0 }")
	User findByUserId(String userId);

}
