package com.mytaxi.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "driverSequences")
public class DriverSequences {
  @Id
  private String id;

  private long seq;

  public DriverSequences() {}

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public long getSeq() {
    return seq;
  }

  public void setSeq(long seq) {
    this.seq = seq;
  }
}
