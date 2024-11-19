import Interfaces.Battle;

import java.util.ArrayList;

abstract public class Entity implements Battle {
    ArrayList abilities = new ArrayList();

    int currentHP;
    int maxHP;

    int currentMana;
    int maxMana;

    boolean fireImmunity = false;
    boolean iceImmunity = false;
    boolean earthImmunity = false;

    public void healthRegen(int hp) {
        currentHP = (currentHP + hp) % maxHP;
    }

    public void manaRegen(int mana) {
        currentMana = (currentMana + mana) % maxMana;
    }

    public void useAbility(String ability, Enemy currentEnemy) {
        // complicated function that does a lot
    }
}
