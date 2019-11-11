package com.webvidhi.pubsub.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.webvidhi.pubsub.modal.DeviceMsg;
import com.webvidhi.pubsub.modal.MetricSubscriber;
import com.webvidhi.pubsub.modal.PerformanceMetrics;
import com.webvidhi.pubsub.service.InfluxDBServiceCloudV2;
//import com.webvidhi.pubsub.service.InfluxDbService;
import com.webvidhi.pubsub.service.RedisMessagePublisher;

@RestController
@CrossOrigin

public class PubsubWebService {
	
	@Autowired
	MetricSubscriber subscribers;
	
	@Autowired 
	InfluxDBServiceCloudV2 influxService;
	
	@Autowired
	RedisMessagePublisher publisher;
	
	
	
	@PostMapping("/test")
	public String echo(@RequestBody PerformanceMetrics metrics) throws JsonProcessingException {
		System.out.println(metrics.getMemUsage());
	
	    influxService.write(metrics);
		publisher.publish(metrics);
		return "echo";
	}
	
	@PostMapping("/subscribe")
	public void subscribe(@RequestParam String deviceId) {
		
		//System.out.println("Adding device:"+deviceId);
		//subscribers.addSubscriber(deviceId);
	}
	
	@PostMapping("/register")
	public void register(@RequestBody DeviceMsg regMsg) throws JsonProcessingException {
		System.out.println("Request to register : "+regMsg.getName());
		publisher.publishAddDevice(regMsg);
	}
	
	
}
