package com.mammadli.automated_parking_lot.api;

import com.mammadli.automated_parking_lot.db.entity.ParkingLot;
import com.mammadli.automated_parking_lot.services.ParkingLotServices;
import com.mammadli.automated_parking_lot.util.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/parkingLot")
public class ParkingLotController {
    private final ParkingLotServices parkingLotServices;

    @PostMapping
    ResponseEntity<ResponseData<ParkingLot>> create(@RequestBody ParkingLot parkingLot){
        return ResponseEntity.ok(parkingLotServices.create(parkingLot));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseData<String>> delete(@PathVariable String id){
        return ResponseEntity.ok(parkingLotServices.delete(id));
    }

}
