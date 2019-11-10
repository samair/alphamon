package com.webvidhi.pubsub.modal;

import java.time.Instant;

import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;

@Measurement(name = "cpu")
public class Cpu {  
  @Column(tag = true)
public String host;  
  @Column  
  public Double used_percent;  
  @Column(timestamp = true)  
  public Instant time;
}
