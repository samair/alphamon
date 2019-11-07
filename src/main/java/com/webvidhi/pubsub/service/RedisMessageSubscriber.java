package com.webvidhi.pubsub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.webvidhi.pubsub.modal.DeviceMsg;
import com.webvidhi.pubsub.modal.MetricSubscriber;

@Service

public class RedisMessageSubscriber implements MessageListener{


	@Autowired
	private SimpMessagingTemplate broker;

	@Autowired
	private MetricSubscriber subscriber;
	

	
	@Override
	public void onMessage(Message message, byte[] pattern) {
		// TODO Auto-generated method stub
		System.out.println("Recieved Message!!"+message);
		sendEvnets(message);
	
		
	}
	public void sendEvnets(Message message) {
		
		  for (String deviceId : subscriber.getSubscribersList()) {
			  
			  //Parse the message and send to appropriate client
		  
		   broker.convertAndSend("/topic/"+deviceId, message.toString());
		   }
		 
	}
	
	public void sendDeviceMessages(DeviceMsg msg) {
		
		String topicName = "/topic/devices";
		System.out.println("Sending to : " + topicName);
		broker.convertAndSend(topicName,msg );
	}
}
