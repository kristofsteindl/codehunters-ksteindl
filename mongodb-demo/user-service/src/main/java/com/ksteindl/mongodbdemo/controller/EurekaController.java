package com.ksteindl.mongodbdemo.controller;

import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/eureka")
@Slf4j
public class EurekaController {
    
    private final EurekaClient eurekaClient;

    public EurekaController(EurekaClient eurekaClient) {
        this.eurekaClient = eurekaClient;
    }

    @GetMapping
    public void printClientDetails() {
        log.info("Eureka Data: {}", eurekaClient.getApplications()
                        .getRegisteredApplications().stream()
                .map(Application::getName)
                .toList());
    }
    
}
