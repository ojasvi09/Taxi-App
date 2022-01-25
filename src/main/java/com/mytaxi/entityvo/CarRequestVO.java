package com.mytaxi.entityvo;

public class CarRequestVO {
  public String engineType;

  public String licensePlate;

  public Integer seatCount;

  public String model;

  private String manufacturer;
  

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
