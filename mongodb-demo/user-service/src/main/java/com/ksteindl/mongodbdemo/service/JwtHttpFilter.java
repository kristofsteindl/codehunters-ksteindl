package com.ksteindl.mongodbdemo.service;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class JwtHttpFilter extends OncePerRequestFilter {

    public static final String HEADER_PREFIX = "Bearer ";

    private final JwtProvider tokenProvider;

    public JwtHttpFilter(JwtProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    private Optional<String> resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (null != bearerToken && bearerToken.startsWith(HEADER_PREFIX)) {
            return Optional.of(bearerToken.replace(HEADER_PREFIX, ""));
        }

        return Optional.empty();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws
            ServletException, IOException {
        Optional<String> tokenOpt = resolveToken(request);
        if (tokenOpt.isPresent()) {
            String token = tokenOpt.get();
            if (tokenProvider.validateToken(token)) {
                Authentication authentication = tokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                
            }
        }
        filterChain.doFilter(request, response);
    }
}
