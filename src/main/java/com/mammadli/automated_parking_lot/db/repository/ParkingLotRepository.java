package com.mammadli.automated_parking_lot.db.repository;

import com.mammadli.automated_parking_lot.db.entity.ParkingLot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingLotRepository extends CrudRepository<ParkingLot,String> {
}
