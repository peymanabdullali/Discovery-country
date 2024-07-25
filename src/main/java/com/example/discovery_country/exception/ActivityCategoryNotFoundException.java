package com.example.discovery_country.exception;

public class ActivityCategoryNotFoundException extends RuntimeException{
    public ActivityCategoryNotFoundException(String code, String message){
        super(message);
    }

}