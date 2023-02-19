package com.mammadli.automated_parking_lot.services.impl;

import com.mammadli.automated_parking_lot.db.dto.ParkingLotDto;
import com.mammadli.automated_parking_lot.db.entity.ParkingLot;
import com.mammadli.automated_parking_lot.db.repository.ParkingLotRepository;
import com.mammadli.automated_parking_lot.mapper.ParkingLotMapper;
import com.mammadli.automated_parking_lot.services.ParkingLotServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParkingLotServicesImpl implements ParkingLotServices {

    private final ParkingLotRepository parkingLotRepository;

    @Override
    public ParkingLot create(ParkingLotDto parkingLotDto) {

        return parkingLotRepository.save(ParkingLotMapper.INSTANCE.toParkingLot(parkingLotDto));
    }

    @Override
    public Void delete(String id) {

        Optional<ParkingLot> result = parkingLotRepository.findById(id);
        if (result.isPresent()) {
            parkingLotRepository.deleteById(id);
        }
        return null;
    }
}
