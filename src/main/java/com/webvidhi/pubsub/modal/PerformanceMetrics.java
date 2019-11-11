package com.webvidhi.pubsub.modal;

public class PerformanceMetrics {

	private String deviceId;

	private String osName;

	private String version;

	private float cpuUsage;

	private float memUsage;

	private float diskUsage;

	private float swapUsage;

	private float cpuTempVal;

	private float wifiUsage;
	
	private long timestamp;

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getOsName() {
		return osName;
	}

	public void setOsName(String osName) {
		this.osName = osName;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public float getCpuUsage() {
		return cpuUsage;
	}

	public void setCpuUsage(float cpuUsage) {
		this.cpuUsage = cpuUsage;
	}

	public float getMemUsage() {
		return memUsage;
	}

	public void setMemUsage(float memUsage) {
		this.memUsage = memUsage;
	}

	public float getDiskUsage() {
		return diskUsage;
	}

	public void setDiskUsage(float diskUsage) {
		this.diskUsage = diskUsage;
	}

	public float getSwapUsage() {
		return swapUsage;
	}

	public void setSwapUsage(float swapUsage) {
		this.swapUsage = swapUsage;
	}

	public float getCpuTempVal() {
		return cpuTempVal;
	}

	public void setCpuTempVal(float cpuTempVal) {
		this.cpuTempVal = cpuTempVal;
	}

	public float getWifiUsage() {
		return wifiUsage;
	}

	public void setWifiUsage(float wifiUsage) {
		this.wifiUsage = wifiUsage;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

}
