package com.webvidhi.pubsub.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.webvidhi.pubsub.modal.APIKey;
import com.webvidhi.pubsub.modal.User;




public interface UsersRepository extends MongoRepository<User, String>{
	
	@Query("{ '_id' : ?0 }")
	User findByUserId(String userId);
	
	@Query("{ 'userName' : ?0 }")
	User findByUserName(String userName);
	
	@Query("{ 'keyStore.keyID' : ?0 }")
	User findByKey(String key);
	
	@Query("{ '_id' : ?0 }, {'keyStore':1,'_id':0}")
	User getAPIKeys(String userID);

	@Query("{ '_id' : ?0 }, {'devices':1,'_id':0}")
	User getDevices(String userId);
	

}
