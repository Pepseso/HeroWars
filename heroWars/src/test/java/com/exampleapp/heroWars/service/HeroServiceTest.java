package com.exampleapp.heroWars.service;

import com.exampleapp.heroWars.model.Hero;
import com.exampleapp.heroWars.model.Role;
import com.exampleapp.heroWars.model.User;
import com.exampleapp.heroWars.model.dto.HeroRequestDTO;
import com.exampleapp.heroWars.model.dto.HeroResponseDTO;
import com.exampleapp.heroWars.repository.HeroRepository;
import com.exampleapp.heroWars.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class HeroServiceTest {

    static UserRepository userRepository;
    static UserService userService;
    static HeroRepository heroRepository;
    static HeroService heroService;


    @BeforeAll
    public static void setup() {
        userRepository = Mockito.mock(UserRepository.class);
        userService = Mockito.mock(UserService.class);
        heroRepository = Mockito.mock(HeroRepository.class);
        heroService = new HeroService(heroRepository,userRepository,userService);
    }


    @Test
    public void heroService_createHero_HeroResponseDTO(){
        //
        User user = User
                .builder()
                .password("1234")
                .firstname("test")
                .lastname("test")
                .role(Role.USER)
                .username("test")
                .build();
        Hero hero = new Hero("Some name");
        hero.setUser(user);

        HeroRequestDTO heroRequestDTO = HeroRequestDTO
                .builder()
                .name("Some name")
                .build();
        when(heroRepository.save(any(Hero.class))).thenReturn(hero);
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userService.getUser()).thenReturn(user);

        HeroResponseDTO heroResponseDTO = heroService.createHero(heroRequestDTO);

        Assertions.assertThat(heroResponseDTO).isNotNull();
        Assertions.assertThat(heroResponseDTO.getName()).isEqualTo("Some name");
    }

    @Test
    public void heroService_showMyHero_HeroResponseDTO(){
        //
        User user = User
                .builder()
                .password("1234")
                .firstname("test")
                .lastname("test")
                .role(Role.USER)
                .username("test")
                .build();
        Hero hero = new Hero("Some name");
        hero.setUser(user);
        user.setHero(hero);

        when(userService.getUser()).thenReturn(user);

        HeroResponseDTO heroResponseDTO = heroService.showMyHero();

        Assertions.assertThat(heroResponseDTO).isNotNull();
        Assertions.assertThat(heroResponseDTO.getName()).isEqualTo("Some name");
    }
}
