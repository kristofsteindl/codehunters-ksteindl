package com.ksteindl.mongodbdemo.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;


    public AuthenticationService(AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }
    
    public String generateToken(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
        
        return jwtProvider.createToken(authentication);
    }
    
}
