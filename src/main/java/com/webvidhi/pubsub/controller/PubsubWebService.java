package com.webvidhi.pubsub.controller;

import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.webvidhi.pubsub.modal.APIKey;
import com.webvidhi.pubsub.modal.Device;
import com.webvidhi.pubsub.modal.DeviceMsg;
import com.webvidhi.pubsub.modal.MetricSubscriber;
import com.webvidhi.pubsub.modal.PerformanceMetrics;
import com.webvidhi.pubsub.modal.User;
import com.webvidhi.pubsub.service.DeviceService;
import com.webvidhi.pubsub.service.InfluxDBServiceCloudV2;
//import com.webvidhi.pubsub.service.InfluxDbService;
import com.webvidhi.pubsub.service.RedisMessagePublisher;
import com.webvidhi.pubsub.service.UserService;

@RestController
@CrossOrigin

public class PubsubWebService {
	
	@Autowired
	MetricSubscriber subscribers;
	
	@Autowired 
	InfluxDBServiceCloudV2 influxService;
	
	@Autowired
	RedisMessagePublisher publisher;
	
	@Autowired
	UserService userService;
	
	@Autowired
	DeviceService deviceService;
	
	@PostMapping("/test")
	public String echo(@RequestBody PerformanceMetrics metrics) throws JsonProcessingException {
		System.out.println(metrics.getMemUsage());
		System.out.println(metrics.getDeviceId());
	
	    //influxService.write(metrics);
		publisher.publish(metrics);
		return "echo";
	}
	
	@PostMapping("/subscribe")
	public void subscribe(@RequestParam String deviceId) {
		
		//System.out.println("Adding device:"+deviceId);
		//subscribers.addSubscriber(deviceId);
	}
	
	@PostMapping("/register")
	public void register(@RequestHeader("Key") String apiKey, @RequestBody DeviceMsg regMsg) throws JsonProcessingException {
		System.out.println("Request to register : "+regMsg.getDeviceID()+ "Key: "+apiKey);
		
		//Check if it is related to a valid user
		User user= userService.getUserByKey(apiKey);
		if (null != user ) {
			//It is a valid key, now check if it used by any other device
			for (APIKey key :user.getKeyStore()) {
				if (null !=key.getDeviceId() && key.getKeyID().contentEquals(apiKey) && key.getDeviceId().contentEquals(regMsg.getDeviceID())) {
					//ok may be re regitering
				}
				else if (null !=key.getDeviceId() && key.getKeyID().contentEquals(apiKey) && !key.getDeviceId().contentEquals(regMsg.getDeviceID())){
					
					//This is wrong, it
					System.out.println("Cant register, you are already using this key..");
				}
				else
				{
					//
					if (key.getKeyID().contentEquals(apiKey)) {
						key.setDeviceId(regMsg.getDeviceID());
						int index =user.getKeyStore().indexOf(key);
						user.getKeyStore().set(index, key);
						userService.createOrUpdate(user);
						
					}
					
					
				}
			}
		}
		//publisher.publishAddDevice(regMsg);
	}
	
	@GetMapping("/apikey")
	public String generateAPIKey() {
		
		 String generatedString = RandomStringUtils.randomAlphanumeric(30);
		
		return generatedString;
	}
	
	
}
