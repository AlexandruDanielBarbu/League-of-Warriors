package Spells;

import java.util.Random;

public class Fire extends Spell {
    public Fire() {
        Random rand = new Random();
        damage = rand.nextInt(MAX_DAMAGE - MIN_DAMAGE) + MIN_DAMAGE;
        manaCost = rand.nextInt(MAX_MANA_COST - MIN_MANA_COST) + MIN_MANA_COST;
    }
}
