package com.mammadli.automated_parking_lot.api;

import com.mammadli.automated_parking_lot.db.dto.CarDto;
import com.mammadli.automated_parking_lot.db.entity.Car;
import com.mammadli.automated_parking_lot.services.CarServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/parking")
@RestController
public class CarController {
    private final CarServices parkingServices;

    @PostMapping
    Car parkNewCar(@RequestBody CarDto carDto) {
        return parkingServices.parkNewCar(carDto);
    }

    @DeleteMapping("/{carId}/{parkingLotId}")
    Void unParkCar(@PathVariable String carId,@PathVariable String parkingLotId) {
        return parkingServices.unParkCar(carId,parkingLotId);
    }
}
