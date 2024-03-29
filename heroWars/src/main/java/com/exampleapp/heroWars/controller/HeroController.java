package com.exampleapp.heroWars.controller;

import com.exampleapp.heroWars.model.Hero;
import com.exampleapp.heroWars.model.dto.HeroResponseDTO;
import com.exampleapp.heroWars.model.dto.HeroRequestDTO;
import com.exampleapp.heroWars.service.HeroService;
import com.exampleapp.heroWars.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/hero")
@RequiredArgsConstructor
public class HeroController {

    private final UserService userService;

    private final HeroService heroService;

    @PostMapping("/create")
    public ResponseEntity<HeroResponseDTO> createHero(
            @RequestBody  HeroRequestDTO request){
        return ResponseEntity.ok(heroService.createHero(request));
    }

    @GetMapping("/myHero")
    public ResponseEntity<HeroResponseDTO> getHeroData(){
        return ResponseEntity.ok(heroService.showMyHero());
    }
}
