package com.exampleapp.heroWars.model.monster;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;

@Entity(name = "dragon")
@DiscriminatorValue("dragon")
@Getter
@Builder
public class Dragon extends Monster{

    static final int DRAGON_BASE_DAMAGE = 5;

    public Dragon() {
        this.initialize(1);
    }

    @Override
    public int dealDamage() {
        return this.getLevel() * DRAGON_BASE_DAMAGE;
    }

    @Override
    public void receiveDamage(int damage) {
        int currentHp = this.getHp();
        this.setHp(currentHp - damage);
    }

    @Override
    public String getBattleRoar() {
        return "Roar of Flames!";
    }

    @Override
    public String getDescription() {
        return "Dragons are legendary creatures of immense power and majesty, often " +
                "depicted as large, reptilian beasts with bat-like wings and the ability " +
                "to breathe fire. They are central figures in many mythologies and fantasy " +
                "stories, revered for their intelligence, strength, and hoarding instinct. " +
                "Dragons are often portrayed as both fearsome adversaries and wise mentors, " +
                "with their lairs filled with vast treasures.";
    }
}
