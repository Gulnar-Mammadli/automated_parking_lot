package com.mammadli.automated_parking_lot.services;

import com.mammadli.automated_parking_lot.db.entity.ParkingLot;
import com.mammadli.automated_parking_lot.util.ResponseData;

public interface ParkingLotServices {
    ResponseData<ParkingLot> create(ParkingLot parkingLot);
    ResponseData<String> delete(String id);
}
