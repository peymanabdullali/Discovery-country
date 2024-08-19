package com.example.discovery_country.exception;

public class RefreshTokenExpiredException extends RuntimeException{
    public RefreshTokenExpiredException(String code, String message){
        super(message);
    }

}