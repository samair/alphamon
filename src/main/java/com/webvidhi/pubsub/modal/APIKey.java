package com.webvidhi.pubsub.modal;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class APIKey {
	
	private String keyID;
	
	private String description;
	
	private String deviceId;
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        APIKey key = (APIKey)o;

        return key.getKeyID().contentEquals(keyID);
    }

    @Override
    public int hashCode() {
        return keyID.hashCode();
    }

	

}
