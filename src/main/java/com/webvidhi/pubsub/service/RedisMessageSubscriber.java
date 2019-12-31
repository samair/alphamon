package com.webvidhi.pubsub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webvidhi.pubsub.modal.DeviceMsg;
import com.webvidhi.pubsub.modal.MetricSubscriber;
import com.webvidhi.pubsub.modal.PerformanceMetrics;

@Service

public class RedisMessageSubscriber implements MessageListener {

	@Autowired
	private SimpMessagingTemplate broker;

	@Autowired
	private MetricSubscriber subscriber;

	@Override
	public void onMessage(Message message, byte[] pattern) {
		// TODO Auto-generated method stub
		System.out.println("Recieved Message!!" + message);

		if (message.getBody().toString().contentEquals("devices")) {

			sendDeviceMessages(message);
		} else {
			try {
				sendEvnets(message);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void sendEvnets(Message message) throws JsonProcessingException {

		for (String deviceId : subscriber.getSubscribersList()) {

			// Parse the message and send to appropriate client
			ObjectMapper mapper = new ObjectMapper();
			PerformanceMetrics metrics = mapper.readValue(message.toString(), PerformanceMetrics.class);
		
			if (metrics.getDeviceId().contentEquals(deviceId)) {
				System.out.println("Sending message to : /topic/"+ deviceId);
				broker.convertAndSend("/topic/" + deviceId, message.toString());
			}
		}

	}

	public void sendDeviceMessages(Message msg) {

		String topicName = "/topic/devices";
		System.out.println("Sending Device Message to  : " + topicName);
		broker.convertAndSend(topicName, msg.toString());
	}
}
