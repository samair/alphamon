package com.webvidhi.pubsub.modal;

import java.util.List;

public class SessionMessage {
	
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public List<String> getAttrs() {
		return attrs;
	}

	public void setAttrs(List<String> attrs) {
		this.attrs = attrs;
	}

	private String deviceId;
	
	private List<String> attrs;

}
