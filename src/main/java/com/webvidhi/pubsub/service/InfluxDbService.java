package com.webvidhi.pubsub.service;

/*
import java.time.Instant;
import java.util.concurrent.TimeUnit;

import org.influxdb.BatchOptions;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.springframework.stereotype.Service;

@Service

public class InfluxDbService {



	public void testWrite(String deviceId, String usage) {

		InfluxDB influxDB = InfluxDBFactory.connect("http://localhost:8086", "root", "root");
		String dbName = deviceId;
		influxDB.query(new Query("CREATE DATABASE " + dbName));
		influxDB.setDatabase(dbName);
		String rpName = "aRetentionPolicy";
		influxDB.query(new Query("CREATE RETENTION POLICY " + rpName + " ON " + dbName + " DURATION 30h REPLICATION 2 SHARD DURATION 30m DEFAULT"));
		influxDB.setRetentionPolicy(rpName);

		influxDB.enableBatch(BatchOptions.DEFAULTS);

		influxDB.write(Point.measurement("mem")
		    .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
		    .addField("used_percent", Float.parseFloat(usage))

		    .build());
		influxDB.close();
	}
	
}
*/
