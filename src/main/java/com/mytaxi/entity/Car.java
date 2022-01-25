package com.mytaxi.entity;

import java.time.ZonedDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection = "Car")
public class Car {
  @Id
  @Field(name = "id")
  private Long id;

  @Field( name = "date_created")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private ZonedDateTime dateCreated = ZonedDateTime.now();

  @Field(name = "engine_type")
  private String engineType;

  @Field(name = "license_plate")
  private String licensePlate;

  @Field(name = "seat_cnt")
  private Integer seatCount;
  
  @Field
  private String model;
  
  @Field
  private String manufacturer;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public ZonedDateTime getDateCreated() {
    return dateCreated;
  }

  public void setDateCreated(ZonedDateTime dateCreated) {
    this.dateCreated = dateCreated;
  }

  public String getEngineType() {
    return engineType;
  }

  public void setEngineType(String engineType) {
    this.engineType = engineType;
  }

  public String getLicensePlate() {
    return licensePlate;
  }

  public void setLicensePlate(String licensePlate) {
    this.licensePlate = licensePlate;
  }

  public Integer getSeatCount() {
    return seatCount;
  }

  public void setSeatCount(Integer seatCount) {
    this.seatCount = seatCount;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }
  
}
