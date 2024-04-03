package com.ksteindl.gatewayservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {


    @GetMapping
    @RequestMapping("/userFallback")
    public Mono<String> fallbackUserService() {
        return Mono.just("Fallback user service");
    }

}
