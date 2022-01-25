package com.mytaxi.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytaxi.constant.ApplicationConstants;
import com.mytaxi.entity.Car;
import com.mytaxi.entity.Driver;
import com.mytaxi.entityvo.DriverRequestVO;
import com.mytaxi.entityvo.DriverVO;
import com.mytaxi.exception.CarInUseException;
import com.mytaxi.exception.InvalidResouceException;
import com.mytaxi.repository.CarRepository;
import com.mytaxi.repository.DriverRepository;

@Service
public class DriverService {

  @Autowired
  DriverRepository driverRepository;

  @Autowired
  CarService carService;

  @Autowired
  CarRepository carRepository;

  @Autowired
  MongoOperations mongoOperations;

  @Autowired
  NextSequenceService nextSequenceService;

  public Object getDriver(long id) throws Exception {
    return driverRepository.findById(id)
        .orElseThrow(() -> new Exception(ApplicationConstants.DRIVER_NOT_FOUND));
  }

  public void deleteDriver(long id) {
    driverRepository.deleteById(id);
  }


  public DriverVO insertDriver(DriverRequestVO driver) {
    Driver obj = new ObjectMapper().findAndRegisterModules().convertValue(driver, Driver.class);
    if (driver.getCar() != null) {
      Car car = new ObjectMapper().findAndRegisterModules()
          .convertValue(carService.insertCar(driver.getCar()), Car.class);
      obj.setCar(car);
    }
    obj.setId(nextSequenceService.getNextCarSequence("driverSequences"));
    driverRepository.save(obj);
    return new ObjectMapper().findAndRegisterModules().convertValue(obj, DriverVO.class);
  }


  public DriverVO updateDriver(long id, DriverRequestVO driver) throws Exception {
    Driver updatedDriver = new Driver();
    Driver driverObj = driverRepository.findById(id)
        .orElseThrow(() -> new InvalidResouceException(ApplicationConstants.DRIVER_NOT_FOUND));
    updatedDriver = new ObjectMapper().findAndRegisterModules().convertValue(driver, Driver.class);
    if (driverObj.getCar() != null) {
      updatedDriver.setCar(new ObjectMapper().findAndRegisterModules().convertValue(
          carService.updateCar(driverObj.getCar().getId(), driver.getCar()), Car.class));
    } else {
      updatedDriver.setCar(new ObjectMapper().findAndRegisterModules()
          .convertValue(carService.insertCar(driver.getCar()), Car.class));
    }

    updatedDriver.setId(driverObj.getId());
    updatedDriver.setDateCreated(driverObj.getDateCreated());
    driverRepository.save(updatedDriver);
    return new ObjectMapper().findAndRegisterModules().convertValue(updatedDriver, DriverVO.class);
  }

  public List<Driver> getDrivesByStatus(String status) {
    return driverRepository.findByStatus(status);
  }

  public List<Driver> getDriverWithCriteria(Map<String, Object> params, int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    Query query = new Query().with(pageable);
    for (Map.Entry<String, Object> entry : params.entrySet()) {
      query.addCriteria(Criteria.where(entry.getKey()).is(entry.getValue()));
    }
    List<Driver> list = mongoOperations.find(query, Driver.class);
    long count = mongoOperations.count(query, Driver.class);
    Page<Driver> resultPage = new PageImpl<>(list, pageable, count);
    return resultPage.getContent();
  }

  public List<Driver> getAll(int page, int size) {
    Pageable paging = PageRequest.of(page, size);
    Page<Driver> pageTuts = driverRepository.findAll(paging);
    return pageTuts.getContent();
  }

  public DriverVO deselectCar(long driverId, long carId) throws InvalidResouceException {

    Driver driverObj = driverRepository.findById(driverId)
        .orElseThrow(() -> new InvalidResouceException(ApplicationConstants.DRIVER_NOT_FOUND));

    if (driverObj.getCar() == null) {
      throw new InvalidResouceException(ApplicationConstants.NO_CAR_ASSIGNED);
    }

    if (driverObj.getCar() != null && driverObj.getCar().getId() != carId) {
      throw new InvalidResouceException(ApplicationConstants.CAR_NOT_ALLOCATED);
    }

    driverObj.setCar(null);
    driverRepository.save(driverObj);

    return new ObjectMapper().findAndRegisterModules().convertValue(driverObj, DriverVO.class);
  }

  public DriverVO selectCar(long driverId, long carId) throws InvalidResouceException {
    Driver driverObj = driverRepository.findById(driverId)
        .orElseThrow(() -> new InvalidResouceException(ApplicationConstants.DRIVER_NOT_FOUND));

    if (driverObj.getCar() != null && driverObj.getCar().getId() == carId) {
      throw new InvalidResouceException(ApplicationConstants.ALREADY_SELECTED);
    }

    Car car = carRepository.findById(carId)
        .orElseThrow(() -> new InvalidResouceException(ApplicationConstants.CAR_NOT_FOUND));

    Driver used = driverRepository.findByCarId(carId);

    if (used != null && used.getStatus().equalsIgnoreCase(ApplicationConstants.ONLINE)) {
      throw new CarInUseException(
          "car in use by driver " + used.getName() + " with id " + used.getId());
    }
    if (used != null) {
      used.setCar(null);
      driverRepository.save(used);
    }
    driverObj.setCar(car);
    driverRepository.save(driverObj);


    return new ObjectMapper().findAndRegisterModules().convertValue(driverObj, DriverVO.class);
  }


}
