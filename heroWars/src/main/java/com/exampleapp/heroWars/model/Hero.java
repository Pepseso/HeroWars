package com.exampleapp.heroWars.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
public class Hero {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "hp")
    private Integer hp;

    @Column(name = "experiencePoints")
    private Integer experiencePoints;

    @Column(name = "gold")
    private Integer gold;

    public Hero(String name) {
        this.name = name;
        this.hp = 100;
        this.experiencePoints = 0;
        this.gold = 500;
    }

    public Hero() {
        this.name = "Example Name"; //TODO make a list for generate random name
        this.hp = 100;
        this.experiencePoints = 0;
        this.gold = 500;
    }
}
