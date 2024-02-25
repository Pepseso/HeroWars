package com.exampleapp.heroWars.repository;

import com.exampleapp.heroWars.model.monster.Dragon;
import com.exampleapp.heroWars.model.monster.Monster;
import com.exampleapp.heroWars.model.monster.Werewolf;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class MonsterRepositoryTest {

    @Autowired
    private MonsterRepository monsterRepository;

    @Test
    public void MonsterRepository_saveAll_ReturnSavedMonster() {
        //Arrange
        Monster monster = new Werewolf();
        //Act
        Monster savedMonster = monsterRepository.save(monster);
        //Assert
        Assertions.assertThat(savedMonster).isNotNull();
        Assertions.assertThat(savedMonster.getId()).isGreaterThan(0);
    }

    @Test
    public void MonsterRepository_findById_ReturnNotNull() {
        //Arrange
        Monster monster = new Werewolf();
        //Act
        monsterRepository.save(monster);
        Monster foundMonster = monsterRepository.findById(monster.getId()).get();
        //Assert
        Assertions.assertThat(foundMonster).isNotNull();
        Assertions.assertThat(foundMonster.getId()).isGreaterThan(0);
    }

    @Test
    public void MonsterRepository_findAll_ReturnAllMonsters() {
        //Arrange
        Monster monster = new Werewolf();
        Monster monster2 = new Dragon();
        //Act
        monsterRepository.save(monster);
        monsterRepository.save(monster2);
        List<Monster> listOfMonsters = monsterRepository.findAll();
        //Assert
        Assertions.assertThat(listOfMonsters.size()).isGreaterThan(1);
        Assertions.assertThat(listOfMonsters.size()).isEqualTo(2);
    }

    @Test
    public void MonsterRepository_UpdateMonster_ReturnValuesMatches() {
        //Arrange
        Monster monster = new Werewolf();
        //Act
        Monster savedMonster = monsterRepository.save(monster);
        savedMonster.setAlive(false);
        monsterRepository.save(savedMonster);
        Monster foundMonster = monsterRepository.findById(savedMonster.getId()).get();
        //Assert
        Assertions.assertThat(foundMonster.isAlive()).isEqualTo(false);
    }

    @Test
    public void monsterRepository_DeleteMonster_ReturnEmptyOptional() {
        //Arrange
        Monster monster = new Werewolf();
        //Act
        monsterRepository.save(monster);

        monsterRepository.deleteById(monster.getId());

        Optional<Monster> heroReturn = monsterRepository.findById(monster.getId());

        //Assert
        Assertions.assertThat(heroReturn).isEmpty();
    }

}
