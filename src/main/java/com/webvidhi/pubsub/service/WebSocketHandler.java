package com.webvidhi.pubsub.service;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.webvidhi.pubsub.modal.MetricSubscriber;
@ComponentScan("com.webvidhi.pubsub")
@Service
public class WebSocketHandler {

	@Autowired
	private SimpMessagingTemplate broker;

	@Autowired
	private MetricSubscriber subscriber;

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	//@Scheduled(fixedDelay = 5000)
	public void addUpdateQuotes() {

		System.out.println("Timer hit!!");
		sendEvnets();
	}

	// @SendTo("/topic/events")
	public void sendEvnets() {

		for (String deviceId : subscriber.getSubscribersList()) {

			String topicName = "/topic/" + deviceId;
			System.out.println("Sending to : " + topicName);
			broker.convertAndSend(topicName, "44");
		}
	}
	


}