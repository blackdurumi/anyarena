package com.blackdurumi.anyarena.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException e) {
        log.error("Unknown Server Error: {}", e.getMessage(), e);
        return ResponseEntity.internalServerError()
            .body(ErrorResponse.builder().message(e.getMessage()).build());
    }
}
