package com.afdoc.domain.authManager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class AuthApiDto {

    @Getter
    @AllArgsConstructor
    public static class SignIn {
        private String username;
        private String password;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Register {
        private String username;
        private String password;
        private String name;
        private String imgUrl;
    }
}
