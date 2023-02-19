package com.mammadli.automated_parking_lot.mapper;

import com.mammadli.automated_parking_lot.db.dto.FloorDto;
import com.mammadli.automated_parking_lot.db.entity.Floor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FloorMapper {

    FloorMapper INSTANCE = Mappers.getMapper(FloorMapper.class);

    Floor toFloor(FloorDto floorDto);

    FloorDto toFloorDto(Floor floor);
}
