package com.mammadli.automated_parking_lot.services.impl;

import com.mammadli.automated_parking_lot.db.dto.FloorDto;
import com.mammadli.automated_parking_lot.db.entity.Floor;
import com.mammadli.automated_parking_lot.db.entity.ParkingLot;
import com.mammadli.automated_parking_lot.db.repository.FloorRepository;
import com.mammadli.automated_parking_lot.db.repository.ParkingLotRepository;
import com.mammadli.automated_parking_lot.mapper.FloorMapper;
import com.mammadli.automated_parking_lot.services.FloorServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FloorServicesImpl implements FloorServices {

    private final FloorRepository floorRepository;
    private final ParkingLotRepository parkingLotRepository;

    @Override
    public Floor create(FloorDto floorDto) {

        Floor newFloor = FloorMapper.INSTANCE.toFloor(floorDto);
        Optional<ParkingLot> parkingLot = parkingLotRepository.findById(floorDto.getParkingLotId());
        if (parkingLot.isEmpty()) {
            return null;
        }
        newFloor.setParkingLot(parkingLot.get());
        return floorRepository.save(newFloor);
    }


    @Override
    public Void delete(String id) {

        Optional<Floor> result = floorRepository.findById(id);
        result.ifPresent(floor -> floorRepository.deleteById(floor.getId()));
        return null;
    }
}
