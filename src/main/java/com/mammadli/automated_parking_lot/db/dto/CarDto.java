package com.mammadli.automated_parking_lot.db.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDto {

    private int height;

    private int weight;

    private String floorId;

    private LocalDateTime parkingTime;

    private LocalDateTime unparkingTime;
}
