package com.afdoc.global.security.jwt;


import com.afdoc.global.security.config.AccessRole;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;
    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain)
            throws ServletException, IOException {


        if(isExcluded(AccessRole.GUEST.getUrls(), request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = resolveToken(request);

        if (token == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized: Missing token");
            return;
        }

        JwtUtils.JwtData jwtData = jwtUtils.tokenInfo(token);
        if(jwtData.getJwtStatus() == JwtUtils.JwtStatus.VALID) {
            Long id = jwtData.getId();
            String role = jwtData.getRole();
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    id, null, List.of(new SimpleGrantedAuthority(role))
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else if(jwtData.getJwtStatus() == JwtUtils.JwtStatus.EXPIRED) {

            System.out.println("token expired...");
            return;
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized: Invalid token");
            return;
        }
        filterChain.doFilter(request, response);
    }

    private boolean isExcluded(List<String> urls, String url) {
        for(String prefix : urls) {
            if(url.equals(prefix)) return true;
            if(prefix.endsWith("/") && url.startsWith(prefix)) return true;
        }
        return false;
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
