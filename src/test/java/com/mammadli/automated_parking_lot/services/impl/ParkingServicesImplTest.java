package com.mammadli.automated_parking_lot.services.impl;

import com.mammadli.automated_parking_lot.db.entity.Car;
import com.mammadli.automated_parking_lot.db.entity.Floor;
import com.mammadli.automated_parking_lot.db.entity.ParkingLot;
import com.mammadli.automated_parking_lot.db.repository.CarRepository;
import com.mammadli.automated_parking_lot.db.repository.FloorRepository;
import com.mammadli.automated_parking_lot.util.ResponseData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {ParkingServicesImpl.class})
@ExtendWith(SpringExtension.class)
class ParkingServicesImplTest {

    public static int SUCCESS_CODE = 200;
    public static int NOT_FOUND_CODE = 404;
    public static String SUCCESS_MESSAGE = "SUCCESS";
    public static String NOT_FOUND_MESSAGE = "NOT_FOUND";

    @MockBean
    private FloorRepository floorRepository;

    @MockBean
    private CarRepository carRepository;

    @Autowired
    private ParkingServicesImpl parkingServicesImpl;
    Car car1 = Car.builder()
            .id("123")
            .height(1)
            .weight(100)
            .parkingTime(LocalDateTime.now())
            .unparkingTime(LocalDateTime.now())
            .build();

    List<Car> cars = List.of(car1);

    ParkingLot parkingLot = ParkingLot.builder()
            .id("111")
            .price(1.5f)
            .build();
    Floor floor = Floor.builder()
            .id("12")
            .ceilingHeight(10)
            .weightCapacity(1000)
            .maxCapacity(5)
            .parkingLot(parkingLot)
            .cars(cars)
            .build();
    Car car2 = Car.builder()
            .id("124")
            .height(2)
            .weight(110)
            .parkingTime(LocalDateTime.now().minusMinutes(5))
            .floor(floor)
            .build();

    @Test
    void parkNewCarSuccess() {

        car1.setHeight(5);
        car1.setWeight(100);
        List<Floor> floors = Arrays.asList(floor);

        when(floorRepository.findByCeilingHeightGreaterThan(car1.getHeight())).thenReturn(floors);

        ResponseData<Car> result = parkingServicesImpl.parkNewCar(car1);

        assertEquals(SUCCESS_CODE, result.getCode());
        assertEquals(SUCCESS_MESSAGE, result.getMessage());
        assertEquals(car1, result.getData());
        assertEquals(floor, car1.getFloor());
        assertEquals(LocalDateTime.now().getDayOfYear(), car1.getParkingTime().getDayOfYear());
        verify(carRepository).save(car1);
        verify(floorRepository).findByCeilingHeightGreaterThan(car1.getHeight());

    }

    @Test
    public void testParkNewCar_floorDoesNotHaveSpace() {

        car1.setHeight(2);
        car1.setWeight(1000);

        ResponseData<Car> response = parkingServicesImpl.parkNewCar(car1);

        assertEquals(NOT_FOUND_CODE, response.getCode());
        assertEquals(NOT_FOUND_MESSAGE, response.getMessage());
        assertNull(response.getData());
    }

    @Test
    void testUnparkCarSuccess() {

        when(carRepository.findById(any())).thenReturn(Optional.of(car2));
        when(carRepository.save(any())).thenReturn(car2);

        ResponseData<Float> result = parkingServicesImpl.unparkCar(car2.getId());

        assertEquals(SUCCESS_CODE, result.getCode());
        assertEquals(SUCCESS_MESSAGE, result.getMessage());
        assertNotNull(result);
        assertTrue(result.getData() > 0);
        verify(carRepository).findById(car2.getId());
        verify(carRepository).save(car2);
    }

    @Test
    public void testUnparkCarNotFound() {

        String carId = "123";
        when(carRepository.findById(any())).thenReturn(Optional.empty());

        ResponseData<Float> result = parkingServicesImpl.unparkCar(carId);

        assertEquals(NOT_FOUND_CODE, result.getCode());
        assertEquals(NOT_FOUND_MESSAGE, result.getMessage());
        verify(carRepository).findById(carId);
    }

    @Test
    public void testParkNewCar_NoSuitableFloors() {

        car1.setHeight(11);

        when(floorRepository.findByCeilingHeightGreaterThan(car1.getHeight())).thenReturn(null);

        ResponseData<Car> result = parkingServicesImpl.parkNewCar(car1);

        assertEquals(NOT_FOUND_CODE, result.getCode());
        assertEquals(NOT_FOUND_MESSAGE, result.getMessage());
        assertEquals(null, result.getData());
        verify(floorRepository).findByCeilingHeightGreaterThan(car1.getHeight());
    }
}
