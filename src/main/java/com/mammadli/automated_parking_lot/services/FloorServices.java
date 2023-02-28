package com.mammadli.automated_parking_lot.services;

import com.mammadli.automated_parking_lot.db.dto.FloorDto;
import com.mammadli.automated_parking_lot.db.entity.Floor;

import java.util.List;

public interface FloorServices {
    Floor create(FloorDto floorDto);

    Void delete(String id);

    List<FloorDto> getFloors(String parkingLotId);
}
