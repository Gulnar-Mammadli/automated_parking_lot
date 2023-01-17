package com.mammadli.automated_parking_lot.services.impl;

import com.mammadli.automated_parking_lot.db.dto.FloorDto;
import com.mammadli.automated_parking_lot.db.entity.Floor;
import com.mammadli.automated_parking_lot.db.entity.ParkingLot;
import com.mammadli.automated_parking_lot.db.repository.FloorRepository;
import com.mammadli.automated_parking_lot.db.repository.ParkingLotRepository;
import com.mammadli.automated_parking_lot.services.FloorServices;
import com.mammadli.automated_parking_lot.util.GenerateResponseUtility;
import com.mammadli.automated_parking_lot.util.ResponseData;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FloorServicesImpl implements FloorServices {

    public static int SUCCESS_CODE = 200;
    public static int NOT_FOUND_CODE = 404;
    public static String SUCCESS_MESSAGE = "SUCCESS";
    public static String NOT_FOUND_MESSAGE = "NOT_FOUND";
    private final FloorRepository floorRepository;
    private final ParkingLotRepository parkingLotRepository;
    private final ModelMapper modelMapper;

    @Override
    public ResponseData<Floor> create(FloorDto floor) {

        Floor newFloor = modelMapper.map(floor, Floor.class);
        Optional<ParkingLot> parkingLot = parkingLotRepository.findById(floor.getParkingLotId());
        if(parkingLot.isEmpty()){
            return GenerateResponseUtility.floorFunc.generate(NOT_FOUND_CODE,NOT_FOUND_MESSAGE,null);
        }
        newFloor.setParkingLot(parkingLot.get());
        newFloor = floorRepository.save(newFloor);
        return GenerateResponseUtility.floorFunc.generate(SUCCESS_CODE, SUCCESS_MESSAGE, newFloor);
    }


    @Override
    public ResponseData<String> delete(String id) {

        Optional<Floor> result = floorRepository.findById(id);
        if(result.isPresent()) {
            floorRepository.deleteById(result.get().getId());
            return GenerateResponseUtility.func.generate(SUCCESS_CODE, SUCCESS_MESSAGE, null);
        }
        return GenerateResponseUtility.func.generate(NOT_FOUND_CODE,NOT_FOUND_MESSAGE,null);
    }
}
