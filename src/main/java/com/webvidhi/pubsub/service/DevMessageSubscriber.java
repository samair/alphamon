package com.webvidhi.pubsub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.webvidhi.pubsub.modal.DeviceMsg;
import com.webvidhi.pubsub.modal.MetricSubscriber;

@Service
public class DevMessageSubscriber implements MessageListener{


	@Autowired
	private SimpMessagingTemplate broker;

	@Autowired
	private MetricSubscriber subscriber;
	

	
	@Override
	public void onMessage(Message message, byte[] pattern) {
		// TODO Auto-generated method stub
		System.out.println("Recieved Message!!"+message);
		sendDeviceMessages(message);
	
		
	}

	
	public void sendDeviceMessages(Message msg) {
		
		String topicName = "/topic/devices";
		System.out.println("Sending to : " + topicName);
		broker.convertAndSend(topicName,msg.toString() );
	}
}
