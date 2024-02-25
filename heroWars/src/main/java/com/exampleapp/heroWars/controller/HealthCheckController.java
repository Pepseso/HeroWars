package com.exampleapp.heroWars.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/healthCheck")
public class HealthCheckController {

@GetMapping("/authenticated")
    public ResponseEntity<String> testAuth() {
    return ResponseEntity.ok("It seems that security works");
}

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("HealthCheck OK");
    }
}

