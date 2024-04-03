package com.ksteindl.mongodbdemo.controller;

import com.ksteindl.mongodbdemo.controller.dto.LoginRequest;
import com.ksteindl.mongodbdemo.controller.dto.LoginResponse;
import com.ksteindl.mongodbdemo.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/login")
@Slf4j
public class AuthenticationController {
    
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    @PostMapping
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            String token = authenticationService.generateToken(loginRequest.getUsername(), loginRequest.getPassword());
            return ResponseEntity.ok(new LoginResponse(token));
        } catch (Exception exception) {
            log.warn("Authentication failed", exception);
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
  }
    
}
