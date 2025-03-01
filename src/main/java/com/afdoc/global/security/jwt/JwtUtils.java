package com.afdoc.global.security.jwt;

import com.afdoc.domain.memberManager.dao.MemberRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Objects;


@Component
public class JwtUtils {
    private final SecretKey secretKey;
    private final Long expiredDateAccessToken;
    private final Long expiredDateRefreshToken;

    @Autowired
    public JwtUtils(@Value("${jwt.secret}")String secretKey,
                    @Value("${jwt.expireDate.accessToken}") Long expireDateAccessToken,
                    @Value("${jwt.expireDate.refreshToken}") long expireDateRefreshToken) {
        this.secretKey = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8),
                Jwts.SIG.HS256.key().build().getAlgorithm());
        this.expiredDateAccessToken = expireDateAccessToken;
        this.expiredDateRefreshToken = expireDateRefreshToken;
    }

    public JwtData tokenInfo(String token) {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();


            String issue = claims.getIssuer();
            if(!Objects.equals(issue, "afdoc")) throw new JwtException("invalid issuer");

            Long userId = claims.get("id", Long.class);
            String role = claims.get("role", String.class);

            return new JwtData(userId, role);
        } catch (ExpiredJwtException e) {
            return new JwtData(JwtStatus.EXPIRED);
        } catch (Exception e) {
            return new JwtData(JwtStatus.INVALID);
        }
    }

    public JwtToken createJwt(Long id, String role) {
        return JwtToken.builder()
                .grantType("Bearer")
                .accessToken(getAccessToken(id, role))
                .refreshToken(getRefreshToken(id))
                .build();
    }

    private String getAccessToken(Long id, String role) {
        return Jwts.builder()
                .claim("id", id)
                .claim("role", role)
                .issuer("afdoc")
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiredDateAccessToken))
                .signWith(secretKey)
                .compact();
    }

    private String getRefreshToken(Long id) {
        return Jwts.builder()
                .claim("id", id)
                .issuer("afdoc")
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiredDateRefreshToken))
                .signWith(secretKey)
                .compact();
    }

    @Getter
    public static class JwtData {
        private final JwtStatus jwtStatus;
        private final Long id;
        private final String role;

        public JwtData(Long id, String role) {
            this.jwtStatus = JwtStatus.VALID;
            this.id = id;
            this.role = role;
        }

        public JwtData(JwtStatus jwtStatus) {
            this.jwtStatus = jwtStatus;
            this.id = null;
            this.role = null;
        }
    }

    public enum JwtStatus {
        VALID,
        INVALID,
        EXPIRED
    }

}
