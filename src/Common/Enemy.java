package Common;

import Spells.Earth;
import Spells.Fire;
import Spells.Ice;

import java.util.Random;

public class Enemy extends Entity {
    public Enemy(int hp, int mana) {
        currentHP = hp;
        currentMana = mana;

        Random rand = new Random();
        int value = rand.nextInt(3);
        switch (value) {
            case 0 -> fireImmunity = true;
            case 1 -> earthImmunity = true;
            case 2 -> iceImmunity = true;
            default -> {
            }
        }

        abilities.add(new Earth());
        abilities.add(new Fire());
        abilities.add(new Ice());
    }

    public Enemy(){
        this(100, 100);
    }

    @Override
    public void receiveDamage(int damage) {
        currentHP -= damage;
        if (currentHP <= 0) {
            System.out.println("Enemy defeated!");
            isAlive = false;
        }
    }

    @Override
    public int getDamage() {
        return NORMAL_DAMAGE;
    }

    @Override
    public String toString() {
        return "Enemy: Immunity [Fire,Earth,Ice]: " + "[" + fireImmunity + earthImmunity + iceImmunity + "]";
    }
}
