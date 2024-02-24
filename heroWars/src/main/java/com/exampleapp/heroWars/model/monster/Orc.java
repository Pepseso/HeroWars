package com.exampleapp.heroWars.model.monster;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;

@Entity(name = "orc")
@DiscriminatorValue("orc")
@Getter
@Builder
public class Orc extends Monster{
    static final int ORC_BASE_DAMAGE = 3;

    public Orc() {
        this.initialize(1);
    }

    @Override
    public int dealDamage() {
        return this.getLevel() * ORC_BASE_DAMAGE;
    }

    @Override
    public void receiveDamage(int damage) {
        int currentHp = this.getHp();
        this.setHp(currentHp - damage);
    }

    @Override
    public String getBattleRoar() {
        return "For the Horde!";
    }

    @Override
    public String getDescription() {
        return "Orcs are a brutish and aggressive humanoid race often depicted in fantasy " +
                "literature and games. They are characterized by their muscular build, greenish " +
                "or grayish skin, and prominent tusks. Orcs are known for their fierce warrior culture, " +
                "often organized into warbands or tribes, and are frequently portrayed as enemies of other " +
                "humanoid races like humans, elves, and dwarves.";
    }
}
