package com.afdoc.domain.authManager.service;

import com.afdoc.global.security.jwt.JwtToken;

public interface AuthService {
    JwtToken signInByUsernamePassword(String username, String password);
}
