package com.mammadli.automated_parking_lot.services.impl;

import com.mammadli.automated_parking_lot.db.entity.ParkingLot;
import com.mammadli.automated_parking_lot.db.repository.ParkingLotRepository;
import com.mammadli.automated_parking_lot.util.ResponseData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

@ContextConfiguration(classes = {ParkingLotServicesImpl.class})
@ExtendWith(SpringExtension.class)
class ParkingLotServicesImplTest {
    public static int SUCCESS_CODE = 200;
    public static int NOT_FOUND_CODE = 404;
    public static int ALREADY_EXIST_CODE = 409;
    public static String SUCCESS_MESSAGE = "SUCCESS";
    public static String NOT_FOUND_MESSAGE = "NOT_FOUND";
    public static String ALREADY_EXIST_MESSAGE = "ALREADY EXIST";

    @MockBean
    private ParkingLotRepository parkingLotRepository;

    @Autowired
    private ParkingLotServicesImpl parkingLotServicesImpl;

    @Test
    void testCreateSuccess() {

        ParkingLot parkingLot = new ParkingLot();
        when(parkingLotRepository.save(parkingLot)).thenReturn(parkingLot);

        ResponseData<ParkingLot> actualResult = parkingLotServicesImpl.create(parkingLot);

        assertEquals(SUCCESS_CODE, actualResult.getCode());
        assertEquals(SUCCESS_MESSAGE, actualResult.getMessage());
        assertEquals(parkingLot, actualResult.getData());
        verify(parkingLotRepository).save(parkingLot);
    }

    @Test
    public void testCreateAlreadyExist() {

        ParkingLot parkingLot = new ParkingLot();
        when(parkingLotRepository.save(parkingLot)).thenReturn(null);

        ResponseData<ParkingLot> result = parkingLotServicesImpl.create(parkingLot);

        assertEquals(ALREADY_EXIST_CODE, result.getCode());
        assertEquals(ALREADY_EXIST_MESSAGE, result.getMessage());
        assertEquals(null, result.getData());
        verify(parkingLotRepository).save(parkingLot);
    }

    @Test
    void testDeleteSuccess() {
        String id = "1";
        ParkingLot parkingLot = new ParkingLot();
        Optional<ParkingLot> optionalResult = Optional.of(parkingLot);
        when(parkingLotRepository.findById(id)).thenReturn(optionalResult);

        ResponseData<String> actualResult = parkingLotServicesImpl.delete(id);

        assertEquals(SUCCESS_CODE, actualResult.getCode());
        assertEquals(SUCCESS_MESSAGE, actualResult.getMessage());
        assertEquals(null, actualResult.getData());
        verify(parkingLotRepository).findById(id);
    }

    @Test
    void testDeleteNotFound() {
        String id = "1";
        when(parkingLotRepository.findById(id)).thenReturn(Optional.empty());

        ResponseData<String> actualResult = parkingLotServicesImpl.delete(id);

        assertEquals(NOT_FOUND_CODE, actualResult.getCode());
        assertEquals(NOT_FOUND_MESSAGE, actualResult.getMessage());
        assertEquals(null, actualResult.getData());

    }

}