package com.mytaxi.entityvo;

import java.time.ZonedDateTime;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mytaxi.entity.Car;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DriverVO {

  private Long id;

  private String name;

  public ZonedDateTime dateCreated;
  
  private String contact;
  
  private String status;
  
  private CarVO car;

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

  public CarVO getCar() {
    return car;
  }

  public void setCar(CarVO car) {
    this.car = car;
  }
  
  

}
