package com.mytaxi.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import com.mytaxi.entity.Driver;

/**
 * Database Access Object for driver table.
 * <p/>
 */
public interface DriverRepository extends CrudRepository<Driver, Long>
{
  
  List<Driver> findByStatus(String status);
  
  Page<Driver> findAll(Pageable page); 
  
  Driver findByCarId(long id);
  
  Driver findByStatusAndCarId(String string, long id);
 
}
