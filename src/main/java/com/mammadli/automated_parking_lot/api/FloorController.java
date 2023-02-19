package com.mammadli.automated_parking_lot.api;

import com.mammadli.automated_parking_lot.db.dto.FloorDto;
import com.mammadli.automated_parking_lot.db.entity.Floor;
import com.mammadli.automated_parking_lot.services.FloorServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/floors")
public class FloorController {
    private final FloorServices floorServices;

    @PostMapping
    Floor create(@RequestBody FloorDto floorDto) {
        return floorServices.create(floorDto);
    }

    @DeleteMapping("/{id}")
    Void delete(@PathVariable String id) {
        return floorServices.delete(id);
    }
}
