package com.example.ecommerce.common.exception;

public class InvalidParamException extends RuntimeException {
    public InvalidParamException() {
        super();
    }

    public InvalidParamException(String message) {
        super(message);
    }

    public InvalidParamException(String message, Throwable cause) {
        super(message, cause);
    }
}
