package com.afdoc.global.security.jwt;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class JwtToken {
    private String grantType;
    private String accessToken;
    private String refreshToken;
}
