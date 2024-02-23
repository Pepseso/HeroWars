package com.exampleapp.heroWars.service;

import com.exampleapp.heroWars.model.Hero;
import com.exampleapp.heroWars.repository.HeroRepository;
import com.exampleapp.heroWars.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HeroService {

    private final HeroRepository heroRepository;
    private final UserRepository userRepository;

    public boolean alreadyExists (String name){
        return heroRepository.findHeroByName(name).isPresent();
    }

    public Hero createHero(){
        return null;
    }
}
