package com.mammadli.automated_parking_lot.services.impl;

import com.mammadli.automated_parking_lot.db.entity.Car;
import com.mammadli.automated_parking_lot.db.entity.Floor;
import com.mammadli.automated_parking_lot.db.repository.CarRepository;
import com.mammadli.automated_parking_lot.db.repository.FloorRepository;
import com.mammadli.automated_parking_lot.services.ParkingServices;
import com.mammadli.automated_parking_lot.util.GenerateResponseUtility;
import com.mammadli.automated_parking_lot.util.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParkingServicesImpl implements ParkingServices {
    public static int SUCCESS_CODE = 200;
    public static int NOT_FOUND_CODE = 404;
    public static String SUCCESS_MESSAGE = "SUCCESS";
    public static String NOT_FOUND_MESSAGE = "NOT_FOUND";
    private final FloorRepository floorRepository;
    private final CarRepository carRepository;

    public ResponseData<Car> parkNewCar(Car car){

        List<Floor> floors = floorRepository.findByCeilingHeightGreaterThan(car.getHeight());
        if(floors !=null){
        for(Floor floor: floors) {
            if (floor.spaceExists()) {
                car.setFloor(floor);
                car.setParkingTime(LocalDateTime.now());
                carRepository.save(car);
                return GenerateResponseUtility.carFunc.generate(SUCCESS_CODE,SUCCESS_MESSAGE,car);
            }
        }}
        return GenerateResponseUtility.carFunc.generate(NOT_FOUND_CODE,NOT_FOUND_MESSAGE,null);
    }

    public ResponseData<Float> unparkCar(String carId){

        Optional<Car> car = carRepository.findById(carId);
        if(car.isEmpty()){
            return GenerateResponseUtility.unparkFunc.generate(NOT_FOUND_CODE,NOT_FOUND_MESSAGE,null);
        }
        car.get().setUnparkingTime(LocalDateTime.now());
        float price = (float) (Duration.between(car.get().getParkingTime(), car.get().getUnparkingTime()).toMinutes() *
                car.get().getFloor().getParkingLot().getPrice());
        car.get().setFloor(null);
        if(pay(price,carId)) {
            carRepository.save(car.get());
        }
        return GenerateResponseUtility.unparkFunc.generate(SUCCESS_CODE,SUCCESS_MESSAGE,price);
    }

    private boolean pay(float bill,String carId){
        return true;
    }
}
