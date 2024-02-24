package com.exampleapp.heroWars.service;

import com.exampleapp.heroWars.exception.hero.AlreadyHaveHeroException;
import com.exampleapp.heroWars.exception.hero.HeroWithThisNameAlreadyExistsException;
import com.exampleapp.heroWars.exception.hero.NoHeroConnectedToUserException;
import com.exampleapp.heroWars.model.Hero;
import com.exampleapp.heroWars.model.User;
import com.exampleapp.heroWars.model.dto.HeroResponseDTO;
import com.exampleapp.heroWars.model.dto.HeroRequestDTO;
import com.exampleapp.heroWars.repository.HeroRepository;
import com.exampleapp.heroWars.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HeroService {

    //Injections
    private final HeroRepository heroRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    //Static values
    static final int BASE_DAMAGE = 6;
    static final int BASE_ARMOR = 2;
    static final int MIN_HP_PERCENTAGE = 20;
    static final int HP_CONSTANT = 100;

    public boolean alreadyExists (String name){
        return heroRepository.findHeroByName(name).isPresent();
    }

    public HeroResponseDTO createHero(HeroRequestDTO request){
        if(userAlreadyHasHero()){
            throw new AlreadyHaveHeroException();
        }
        if(alreadyExists(request.getName())){
            throw new HeroWithThisNameAlreadyExistsException();
        }
        User user = userService.getUser();
        Hero hero = new Hero(request.getName());
        heroRepository.save(hero);
        user.setHero(hero);
        userRepository.save(user);
        return HeroResponseDTO
                .builder()
                .name(hero.getName())
                .level(hero.getLevel())
                .hp(hero.getHp())
                .experiencePoints(hero.getExperiencePoints())
                .gold(hero.getGold())
                .build();
    }

    public int damageTotal(Hero hero){
        return hero.getLevel() * BASE_DAMAGE;
    }

    public int receiveDamage (int damage, Hero hero){
        double injury  = (double) damage / BASE_ARMOR;
        hero.setHp((int) (hero.getHp() - Math.floor(injury)));
        return (int) Math.floor(injury);
    }

    //If Hp is lower than MIN_HP_PERCENTAGE of max hp Hero is not able to fight and must be healed
    public boolean isHeroAbleToFight (Hero hero) {
        return hero.getHp() > (getMaxHp(hero) / MIN_HP_PERCENTAGE);
    }

    public int getMaxHp(Hero hero){
        return hero.getLevel() * HP_CONSTANT;
    }

    public boolean userAlreadyHasHero(){
        return userService.getUser().getHero() != null;
    }

    public Hero getMyHero(){
        return userService.getUser().getHero();
    }

    public HeroResponseDTO showMyHero() {
        if(!userAlreadyHasHero()){
            throw new NoHeroConnectedToUserException();
        }
        Hero hero = getMyHero();
        return HeroResponseDTO
                .builder()
                .name(hero.getName())
                .experiencePoints(hero.getExperiencePoints())
                .hp(hero.getHp())
                .gold(hero.getGold())
                .level(hero.getLevel())
                .build();
    }
}
