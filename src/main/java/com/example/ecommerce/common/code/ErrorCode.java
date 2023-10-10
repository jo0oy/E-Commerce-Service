package com.example.ecommerce.common.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    ENTITY_NOT_FOUND("Entity Not Found", HttpStatus.BAD_REQUEST.value()),
    INVALID_PARAM("Invalid Param Requested", HttpStatus.BAD_REQUEST.value());

    private final String message;
    private final Integer statusCode;
}
