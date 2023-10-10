package com.example.ecommerce.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Slf4j
@ToString
@Getter
public class ApiResult<T> {
    private final boolean success;
    private final T response;
    private final ApiError<?> error;

    @Builder
    private ApiResult(final boolean success,
                      final T response,
                      final ApiError<?> error) {
        this.success = success;
        this.response = response;
        this.error = error;
    }

    public static <T> ApiResult<T> res(final boolean success,
                                       final T response,
                                       ApiError<?> error) {
        return ApiResult.<T>builder()
                .success(success)
                .response(response)
                .error(error)
                .build();
    }

    @ToString
    @Getter
    public static class ApiError<T> {
        private final int status;
        private final String message;

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        private final T data;

        private ApiError(int status, String message, T data) {
            this.status = status;
            this.message = message;
            this.data = data;
        }

        public static ApiError<?> of(int status, String message) {
            return new ApiError<>(status, message, null);
        }

        public static ApiError<?> of(int status, Throwable throwable) {
            return new ApiError<>(status, throwable.getMessage(), null);
        }

        public static ApiError<?> of(int status, String message, BindingResult bindingResult) {
            var data = bindingResult.getFieldErrors()
                    .stream()
                    .map(ValidationError::of)
                    .toList();

            return new ApiError<>(status, message, data);
        }
    }

    public record ValidationError(String field, String message) {
        public static ValidationError of(final FieldError fieldError) {
            return new ValidationError(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }
}
