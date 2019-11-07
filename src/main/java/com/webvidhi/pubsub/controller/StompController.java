package com.webvidhi.pubsub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.webvidhi.pubsub.modal.MetricSubscriber;
import com.webvidhi.pubsub.modal.SessionMessage;


@Controller
public class StompController {

	@Autowired
	MetricSubscriber subscriber;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public String greeting(String message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return "OK";
    }

    @MessageMapping("/stream/start")
    public void  startSubscription(@Payload SessionMessage sessionMsg , SimpMessageHeaderAccessor headerAccessor) {
    	System.out.println("Recieved payload .." + sessionMsg.getDeviceId());
    	headerAccessor.getSessionAttributes().put("sessionId", sessionMsg.getDeviceId());
    	subscriber.addSubscriber(sessionMsg.getDeviceId());
    	
    }
}