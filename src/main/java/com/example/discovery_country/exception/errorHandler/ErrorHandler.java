package com.example.discovery_country.exception.errorHandler;


import com.example.discovery_country_app.exception.UserNotFoundException;
import com.example.discovery_country.exception.error.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handlerUserNotFoundException(Exception exception) {
        log.error("handlerUserNotFoundException {}", exception.getMessage());

        return ErrorResponse.builder()
                .code(HttpStatus.NOT_FOUND.name())
                .message(exception.getMessage())
                .build();
    }
}