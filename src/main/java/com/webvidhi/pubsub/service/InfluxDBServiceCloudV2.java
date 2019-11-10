package com.webvidhi.pubsub.service;

import java.time.Instant;
import java.util.List;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApi;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.query.FluxTable;
import com.webvidhi.pubsub.modal.Cpu;
import com.webvidhi.pubsub.modal.Memory;
import com.webvidhi.pubsub.modal.PerformanceMetrics;

public class InfluxDBServiceCloudV2 {

	private InfluxDBClient client;
	
	  private String influxDbOrg  =null;
	 private void connect() { 
		 influxDbOrg= System.getenv("INFLUXDB-ORG");
		    
		     this.client = InfluxDBClientFactory.create("https://us-west-2-1.aws.cloud2.influxdata.com",
		    		 System.getenv("INFLUXDB-TOKEN").toCharArray());  
		  }
	 
	 public void write(PerformanceMetrics metric) {
		 connect();
		 String host = metric.getOsName();
		 Memory mem = new Memory();
		 mem.host = host;
		 mem.used_percent = (double) metric.getMemUsage();
		 mem.time = Instant.now();
		 
		 Cpu cpu = new Cpu();
		 cpu.host = host;
		 cpu.used_percent = (double) metric.getCpuUsage();
		 
		
		 try (WriteApi writeApi = client.getWriteApi()) { 
		   System.out.println("Writing");
		   writeApi.writeMeasurement("Perf", influxDbOrg, WritePrecision.MS, mem);
		   writeApi.writeMeasurement("Perf", influxDbOrg, WritePrecision.MS, cpu);
		 }
		 catch(Exception e) {
			 System.out.println(e.getMessage());
		 }
	 }
	 
	 public void read() {
		 connect();
		 String query = "from(bucket: \"Perf\") |> range(start: -1h)";
		 
		 List<FluxTable> tables = client.getQueryApi().query(query, "tsameerc@gmail.com");
		 
		 System.out.println("Readding");
		 for (FluxTable t : tables) {
			 System.out.println(t.toString());
		 }
	 }
	 
	public static void main(String[] args) {
		
		InfluxDBServiceCloudV2 instance = new InfluxDBServiceCloudV2();
		
		//instance.write();
		instance.read();
		
	}
}
