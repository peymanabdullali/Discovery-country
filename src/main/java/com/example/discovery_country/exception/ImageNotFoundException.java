package com.example.discovery_country.exception;

public class ImageNotFoundException extends RuntimeException{
    public ImageNotFoundException(String code, String message){
        super(message);
    }

}