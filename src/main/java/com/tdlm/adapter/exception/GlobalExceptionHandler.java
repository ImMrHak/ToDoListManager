package com.tdlm.adapter.exception;

import com.tdlm.adapter.exception.user.UserNotFoundException;
import com.tdlm.adapter.wrapper.ResponseWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> userNotFoundException(UserNotFoundException e) {
        Map<String, Object> response = ResponseWrapper.error("User not found", HttpStatus.NOT_FOUND);
        // ADDING LOGS HERE FOR THE DATABASE !
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
