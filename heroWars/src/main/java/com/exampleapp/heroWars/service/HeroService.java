package com.exampleapp.heroWars.service;

import com.exampleapp.heroWars.model.Hero;
import com.exampleapp.heroWars.model.User;
import com.exampleapp.heroWars.model.dto.HeroCreationResponseDTO;
import com.exampleapp.heroWars.model.dto.HeroRequestDTO;
import com.exampleapp.heroWars.repository.HeroRepository;
import com.exampleapp.heroWars.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HeroService {

    private final HeroRepository heroRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public boolean alreadyExists (String name){
        return heroRepository.findHeroByName(name).isPresent();
    }

    public HeroCreationResponseDTO createHero(HeroRequestDTO request){
        User user = userService.getUser();
        Hero hero = new Hero(request.getName());
        heroRepository.save(hero);
        user.setHero(hero);
        userRepository.save(user);
        return HeroCreationResponseDTO
                .builder()
                .name(hero.getName())
                .level(hero.getLevel())
                .hp(hero.getHp())
                .experiencePoints(hero.getExperiencePoints())
                .gold(hero.getGold())
                .build();
    }
}
