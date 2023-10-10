package com.example.ecommerce.common.exception;

import com.example.ecommerce.common.code.ErrorCode;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException() {
        super(ErrorCode.ENTITY_NOT_FOUND.getMessage());
    }

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
