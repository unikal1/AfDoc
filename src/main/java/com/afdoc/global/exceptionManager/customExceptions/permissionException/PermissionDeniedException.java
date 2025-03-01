package com.afdoc.global.exceptionManager.customExceptions.permissionException;

import com.afdoc.global.exceptionManager.customExceptions.BaseException;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public class PermissionDeniedException extends BaseException {
    private final Long userId;
    private final String permissionName;
    private final boolean isReport;
    private final boolean isLogged;

    @Builder
    public PermissionDeniedException(String message, Long userId, String permissionName, boolean isReport, boolean isLogged) {
        super(message != null ? message : "Permission Denied", HttpStatus.FORBIDDEN);
        this.userId = userId;
        this.permissionName = permissionName;
        this.isReport = Boolean.TRUE.equals(isReport);
        this.isLogged = Boolean.TRUE.equals(isLogged);
    }
}
