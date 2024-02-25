package com.exampleapp.heroWars.repository;

import com.exampleapp.heroWars.model.Hero;
import com.exampleapp.heroWars.model.Role;
import com.exampleapp.heroWars.model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void UserRepository_saveAll_ReturnSavedUser() {
        //Arrange
        User user = User
                .builder()
                .username("user1")
                .role(Role.USER)
                .firstname("Test")
                .lastname("user")
                .password("1234")
                .build();
        //Act
        User savedUser = userRepository.save(user);
        //Assert
        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void UserRepository_findAll_ReturnAllUsers() {
        //Arrange
        User user1 = User
                .builder()
                .username("user1")
                .role(Role.USER)
                .firstname("Test1")
                .lastname("user1")
                .password("1234")
                .build();
        User user2 = User
                .builder()
                .username("user2")
                .role(Role.USER)
                .firstname("Test2")
                .lastname("user2")
                .password("1234")
                .build();
        //Act
        userRepository.save(user1);
        userRepository.save(user2);
        List<User> listOfUsers = userRepository.findAll();
        //Assert
        Assertions.assertThat(listOfUsers.size()).isGreaterThan(1);
        Assertions.assertThat(listOfUsers.size()).isEqualTo(2);
    }

    @Test
    public void UserRepository_FindByID_ReturnUserNotNull() {
        //Arrange
        User user = User
                .builder()
                .username("user1")
                .role(Role.USER)
                .firstname("Test")
                .lastname("user")
                .password("1234")
                .build();
        //Act
        userRepository.save(user);
        User hero = userRepository.findById(user.getId()).get();
        //Assert
        Assertions.assertThat(hero).isNotNull();
    }

    @Test
    public void userRepository_FindUserByUsername_ReturnUserNotNull() {
        //Arrange
        User user = User
                .builder()
                .username("user1")
                .role(Role.USER)
                .firstname("Test")
                .lastname("user")
                .password("1234")
                .build();
        //Act
        userRepository.save(user);
        User foudedUser = userRepository.findByUsername("user1").get();
        //Assert
        Assertions.assertThat(foudedUser).isNotNull();
    }

    @Test
    public void userRepository_UpdateUser_ReturnValuesMatches() {
        //Arrange
        User user = User
                .builder()
                .username("user1")
                .role(Role.USER)
                .firstname("Test")
                .lastname("user")
                .password("1234")
                .build();
        //Act
        userRepository.save(user);

        User userSaved = userRepository.findById(user.getId()).get();
        userSaved.setUsername("user2");
        userSaved.setFirstname("test2");

        User updatedUser = userRepository.save(userSaved);

        //Assert
        Assertions.assertThat(updatedUser.getUsername()).isEqualTo("user2");
        Assertions.assertThat(updatedUser.getFirstname()).isEqualTo("test2");
    }

    @Test
    public void userRepository_DeleteUser_ReturnEmptyOptional() {
        //Arrange
        User user = User
                .builder()
                .username("user1")
                .role(Role.USER)
                .firstname("Test")
                .lastname("user")
                .password("1234")
                .build();
        //Act
        userRepository.save(user);

        userRepository.deleteById(user.getId());

        Optional<User> heroReturn = userRepository.findById(user.getId());

        //Assert
        Assertions.assertThat(heroReturn).isEmpty();
    }
}
