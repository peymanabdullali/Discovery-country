package com.example.discovery_country.exception;

public class PasswordInvalidException extends RuntimeException{
    public PasswordInvalidException(String code, String message){
        super(message);
    }

}