package com.webvidhi.pubsub.modal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class MetricSubscriber {

	private List<String> subscriberList = Collections.synchronizedList(new  ArrayList<>());
	
	public void addSubscriber(String deviceId) {
		System.out.println("Adding subscriber-"+deviceId);
		subscriberList.add(deviceId);
	}
	
	public void removeSubscriber(String deviceId) {
		System.out.println("Removing  subscriber-"+deviceId);
		subscriberList.remove(deviceId);
	}
	
	public List<String> getSubscribersList() {
		
		return subscriberList;
	}
}
