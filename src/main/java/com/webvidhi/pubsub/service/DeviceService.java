package com.webvidhi.pubsub.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webvidhi.pubsub.modal.Device;
import com.webvidhi.pubsub.modal.DeviceMsg;
import com.webvidhi.pubsub.modal.User;
import com.webvidhi.pubsub.repo.UsersRepository;

@Service

public class DeviceService {
	
	@Autowired
	UsersRepository usrRepo;

	public List<Device> addDevice(String keyId, DeviceMsg msg){
		
		User user = usrRepo.findByKey(keyId);
		Device d = new Device();
		d.set_id(msg.getDeviceID());
		user.addDevice(d);
		return user.getDevices();
	}
	
}