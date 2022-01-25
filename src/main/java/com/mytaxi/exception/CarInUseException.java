package com.mytaxi.exception;

public class CarInUseException extends RuntimeException{
  private static final long serialVersionUID = 1L;
  
private String message;
  
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public CarInUseException(String message) {
    super();
    this.message = message;
  }
  

}
