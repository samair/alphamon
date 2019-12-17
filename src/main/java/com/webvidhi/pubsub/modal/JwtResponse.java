package com.webvidhi.pubsub.modal;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class JwtResponse {
	
	private String tokenType ="bearer";

	public JwtResponse(String token) {
		accessToken = token;
	}
	
	public JwtResponse(String acToken, String rfToken) {
		accessToken = acToken;
		refreshToken = rfToken;
	}

	private String accessToken;
	
	private String refreshToken;
}
