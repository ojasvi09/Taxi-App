package com.mytaxi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mytaxi.entityvo.CarRequestVO;
import com.mytaxi.entityvo.CarVO;
import com.mytaxi.service.CarService;

@RestController
@RequestMapping("car")
public class CarController {

  @Autowired
  CarService carService;

  @GetMapping("/{id}")
  public ResponseEntity<CarVO> fetchCar(@PathVariable long id) throws Exception {
    return ResponseInterceptor.prepareResponseEntity(carService.getCar(id));
  }

  @PostMapping
  public ResponseEntity<CarVO> insertCar(@RequestBody CarRequestVO car) {
    return ResponseInterceptor.prepareResponseEntity(carService.insertCar(car));
  }

  @PutMapping("/{id}")
  public ResponseEntity<CarVO> updateCar(@PathVariable long id, @RequestBody CarRequestVO car)
      throws Exception {
    return ResponseInterceptor.prepareResponseEntity(carService.updateCar(id, car));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCar(@PathVariable long id) {
    carService.deleteCar(id);
    return ResponseInterceptor.prepareResponseEntity(new ResponseEntity<>(HttpStatus.OK));
  }



}
