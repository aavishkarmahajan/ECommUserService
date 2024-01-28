package com.scaler.ECommUserService.Exception;

public class UserAlreadyLoggedOut extends RuntimeException{
    public UserAlreadyLoggedOut() {
    }

    public UserAlreadyLoggedOut(String message) {
        super(message);
    }
}
