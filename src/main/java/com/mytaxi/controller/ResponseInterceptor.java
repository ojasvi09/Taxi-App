package com.mytaxi.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class ResponseInterceptor {

  public static <T> ResponseEntity<T> prepareResponseEntity(T responseBody) {
    HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.add("CONTENT-TYPE", MediaType.APPLICATION_JSON_VALUE);
    return ResponseEntity.ok().headers(responseHeaders).body(responseBody);
  }

  public static <T> ResponseEntity<T> prepareResponseEntity(ResponseEntity<T> response) {

    return prepareResponseEntity(response.getBody());
  }

  private ResponseInterceptor() {}
}
