package com.mammadli.automated_parking_lot.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ResponseData<T> {

    private int code;
    private String message;
    private T data;

}
