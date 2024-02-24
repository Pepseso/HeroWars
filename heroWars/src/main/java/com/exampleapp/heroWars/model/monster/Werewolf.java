package com.exampleapp.heroWars.model.monster;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;

@Entity(name = "werewolf")
@DiscriminatorValue("werewolf")
@Getter
@Builder
public class Werewolf extends Monster{
    static final int WEREWOLF_BASE_DAMAGE = 2;

    public Werewolf() {
        this.initialize(1);
    }

    @Override
    public int dealDamage() {
        return this.getLevel() * WEREWOLF_BASE_DAMAGE;
    }

    @Override
    public void receiveDamage(int damage) {
        int currentHp = this.getHp();
        this.setHp(currentHp - damage);
    }

    @Override
    public String getBattleRoar() {
        return "Howl of the Night!";
    }

    @Override
    public String getDescription() {
        return "Werewolves are mythical creatures cursed with the ability to shapeshift " +
                "between human and wolf forms. Typically associated with the full moon " +
                "and lycanthropy, werewolves are depicted as fearsome hunters with enhanced senses, " +
                "strength, and agility in their wolf form. In folklore and modern media, werewolves " +
                "are often portrayed as tragic figures struggling to control their inner beast and reconcile " +
                "their human and animal instincts.";
    }
}
