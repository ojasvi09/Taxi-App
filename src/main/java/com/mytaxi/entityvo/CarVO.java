package com.mytaxi.entityvo;

import java.time.ZonedDateTime;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarVO {
  
  public Long id;

  public ZonedDateTime dateCreated;

  public String engineType;

  public String licensePlate;

  public Integer seatCount;

  public String model;
  
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
