package com.example.discovery_country.exception;

public class HomeHotelNotFoundException extends RuntimeException{
    public HomeHotelNotFoundException(String code, String message){
        super(message);
    }

}