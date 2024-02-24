package com.exampleapp.heroWars.repository;

import com.exampleapp.heroWars.model.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Integer> {

    Optional<Hero> findHeroByName (String name);

}
