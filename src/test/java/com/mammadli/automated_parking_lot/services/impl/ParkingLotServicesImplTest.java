//package com.mammadli.automated_parking_lot.services.impl;
//
//import com.mammadli.automated_parking_lot.db.entity.ParkingLot;
//import com.mammadli.automated_parking_lot.db.repository.ParkingLotRepository;
//import com.mammadli.automated_parking_lot.util.ResponseData;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ContextConfiguration(classes = {ParkingLotServicesImpl.class})
//@ExtendWith(SpringExtension.class)
//class ParkingLotServicesImplTest {
//    public static int SUCCESS_CODE = 200;
//    public static int NOT_FOUND_CODE = 404;
//    public static String SUCCESS_MESSAGE = "SUCCESS";
//    public static String NOT_FOUND_MESSAGE = "NOT_FOUND";
//
//    @MockBean
//    private ParkingLotRepository parkingLotRepository;
//
//    @Autowired
//    private ParkingLotServicesImpl parkingLotServicesImpl;
//
//    String id = "1";
//
//    @Test
//    void testCreateSuccess() {
//
//        ParkingLot parkingLot = new ParkingLot();
//        when(parkingLotRepository.save(any())).thenReturn(parkingLot);
//
//        ParkingLot actualResult = parkingLotServicesImpl.create(parkingLot);
//
//        assertEquals(SUCCESS_CODE, actualResult.getCode());
//        assertEquals(SUCCESS_MESSAGE, actualResult.getMessage());
//        assertEquals(parkingLot, actualResult.getData());
//        verify(parkingLotRepository).save(parkingLot);
//    }
//
//    @Test
//    void testDeleteSuccess() {
//
//        ParkingLot parkingLot = new ParkingLot();
//        Optional<ParkingLot> optionalResult = Optional.of(parkingLot);
//        when(parkingLotRepository.findById(any())).thenReturn(optionalResult);
//
//        ResponseData<String> actualResult = parkingLotServicesImpl.delete(id);
//
//        assertEquals(SUCCESS_CODE, actualResult.getCode());
//        assertEquals(SUCCESS_MESSAGE, actualResult.getMessage());
//        assertEquals(null, actualResult.getData());
//        verify(parkingLotRepository).findById(id);
//    }
//
//    @Test
//    void testDeleteNotFound() {
//
//        when(parkingLotRepository.findById(any())).thenReturn(Optional.empty());
//
//        ResponseData<String> actualResult = parkingLotServicesImpl.delete(id);
//
//        assertEquals(NOT_FOUND_CODE, actualResult.getCode());
//        assertEquals(NOT_FOUND_MESSAGE, actualResult.getMessage());
//        assertEquals(null, actualResult.getData());
//
//    }
//
//}