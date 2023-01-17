package com.mammadli.automated_parking_lot.api;

import com.mammadli.automated_parking_lot.db.entity.Car;
import com.mammadli.automated_parking_lot.services.ParkingServices;
import com.mammadli.automated_parking_lot.util.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/parking")
@RestController
public class ParkingController {
    private final ParkingServices parkingServices;

    @PostMapping
    ResponseEntity<ResponseData<Car>> parkNewCar(@RequestBody Car car){
        return ResponseEntity.ok(parkingServices.parkNewCar(car));
    }

    @DeleteMapping("/{carId}")
    ResponseEntity<ResponseData<Float>> unparkCar(String carId){
        return ResponseEntity.ok(parkingServices.unparkCar(carId));
    }
}
