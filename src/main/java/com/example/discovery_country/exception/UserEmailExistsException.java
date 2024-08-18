package com.example.discovery_country.exception;

public class UserEmailExistsException extends RuntimeException{
    public UserEmailExistsException(String code, String message){
        super(message);
    }

}