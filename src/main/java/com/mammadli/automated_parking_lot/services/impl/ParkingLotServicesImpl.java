package com.mammadli.automated_parking_lot.services.impl;

import com.mammadli.automated_parking_lot.db.entity.ParkingLot;
import com.mammadli.automated_parking_lot.db.repository.ParkingLotRepository;
import com.mammadli.automated_parking_lot.services.ParkingLotServices;
import com.mammadli.automated_parking_lot.util.GenerateResponseUtility;
import com.mammadli.automated_parking_lot.util.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParkingLotServicesImpl implements ParkingLotServices {
    public static int SUCCESS_CODE = 200;
    public static int NOT_FOUND_CODE = 404;
    public static String SUCCESS_MESSAGE = "SUCCESS";
    public static String NOT_FOUND_MESSAGE = "NOT_FOUND";

    private final ParkingLotRepository parkingLotRepository;
    @Override
    public ResponseData<ParkingLot> create(ParkingLot parkingLot) {

        ParkingLot newParkingLot = parkingLotRepository.save(parkingLot);
        return GenerateResponseUtility.parkingLotFunc.generate(SUCCESS_CODE, SUCCESS_MESSAGE, newParkingLot);
    }

    @Override
    public ResponseData<String> delete(String id) {

        Optional<ParkingLot> result = parkingLotRepository.findById(id);
        if(result.isPresent()){
            parkingLotRepository.deleteById(result.get().getId());
            return GenerateResponseUtility.func.generate(SUCCESS_CODE,SUCCESS_MESSAGE,null);
        }
        return GenerateResponseUtility.func.generate(NOT_FOUND_CODE,NOT_FOUND_MESSAGE,null);
    }
}
