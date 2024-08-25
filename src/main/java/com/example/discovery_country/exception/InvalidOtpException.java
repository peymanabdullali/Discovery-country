package com.example.discovery_country.exception;

public class InvalidOtpException extends RuntimeException {
    public InvalidOtpException(String code, String message) {
        super(message);
    }


}
