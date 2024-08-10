package com.example.discovery_country.exception;

public class RoomNotFoundException extends RuntimeException{
    public RoomNotFoundException(String code, String message){
        super(message);
    }

}