package com.mammadli.automated_parking_lot.services;

import com.mammadli.automated_parking_lot.db.dto.ParkingLotDto;
import com.mammadli.automated_parking_lot.db.entity.ParkingLot;

public interface ParkingLotServices {
    ParkingLot create(ParkingLotDto parkingLotDto);
    Void delete(String id);
}
