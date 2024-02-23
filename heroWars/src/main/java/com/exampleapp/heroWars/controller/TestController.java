package com.exampleapp.heroWars.controller;

import com.exampleapp.heroWars.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final UserService userService;


    @GetMapping("/username")
    public ResponseEntity<String> getCurrentUserName(Principal principal){
        return ResponseEntity.ok(userService.getUsername());
    }

}
