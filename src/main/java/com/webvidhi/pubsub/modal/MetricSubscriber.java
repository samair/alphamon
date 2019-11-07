package com.webvidhi.pubsub.modal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class MetricSubscriber {

	private List<String> subscriberList = new ArrayList<>();
	
	public void addSubscriber(String deviceId) {
		subscriberList.add(deviceId);
	}
	
	public void removeSubscriber(String deviceId) {
		subscriberList.remove(deviceId);
	}
	
	public List<String> getSubscribersList() {
		
		return subscriberList;
	}
}
