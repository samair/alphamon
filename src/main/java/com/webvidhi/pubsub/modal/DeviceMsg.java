package com.webvidhi.pubsub.modal;

public class DeviceMsg {
	
	private String deviceID;
	
	private String name;
	
	private boolean newDevice;
	
	private boolean removeDevice;

	public String getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isNewDevice() {
		return newDevice;
	}

	public void setNewDevice(boolean newDevice) {
		this.newDevice = newDevice;
	}

	public boolean isRemoveDevice() {
		return removeDevice;
	}

	public void setRemoveDevice(boolean removeDevice) {
		this.removeDevice = removeDevice;
	}

}
