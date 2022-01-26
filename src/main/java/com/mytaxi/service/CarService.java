package com.mytaxi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytaxi.entity.Car;
import com.mytaxi.entity.Driver;
import com.mytaxi.entityvo.CarRequestVO;
import com.mytaxi.entityvo.CarVO;
import com.mytaxi.repository.CarRepository;
import com.mytaxi.repository.DriverRepository;

@Service
public class CarService {

  @Autowired
  CarRepository carRepository;

  @Autowired
  NextSequenceService nextSequenceService;

  @Autowired
  DriverRepository driverRepository;

  public CarVO getCar(long id) throws Exception {
    Car car = carRepository.findById(id).orElseThrow(() -> new Exception("Car Not Found"));
    return new ObjectMapper().findAndRegisterModules().convertValue(car, CarVO.class);
  }

  public CarVO insertCar(CarRequestVO car) {
    Car carobj = new ObjectMapper().convertValue(car, Car.class);
    carobj.setId(nextSequenceService.getNextCarSequence("carSequences"));
    carRepository.save(carobj);
    return new ObjectMapper().findAndRegisterModules().convertValue(carobj, CarVO.class);
  }

  public void deleteCar(long id) {
    Driver driver = driverRepository.findByCarId(id);
    if (driver != null) {
      driver.setCar(null);
      driverRepository.save(driver);
    }

    carRepository.deleteById(id);
  }

  public CarVO updateCar(long id, CarRequestVO car) throws Exception {
    Car updatedCar = new Car();
    Car carObj = carRepository.findById(id).orElseThrow(() -> new Exception("Car Not Found"));
    updatedCar = new ObjectMapper().findAndRegisterModules().convertValue(car, Car.class);
    updatedCar.setId(carObj.getId());
    updatedCar.setDateCreated(carObj.getDateCreated());

    Driver driver = driverRepository.findByCarId(id);
    if (driver != null) {
      driver.setCar(updatedCar);
      driverRepository.save(driver);
    }

    carRepository.save(updatedCar);
    return new ObjectMapper().findAndRegisterModules().convertValue(updatedCar, CarVO.class);
  }

}
