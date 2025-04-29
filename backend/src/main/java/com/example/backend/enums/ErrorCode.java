package com.example.backend.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /* 400 BAD_REQUEST : 잘못된 요청 */
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "Request Validation failed."),
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "The input value is invalid."),
    INVALID_STATUS(HttpStatus.BAD_REQUEST, "The Status is invalid."),

    /* 404 NOT_FOUND : Resource not found */
    PROFILE_NOT_FOUND(HttpStatus.NOT_FOUND, "Resource could not be found."),
    CODE_NOT_FOUND(HttpStatus.NOT_FOUND, "Code could not be found."),
    NAME_NOT_FOUND(HttpStatus.NOT_FOUND, "Name could not be found."),
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "Product could not be found."),
    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, "Order could not be found."),
    INVOICE_NOT_FOUND(HttpStatus.NOT_FOUND, "Invoice could not be found."),
    LINE_ITEM_NOT_FOUND(HttpStatus.NOT_FOUND, "Invoice could not be found."),
    COMPANY_NOT_FOUND(HttpStatus.NOT_FOUND, "Company could not be found."),
    TAX_INVOICE_NOT_FOUND(HttpStatus.NOT_FOUND, "Tax invoice could not be found."),
    ORDER_STATUS_NOT_FOUND(HttpStatus.NOT_FOUND, "Order status could not be found."),
    DOCUMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "Document could not be found."),

    /* 422 CONFLICT : Unprocessable Entity */
    PROFILE_CONFLICT(HttpStatus.CONFLICT, "Data conflict."),

    /* 401 UNAUTHORIZED : Unauthorized user */
    EXPIRED_JWT_TOKEN(HttpStatus.UNAUTHORIZED, "The token has expired."),
    UNAUTHORIZED_MEMBER(HttpStatus.UNAUTHORIZED, "The current account information does not exist."),
    FAIL_AUTHORIZATION(HttpStatus.UNAUTHORIZED, "Authentication failed."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "The password does not match."),
    NOT_AUTHORIZATION(HttpStatus.UNAUTHORIZED, "No permission."),
    USER_NOT_FOUND(HttpStatus.UNAUTHORIZED, "User information could not be found."),

    /* 403 FORBIDDEN : Forbidden user */
    FORBIDDEN_MEMBER(HttpStatus.FORBIDDEN, "Unauthorized user."),
    DUPLICATE_USERNAME(HttpStatus.FORBIDDEN, "The username already exists."),
    DUPLICATE_KEY(HttpStatus.FORBIDDEN, "Duplicate key."),

    /* 500 SERVER ERROR */
    IMAGE_UPLOAD_FAIL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to upload the image to the S3 bucket."),
    FAIL_PROCESS(HttpStatus.INTERNAL_SERVER_ERROR, "Process failed."),
    EXTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "External server error."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error.");


    private final HttpStatus httpStatus;
    private final String detail;
}