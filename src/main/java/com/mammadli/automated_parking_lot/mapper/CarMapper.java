package com.mammadli.automated_parking_lot.mapper;

import com.mammadli.automated_parking_lot.db.dto.CarDto;
import com.mammadli.automated_parking_lot.db.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    Car mapToCar(CarDto carDto);
}
