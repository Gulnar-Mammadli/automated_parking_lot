package com.mammadli.automated_parking_lot.services;

import com.mammadli.automated_parking_lot.db.dto.CarDto;
import com.mammadli.automated_parking_lot.db.entity.Car;

import java.util.List;

public interface CarServices {
    Car parkNewCar(CarDto carDto);

    Void unParkCar(String carId,String parkingLotId);

    List<Car> getCars(String floorId);
}
