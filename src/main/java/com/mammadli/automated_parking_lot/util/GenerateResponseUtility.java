package com.mammadli.automated_parking_lot.util;

import com.mammadli.automated_parking_lot.db.entity.*;

public class GenerateResponseUtility {

    public static GenerateResponse<Integer, String, String , ResponseData<String>> func = (code, message, data) ->
            ResponseData.<String>builder()
                    .code(code)
                    .message(message)
                    .data(data)
                    .build();

    public static GenerateResponse<Integer, String, Floor, ResponseData<Floor>> floorFunc = (code, message, data) ->
            ResponseData.<Floor>builder()
                    .code(code)
                    .message(message)
                    .data(data)
                    .build();

    public static GenerateResponse<Integer, String, ParkingLot, ResponseData<ParkingLot>> parkingLotFunc = (code, message, data) ->
            ResponseData.<ParkingLot>builder()
                    .code(code)
                    .message(message)
                    .data(data)
                    .build();
    public static GenerateResponse<Integer, String, Car, ResponseData<Car>> carFunc = (code, message, data) ->
            ResponseData.<Car>builder()
                    .code(code)
                    .message(message)
                    .data(data)
                    .build();
    public static GenerateResponse<Integer, String, Float, ResponseData<Float>> unparkFunc = (code, message, data) ->
            ResponseData.<Float>builder()
                    .code(code)
                    .message(message)
                    .data(data)
                    .build();
}
