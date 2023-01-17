package com.mammadli.automated_parking_lot.services.impl;

import com.mammadli.automated_parking_lot.db.dto.FloorDto;
import com.mammadli.automated_parking_lot.db.entity.Floor;
import com.mammadli.automated_parking_lot.db.entity.ParkingLot;
import com.mammadli.automated_parking_lot.db.repository.FloorRepository;
import com.mammadli.automated_parking_lot.db.repository.ParkingLotRepository;
import com.mammadli.automated_parking_lot.util.ResponseData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {FloorServicesImpl.class})
@ExtendWith(SpringExtension.class)
class FloorServicesImplTest {

    public static int SUCCESS_CODE = 200;
    public static int NOT_FOUND_CODE = 404;
    public static String SUCCESS_MESSAGE = "SUCCESS";
    public static String NOT_FOUND_MESSAGE = "NOT_FOUND";
    @MockBean
    private FloorRepository floorRepository;

    @MockBean
    private  ParkingLotRepository parkingLotRepository;

    @MockBean
    private ModelMapper modelMapper;

    @Autowired
    private FloorServicesImpl floorServicesImpl;

    @Test
    void testCreateSuccess() {

            FloorDto floorDto = new FloorDto();
            Floor newFloor = new Floor();
            ParkingLot parkingLot = new ParkingLot();
            when(modelMapper.map(any(), any())).thenReturn(newFloor);
            when(parkingLotRepository.findById(any())).thenReturn(Optional.of(parkingLot));
            when(floorRepository.save(any())).thenReturn(newFloor);

            ResponseData<Floor> result = floorServicesImpl.create(floorDto);

            assertEquals(SUCCESS_CODE, result.getCode());
            assertEquals(SUCCESS_MESSAGE, result.getMessage());
            assertEquals(newFloor, result.getData());
            verify(modelMapper).map(floorDto, Floor.class);
            verify(parkingLotRepository).findById(floorDto.getParkingLotId());
            verify(floorRepository).save(newFloor);
    }

        @Test
        public void testCreate_ParkingLotNotFound() {
            FloorDto floorDto = new FloorDto();
            when(parkingLotRepository.findById(floorDto.getParkingLotId())).thenReturn(Optional.empty());

            ResponseData<Floor> result = floorServicesImpl.create(floorDto);

            assertEquals(NOT_FOUND_CODE, result.getCode());
            assertEquals(NOT_FOUND_MESSAGE, result.getMessage());
            assertEquals(null, result.getData());
            verify(parkingLotRepository).findById(floorDto.getParkingLotId());
        }

    @Test
    void testDeleteSuccess() {
        String id = "1";
        Floor floor = new Floor();
        Optional<Floor> optionalResult = Optional.of(floor);
        when(floorRepository.findById(id)).thenReturn(optionalResult);

        ResponseData<String> actualResult = floorServicesImpl.delete(id);

        assertEquals(SUCCESS_CODE, actualResult.getCode());
        assertEquals(SUCCESS_MESSAGE, actualResult.getMessage());
        assertEquals(null, actualResult.getData());
        verify(floorRepository).findById(id);
    }

    @Test
    void testDeleteNotFound() {
        String id = "1";
        when(floorRepository.findById(id)).thenReturn(Optional.empty());

        ResponseData<String> actualResult = floorServicesImpl.delete(id);

        assertEquals(NOT_FOUND_CODE, actualResult.getCode());
        assertEquals(NOT_FOUND_MESSAGE, actualResult.getMessage());
        assertEquals(null, actualResult.getData());

    }

}