package com.scaler.ECommUserService.Exception;

public class InvalidCredentialsException extends RuntimeException{
    public InvalidCredentialsException() {
    }

    public InvalidCredentialsException(String message) {
        super(message);
    }
}
