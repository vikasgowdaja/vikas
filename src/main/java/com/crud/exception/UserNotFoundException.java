package com.crud.exception;

// Custom Exception (extends RuntimeException - unchecked)
public class UserNotFoundException extends RuntimeException {

    private final int errorCode;

    // Constructor with message only
    public UserNotFoundException(String message) {
        super(message);
        this.errorCode = 404;
    }

    // Constructor with message + custom error code (Overloading)
    public UserNotFoundException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
