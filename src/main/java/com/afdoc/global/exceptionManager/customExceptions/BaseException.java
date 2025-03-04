package com.afdoc.global.exceptionManager.customExceptions;

import org.springframework.http.HttpStatus;

//base exception for custom Exception!!
public class BaseException extends RuntimeException {
    private final HttpStatus httpStatus;

    public BaseException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public BaseException(String message) {
        super(message);
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public BaseException(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
