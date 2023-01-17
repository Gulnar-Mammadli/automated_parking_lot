package com.mammadli.automated_parking_lot.services;

import com.mammadli.automated_parking_lot.db.dto.FloorDto;
import com.mammadli.automated_parking_lot.db.entity.Floor;
import com.mammadli.automated_parking_lot.util.ResponseData;

public interface FloorServices {
    ResponseData<Floor> create(FloorDto floor);
    ResponseData<String> delete(String id);
}
