package com.mytaxi.entity;

import java.time.ZonedDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection = "Driver")
public class Driver {

  @Id
  @Field(name = "id")
  private Long id;

  @Field
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private ZonedDateTime dateCreated = ZonedDateTime.now();

  @Field(name = "full_name")
  private String name;

  @Field(name = "contact_num")
  private String contact;


  @Field
  private String status;

  @Field
  private Car car;

  public Driver() {
    super();
  }

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

  public Car getCar() {
    return car;
  }

  public void setCar(Car car) {
    this.car = car;
  }



}
