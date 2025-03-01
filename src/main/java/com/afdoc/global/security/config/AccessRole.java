package com.afdoc.global.security.config;

import lombok.Getter;

import java.util.List;


@Getter
public enum AccessRole {
    GUEST(List.of(
            "/login",
            "/register",
            "/first-page"
    )),
    
    USER(List.of(
            "/home"
    )),
    
    ADMIN(List.of(
            "/admin/**"
    ));
    
    
    private final List<String> urls;
    
    AccessRole(List<String> urls) {
        this.urls = urls;
    }
    
}
