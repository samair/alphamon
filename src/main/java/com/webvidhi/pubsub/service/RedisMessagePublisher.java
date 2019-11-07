package com.webvidhi.pubsub.service;

import javax.imageio.spi.RegisterableService;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webvidhi.pubsub.modal.DeviceMsg;
import com.webvidhi.pubsub.modal.PerformanceMetrics;


@Component
public class RedisMessagePublisher implements MessagePublisher {

	
		private RedisTemplate<String, Object> redisTemplate;
		
		private ChannelTopic topic;
	
	    public RedisMessagePublisher(
	      RedisTemplate<String, Object> redisTemplate, ChannelTopic topic) {
	      this.redisTemplate = redisTemplate;
	      this.topic = topic;
	    }
	 
	    public void publish(PerformanceMetrics metrics) throws JsonProcessingException {
	    	ObjectMapper mapper = new ObjectMapper();
	    	String metricString = mapper.writeValueAsString(metrics);
	    	System.out.println("Publishing "+ metricString);
	        redisTemplate.convertAndSend(topic.getTopic(), metricString);
	    }

		public void publishAddDevice(DeviceMsg msg) throws JsonProcessingException {
			// TODO Auto-generated method stub
			msg.setNewDevice(true);
			ObjectMapper mapper = new ObjectMapper();
	    	String addMessage = mapper.writeValueAsString(msg);
	    	System.out.println("Publishing Add device"+ addMessage);
	        redisTemplate.convertAndSend("devices", addMessage);
			
		}
}
