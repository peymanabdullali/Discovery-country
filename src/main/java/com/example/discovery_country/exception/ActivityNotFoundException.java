package com.example.discovery_country.exception;

public class ActivityNotFoundException extends RuntimeException{
    public ActivityNotFoundException(String code, String message){
        super(message);
    }

}