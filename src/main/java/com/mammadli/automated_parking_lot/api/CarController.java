package com.mammadli.automated_parking_lot.api;

import com.mammadli.automated_parking_lot.db.dto.CarDto;
import com.mammadli.automated_parking_lot.db.entity.Car;
import com.mammadli.automated_parking_lot.services.CarServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/parking")
@RestController
public class CarController {
    private final CarServices carServices;

    @PostMapping
    Car parkNewCar(@RequestBody CarDto carDto) {
        return carServices.parkNewCar(carDto);
    }

    @GetMapping("/{floorId}")
    List<Car> getCars(@PathVariable String floorId){
        return carServices.getCars(floorId);
    }

    @DeleteMapping("/{carId}/{parkingLotId}")
    Void unParkCar(@PathVariable String carId,@PathVariable String parkingLotId) {
        return carServices.unParkCar(carId,parkingLotId);
    }
}
