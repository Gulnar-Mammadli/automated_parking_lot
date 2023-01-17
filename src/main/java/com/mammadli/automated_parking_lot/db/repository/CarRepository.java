package com.mammadli.automated_parking_lot.db.repository;

import com.mammadli.automated_parking_lot.db.entity.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car,String> {

}
