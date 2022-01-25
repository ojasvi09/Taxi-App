package com.mytaxi.repository;

import com.mytaxi.entity.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends CrudRepository<Car, Long>
{}
