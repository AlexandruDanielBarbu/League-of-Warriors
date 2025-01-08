package Spells;

import Common.Entity;

import java.util.Random;

public class Earth extends Spell {
    public Earth() {
        Random rand = new Random();
        damage = rand.nextInt(MAX_DAMAGE - MIN_DAMAGE) + MIN_DAMAGE;
        manaCost = rand.nextInt(MAX_MANA_COST - MIN_MANA_COST) + MIN_MANA_COST;
    }

    @Override
    public void visit(Entity entity) {
        entity.receiveDamage(damage);
    }
}
