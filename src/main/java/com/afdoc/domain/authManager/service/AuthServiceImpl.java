package com.afdoc.domain.authManager.service;

import com.afdoc.domain.memberManager.dao.MemberRepository;
import com.afdoc.domain.memberManager.entity.Member;
import com.afdoc.global.security.jwt.JwtToken;
import com.afdoc.global.security.jwt.JwtUtils;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
    private final JwtUtils jwtUtils;
    private final MemberRepository memberRepository;

    @Override
    public JwtToken signInByUsernamePassword(String username, String password) {
        try {
            Member member = memberRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            return jwtUtils.createJwt(member.getId(), member.getRole());
        } catch (UsernameNotFoundException | BadCredentialsException e) {
            throw new JwtException("bad value insert");
        } catch (Exception e) {
            throw new IllegalArgumentException("unknown error occur");
        }
    }
}
