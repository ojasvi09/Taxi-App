package com.mytaxi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytaxi.entity.Car;
import com.mytaxi.entityvo.CarRequestVO;
import com.mytaxi.entityvo.CarVO;
import com.mytaxi.repository.CarRepository;

@Service
public class CarService {

  @Autowired
  CarRepository carRepository;

  @Autowired
  NextSequenceService nextSequenceService;

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
    carRepository.deleteById(id);
  }

  public CarVO updateCar(long id, CarRequestVO car) throws Exception {
    Car updatedCar = new Car();
    Car carObj = carRepository.findById(id).orElseThrow(() -> new Exception("Car Not Found"));
    updatedCar = new ObjectMapper().findAndRegisterModules().convertValue(car, Car.class);
    updatedCar.setId(carObj.getId());
    updatedCar.setDateCreated(carObj.getDateCreated());
    carRepository.save(updatedCar);
    return new ObjectMapper().findAndRegisterModules().convertValue(updatedCar, CarVO.class);
  }

}
