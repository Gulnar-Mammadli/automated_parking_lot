package com.mammadli.automated_parking_lot.mapper;

import com.mammadli.automated_parking_lot.db.dto.ParkingLotDto;
import com.mammadli.automated_parking_lot.db.entity.ParkingLot;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ParkingLotMapper {

    ParkingLotMapper INSTANCE = Mappers.getMapper(ParkingLotMapper.class);

    ParkingLot toParkingLot(ParkingLotDto parkingLotDto);

}
