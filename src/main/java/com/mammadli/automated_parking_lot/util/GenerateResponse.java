package com.mammadli.automated_parking_lot.util;

@FunctionalInterface
public interface GenerateResponse<Code,Message,Body,ResponseType>{
    ResponseType generate(Code code, Message message, Body body);
}
