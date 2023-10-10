package com.example.ecommerce.common.exception;

import com.example.ecommerce.common.code.ErrorCode;

public class InvalidParamException extends RuntimeException {

    public InvalidParamException() {
        super(ErrorCode.INVALID_PARAM.getMessage());
    }

    public InvalidParamException(String message) {
        super(message);
    }

    public InvalidParamException(String message, Throwable cause) {
        super(message, cause);
    }
}
