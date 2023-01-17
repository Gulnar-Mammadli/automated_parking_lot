package com.mammadli.automated_parking_lot.api;

import com.mammadli.automated_parking_lot.db.dto.FloorDto;
import com.mammadli.automated_parking_lot.db.entity.Floor;
import com.mammadli.automated_parking_lot.services.FloorServices;
import com.mammadli.automated_parking_lot.util.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/floors")
public class FloorController {
    private final FloorServices floorServices;

    @PostMapping
    ResponseEntity<ResponseData<Floor>> create(@RequestBody FloorDto floor){
       return ResponseEntity.ok(floorServices.create(floor));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseData<String>> delete(@PathVariable String id){
        return ResponseEntity.ok(floorServices.delete(id));
    }
}
