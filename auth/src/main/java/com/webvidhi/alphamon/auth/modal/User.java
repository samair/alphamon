package com.webvidhi.alphamon.auth.modal;



import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection="Users")
@Getter @Setter
public class User {
	
	@Id
	private String _id;
	
	private String userName;
	
	private String password;
	
	private int keyLimit;
	
	private List<APIKey> keyStore;
	
	//private List<Device> devices;

	public void addkey(APIKey key) {
		// TODO Auto-generated method stub
		keyStore.add(key);
		
	}
	public void removekey(String keyId) {
		// TODO Auto-generated method stub
		int index=0;
		for (APIKey key:keyStore) {
			if (key.getKeyID().contentEquals(keyId)) {
				index=keyStore.indexOf(key);
				
			}
		}
		keyStore.remove(index);
		
		
	}

	

}
