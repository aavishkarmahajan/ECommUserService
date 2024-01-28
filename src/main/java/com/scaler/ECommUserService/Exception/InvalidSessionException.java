package com.scaler.ECommUserService.Exception;

public class InvalidSessionException extends RuntimeException{
    public InvalidSessionException() {
    }

    public InvalidSessionException(String message) {
        super(message);
    }
}
