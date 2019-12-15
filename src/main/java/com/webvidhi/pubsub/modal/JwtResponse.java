package com.webvidhi.pubsub.modal;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class JwtResponse {

	public JwtResponse(String token) {
		bearerToken = token;
	}

	private String bearerToken;
}
