package com.webvidhi.pubsub.modal;

import java.time.Instant;

import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;

@Measurement(name = "mem")
public class Memory {  
  @Column(tag = true)
public String host;  
  @Column  
  public Double used_percent;  
  @Column(timestamp = true)  
  public Instant time;
}