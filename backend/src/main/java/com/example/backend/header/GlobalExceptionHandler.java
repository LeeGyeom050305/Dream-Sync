package com.example.backend.header;

import com.example.backend.dto.ErrorResponse;
import com.example.backend.enums.ErrorCode;
import com.example.backend.exception.AppException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RestController
public class GlobalExceptionHandler {

    private static final String LOG_CODE_FORMAT = "Class : {}, Code : {}, Message : {}";

    @ExceptionHandler(value = {AppException.class})
    protected ResponseEntity<Object> handleCustomException(AppException e) {
        ErrorCode errorCode = e.getErrorCode();
        String exceptionClassName = e.getClass().getSimpleName();
        String message = e.getMessage();

        log.error(LOG_CODE_FORMAT, exceptionClassName, errorCode, message);
        return ErrorResponse.toResponseObjectEntity(errorCode, message);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    protected ResponseEntity<Object> bindException(ConstraintViolationException e) {
        log.error("ExceptionCode : {} , ExceptionMessage : {}", ErrorCode.INVALID_INPUT_VALUE.getHttpStatus(), e.getMessage());
        return ErrorResponse.toResponseObjectEntity(ErrorCode.INVALID_INPUT_VALUE, e.getMessage());
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    protected ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException e) {
        ErrorCode errorCode = ErrorCode.INVALID_REQUEST;
        String exceptionClassName = e.getClass().getSimpleName();
        String message = e.getMessage();

        log.error(LOG_CODE_FORMAT, exceptionClassName, errorCode, message);
        return ErrorResponse.toResponseObjectEntity(errorCode, message);
    }

}