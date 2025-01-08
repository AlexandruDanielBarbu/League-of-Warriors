package Spells;

import Common.Entity;

import java.util.Random;

public class Ice extends Spell {
    public Ice() {
        Random rand = new Random();
        damage = rand.nextInt(MAX_DAMAGE - MIN_DAMAGE) + MIN_DAMAGE;
        manaCost = rand.nextInt(MAX_MANA_COST - MIN_MANA_COST) + MIN_MANA_COST;
    }

    @Override
    public void visit(Entity entity) {
        if (entity.isFireImmunity()) {
            entity.receiveDamage(2 * damage);
        } else {
            entity.receiveDamage(damage);
        }
    }
}
