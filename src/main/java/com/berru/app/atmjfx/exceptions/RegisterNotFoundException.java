package com.berru.app.atmjfx.exceptions;

public class RegisterNotFoundException extends Exception{
    public RegisterNotFoundException(String message) {
        super(message);
    }

    // Parameterless Constructor
    public RegisterNotFoundException() {
        super("Record not found");
    }
}
