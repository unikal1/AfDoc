package com.afdoc.global.exceptionManager.customExceptions.authException;


import com.afdoc.global.exceptionManager.customExceptions.BaseException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * occur when login fail because of invalid username or password.
 */
@Getter
public class BadAuthenticationException extends BaseException {
    private final Long userId;

    public BadAuthenticationException(String message, Long userId) {
        super(message, HttpStatus.UNAUTHORIZED);
        this.userId = userId;
    }

    public BadAuthenticationException(Long userId) {
        super(HttpStatus.UNAUTHORIZED);
        this.userId = userId;
    }

    public BadAuthenticationException() {
        super(HttpStatus.UNAUTHORIZED);
        this.userId = -1L;
    }
}
