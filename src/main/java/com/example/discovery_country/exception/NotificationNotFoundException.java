package com.example.discovery_country.exception;

public class NotificationNotFoundException extends RuntimeException{
    public NotificationNotFoundException(String code, String message){
        super(message);
    }

}