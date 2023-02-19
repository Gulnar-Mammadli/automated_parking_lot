package com.mammadli.automated_parking_lot.api;

import com.mammadli.automated_parking_lot.db.dto.ParkingLotDto;
import com.mammadli.automated_parking_lot.db.entity.ParkingLot;
import com.mammadli.automated_parking_lot.services.ParkingLotServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/parkingLot")
public class ParkingLotController {
    private final ParkingLotServices parkingLotServices;

    @PostMapping
    ParkingLot create(@RequestBody ParkingLotDto parkingLotDto) {
        return parkingLotServices.create(parkingLotDto);
    }

    @DeleteMapping("/{id}")
    Void delete(@PathVariable String id) {
        return parkingLotServices.delete(id);
    }

}
