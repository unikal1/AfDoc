package com.afdoc.global.exceptionManager.customExceptions.authException;

import com.afdoc.global.exceptionManager.customExceptions.BaseException;
import org.springframework.http.HttpStatus;


/**
 * occur when access to upper level domain.
 */
public class AccessDeniedException extends BaseException {


    public AccessDeniedException(String message) {
        super(message, HttpStatus.FORBIDDEN);
    }

    public AccessDeniedException() {
        super(HttpStatus.FORBIDDEN);
    }

}
