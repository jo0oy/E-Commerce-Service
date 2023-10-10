package com.example.ecommerce.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

@Slf4j
public class ApiUtils {
    public static <T> ApiResult<T> success(T response) {
        return ApiResult.res(true, response, null);
    }

    public static ApiResult<?> error(HttpStatus status, Throwable throwable) {
        return ApiResult.res(false, null, ApiResult.ApiError.of(status.value(), throwable));
    }

    public static ApiResult<?> error(HttpStatus status, String message) {
        return ApiResult.res(false, null, ApiResult.ApiError.of(status.value(), message));
    }

    public static ApiResult<?> error(HttpStatus status, String message, BindingResult bindingResult) {
        return ApiResult.res(false, null, ApiResult.ApiError.of(status.value(), message, bindingResult));
    }

}
