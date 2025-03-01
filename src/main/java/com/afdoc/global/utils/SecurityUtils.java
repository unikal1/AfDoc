package com.afdoc.global.utils;

import com.afdoc.domain.memberManager.dao.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityUtils {

    private final MemberRepository memberRepository;

    public Long getUserId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication.getName() == null) {
            throw new UsernameNotFoundException("No user in context holder or else");
        }
        return (Long) authentication.getPrincipal();

    }
}
