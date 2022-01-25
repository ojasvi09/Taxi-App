package com.mytaxi.entityvo;

public class DriverRequestVO {

  private String name;

  private String contact;
  
  private String status;
  
  private CarRequestVO car;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public CarRequestVO getCar() {
    return car;
  }

  public void setCar(CarRequestVO car) {
    this.car = car;
  }
  
  
}
