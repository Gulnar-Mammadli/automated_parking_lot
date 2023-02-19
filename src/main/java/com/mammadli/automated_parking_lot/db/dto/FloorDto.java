package com.mammadli.automated_parking_lot.db.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FloorDto {

    private int ceilingHeight;

    private int weightCapacity;

    private int maxCapacity;

    private String parkingLotId;
}
