package com.example.discovery_country_app.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String code, String message){
        super(message);
    }

}