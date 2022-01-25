package com.mytaxi.exception;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(InvalidResouceException.class)
  public ResponseEntity<ExceptionResponse> handleAllExceptions(InvalidResouceException ex, WebRequest request) {
    ExceptionResponse error =
        new ExceptionResponse(ex.getMessage(),LocalDateTime.now());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }
  
  
  @ExceptionHandler(CarInUseException.class)
  public ResponseEntity<ExceptionResponse> handleAllExceptions(CarInUseException ex, WebRequest request) {
    ExceptionResponse error =
        new ExceptionResponse(ex.getMessage(),LocalDateTime.now());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }
}

