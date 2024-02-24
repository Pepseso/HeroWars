package com.exampleapp.heroWars.repository;

import com.exampleapp.heroWars.model.monster.Monster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MonsterRepository extends JpaRepository<Monster, Integer> {

    Optional<Monster> findById(int id);

}
