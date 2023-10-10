package com.example.ecommerce.common.exception.handler;

import com.example.ecommerce.common.exception.EntityNotFoundException;
import com.example.ecommerce.common.exception.InvalidParamException;
import com.example.ecommerce.common.utils.ApiUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> exception(Exception ex) {
        log.error("handling {}, message : {}", ex.getClass().toString(), ex.getMessage());
        var status = HttpStatus.INTERNAL_SERVER_ERROR;

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiUtils.error(status, status.getReasonPhrase()));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> exception(EntityNotFoundException ex) {
        log.error("handling {}, message : {}", ex.getClass().toString(), ex.getMessage());
        var status = HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(status)
                .body(ApiUtils.error(status, ex));
    }

    @ExceptionHandler(InvalidParamException.class)
    public ResponseEntity<?> exception(InvalidParamException ex) {
        log.error("handling {}, message : {}", ex.getClass().toString(), ex.getMessage());
        var status = HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(status)
                .body(ApiUtils.error(status, ex));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> exception(MethodArgumentNotValidException ex) {
        log.error("handling {}, message : {}", ex.getClass().toString(), ex.getLocalizedMessage());
        var status = HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(status)
                .body(ApiUtils.error(status, "Validation Error", ex.getBindingResult()));
    }
}
