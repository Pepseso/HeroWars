package com.exampleapp.heroWars.model.monster;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "monster_type")
@Getter
@Setter
public abstract class Monster extends BaseEntity{

    private int hp;
    private int level;
    private boolean alive = true;
    private  int goldAmount;

    public Monster() {

    }

    public abstract int dealDamage();
    public abstract void receiveDamage(int damage);
    public abstract String getBattleRoar();
    public abstract String getDescription();

    public void initialize(int level){
        Random random = new Random();
        this.level = level;
        this.hp = level * random.nextInt(10) +1;
        this.alive = true;
        this.goldAmount = level * random.nextInt(30) + 1;
    }
}
