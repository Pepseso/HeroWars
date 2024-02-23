package com.exampleapp.heroWars.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

@GetMapping("/testAuth")
    public ResponseEntity<String> testAuth() {
    return ResponseEntity.ok("Auth works!!");
}

}
