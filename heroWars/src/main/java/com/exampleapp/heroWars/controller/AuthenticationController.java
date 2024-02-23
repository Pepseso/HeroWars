package com.exampleapp.heroWars.controller;

import com.exampleapp.heroWars.model.dto.AuthenticationResponse;
import com.exampleapp.heroWars.model.User;
import com.exampleapp.heroWars.model.dto.LoginDTO;
import com.exampleapp.heroWars.model.dto.RegisterDTO;
import com.exampleapp.heroWars.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthenticationController {

private final AuthenticationService authenticationService;


    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterDTO request
    ){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody LoginDTO request
    ){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }


}
