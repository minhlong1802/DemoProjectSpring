package com.example.demoproject.exception;

public class TodoValidationException extends RuntimeException {
    public TodoValidationException(String message) {
        super(message);
    }
}
