package com.mammadli.automated_parking_lot.services;

import com.mammadli.automated_parking_lot.db.entity.Car;
import com.mammadli.automated_parking_lot.util.ResponseData;

public interface ParkingServices {

    ResponseData<Car> parkNewCar(Car car);

    ResponseData<Float> unparkCar(String carId);
}
