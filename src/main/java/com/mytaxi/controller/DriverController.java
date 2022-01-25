package com.mytaxi.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.mytaxi.entity.Driver;
import com.mytaxi.entityvo.DriverRequestVO;
import com.mytaxi.entityvo.DriverVO;
import com.mytaxi.exception.InvalidResouceException;
import com.mytaxi.service.DriverService;

@RestController
@RequestMapping("driver")
public class DriverController {

  @Autowired
  DriverService driverService;

  @GetMapping("/{id}")
  public ResponseEntity<Object> fetchDriver(@PathVariable long id) throws Exception {
    return ResponseInterceptor.prepareResponseEntity(driverService.getDriver(id));
  }

  @PostMapping
  public ResponseEntity<DriverVO> insertDriver(@RequestBody DriverRequestVO driver) {
    return ResponseInterceptor.prepareResponseEntity(driverService.insertDriver(driver));
  }

  @PutMapping("/{id}")
  public ResponseEntity<DriverVO> updateDriver(@PathVariable long id , @RequestBody DriverRequestVO driver) throws Exception {
    return ResponseInterceptor.prepareResponseEntity(driverService.updateDriver(id,driver));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteDriver(@PathVariable long id) {
    driverService.deleteDriver(id);
    return ResponseInterceptor.prepareResponseEntity(new ResponseEntity<>(HttpStatus.OK));
  }

  @GetMapping("/status/{status}")
  public ResponseEntity<List<Driver>> fetchDriver(@PathVariable String status) throws Exception {
    return ResponseInterceptor.prepareResponseEntity(driverService.getDrivesByStatus(status));
  }

  @GetMapping("/filter")
  public ResponseEntity<List<Driver>> fetchDriverByCriteria(
      @RequestParam Map<String, Object> params,
      @RequestHeader int page,
      @RequestHeader int size
      ) throws Exception {
    return ResponseInterceptor.prepareResponseEntity(driverService.getDriverWithCriteria(params,page,size));
  }

  @GetMapping("/all")
  public ResponseEntity<List<Driver>> getAll(@RequestParam int page, @RequestParam int size) {
    return ResponseInterceptor.prepareResponseEntity(driverService.getAll(page, size));
  }


  @DeleteMapping("/deselectCar")
  public ResponseEntity<DriverVO> deselectCar(@RequestParam long driverId, @RequestParam long carId)
      throws InvalidResouceException {
    return ResponseInterceptor.prepareResponseEntity(driverService.deselectCar(driverId, carId));
  }

  @DeleteMapping("/selectCar")
  public ResponseEntity<DriverVO> selectCar(@RequestParam long driverId, @RequestParam long carId)
      throws InvalidResouceException {
    return ResponseInterceptor.prepareResponseEntity(driverService.selectCar(driverId, carId));
  }


}
