package com.example.backend.exception;

import com.example.backend.enums.ErrorCode;
import lombok.Getter;

@Getter
public class AppException extends RuntimeException {
    private final ErrorCode errorCode;
    private final String message;

    public AppException(ErrorCode errorCode) {
        super(errorCode.getDetail());
        this.errorCode = errorCode;
        this.message = "";
    }

    public AppException(ErrorCode errorCode, String message) {
        super(errorCode.getDetail() + ": " + message);
        this.errorCode = errorCode;
        this.message = message;
    }

}