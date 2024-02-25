package com.exampleapp.heroWars.repository;

import com.exampleapp.heroWars.model.Hero;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class HeroRepositoryTest {

    @Autowired
    private HeroRepository heroRepository;

    @Test
    public void HeroRepository_saveAll_ReturnSavedHero() {
        //Arrange
        Hero hero = new Hero("someone");
        //Act
        Hero savedHero = heroRepository.save(hero);
        //Assert
        Assertions.assertThat(savedHero).isNotNull();
        Assertions.assertThat(savedHero.getId()).isGreaterThan(0);
    }
    @Test
    public void HeroRepository_findAll_ReturnAllHeroes() {
        //Arrange
        Hero hero1 = new Hero("someone1");
        Hero hero2 = new Hero("someone2");
        //Act
        heroRepository.save(hero1);
        heroRepository.save(hero2);
        List<Hero> listOfHeroes = heroRepository.findAll();
        //Assert
        Assertions.assertThat(listOfHeroes.size()).isGreaterThan(1);
        Assertions.assertThat(listOfHeroes.size()).isEqualTo(2);
    }

    @Test
    public void heroRepository_FindByID_ReturnHeroNotNull() {
        //Arrange
        Hero hero1 = new Hero("first");
        //Act
        heroRepository.save(hero1);
        Hero hero = heroRepository.findById(hero1.getId()).get();
        //Assert
        Assertions.assertThat(hero).isNotNull();
    }

    @Test
    public void heroRepository_FindHeroByName_ReturnHeroNotNull() {
        //Arrange
        Hero hero1 = new Hero("first");
        //Act
        heroRepository.save(hero1);
        Hero hero = heroRepository.findHeroByName("first").get();
        //Assert
        Assertions.assertThat(hero).isNotNull();
    }

    @Test
    public void heroRepository_UpdateHero_ReturnValuesMatches() {
        //Arrange
        Hero hero1 = new Hero("first");
        //Act
        heroRepository.save(hero1);

        Hero heroSaved = heroRepository.findById(hero1.getId()).get();
        heroSaved.setName("second");
        heroSaved.setLevel(2);

        Hero updatedHero = heroRepository.save(heroSaved);

        //Assert
        Assertions.assertThat(updatedHero.getName()).isEqualTo("second");
        Assertions.assertThat(updatedHero.getLevel()).isEqualTo(2);
    }

    @Test
    public void heroRepository_DeleteHero_ReturnEmptyOptional() {
        //Arrange
        Hero hero1 = new Hero("first");
        //Act
        heroRepository.save(hero1);

        heroRepository.deleteById(hero1.getId());

        Optional<Hero> heroReturn = heroRepository.findById(hero1.getId());

        //Assert
        Assertions.assertThat(heroReturn).isEmpty();
    }
}
