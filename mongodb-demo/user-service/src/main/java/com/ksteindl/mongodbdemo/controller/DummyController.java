package com.ksteindl.mongodbdemo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/dummy")
public class DummyController {
    
    @Value("${services.user-service.external-configuration}")
    private String stringFromConfig;
    
    @GetMapping
    public String getString() {
        return "Foo";
    }

    @GetMapping("/config")
    public String getConfigString() {
        return stringFromConfig;
    }
}
