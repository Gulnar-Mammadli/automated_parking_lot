package com.mammadli.automated_parking_lot.db.dto;

import lombok.Data;

@Data
public class FloorDto {
    private int ceilingHeight;
    private int weightCapacity;
    private int maxCapacity;
    private String parkingLotId;
}
