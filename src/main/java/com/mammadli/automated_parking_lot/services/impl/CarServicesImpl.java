package com.mammadli.automated_parking_lot.services.impl;

import com.mammadli.automated_parking_lot.db.dto.CarDto;
import com.mammadli.automated_parking_lot.db.entity.Car;
import com.mammadli.automated_parking_lot.db.entity.Floor;
import com.mammadli.automated_parking_lot.db.entity.ParkingLot;
import com.mammadli.automated_parking_lot.db.repository.CarRepository;
import com.mammadli.automated_parking_lot.db.repository.FloorRepository;
import com.mammadli.automated_parking_lot.db.repository.ParkingLotRepository;
import com.mammadli.automated_parking_lot.mapper.CarMapper;
import com.mammadli.automated_parking_lot.services.CarServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarServicesImpl implements CarServices {

    private final FloorRepository floorRepository;
    private final CarRepository carRepository;

    private final ParkingLotRepository parkingLotRepository;

    public Car parkNewCar(CarDto carDto) {

        List<Floor> floors = floorRepository.findByCeilingHeightGreaterThan(carDto.getHeight());
        if (floors != null) {
            for (Floor floor : floors) {
                if (floor.spaceExists()) {
                    carDto.setFloorId(floor.getId());
                    carDto.setParkingTime(LocalDateTime.now());
                    return carRepository.save(CarMapper.INSTANCE.mapToCar(carDto));
//                floor.getCars().add(car);
                }
            }
        }
        return null;
    }

    public Void unParkCar(String carId, String parkingLotId) {

        Optional<Car> car = carRepository.findById(carId);
        Optional<ParkingLot> parkingLot = parkingLotRepository.findById(parkingLotId);
        if (parkingLot.isPresent()) {
            if (car.isPresent()) {
                car.get().setUnparkingTime(LocalDateTime.now());
//        float price = (float) (Duration.between(car.get().getParkingTime(), car.get().getUnparkingTime()).toMinutes() *
//                car.get().getFloor().getParkingLot().getPrice());
                long totalTime = Duration.between(car.get().getParkingTime(), car.get().getUnparkingTime()).toMinutes();
                BigDecimal totalBill = BigDecimal.valueOf(totalTime).multiply(parkingLot.get().getPrice());

                car.get().setFloor(null);
                if (pay(totalBill, carId)) {
                    carRepository.save(car.get());
                }
            }
        }
        return null;
    }


    private boolean pay(BigDecimal bill, String carId) {
        return true;
    }
}
