package com.example.discovery_country.exception;

public class RefreshTokenNotFoundException extends RuntimeException{
    public RefreshTokenNotFoundException(String code, String message){
        super(message);
    }

}