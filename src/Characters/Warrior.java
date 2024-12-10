package Characters;

import Common.Game;
import Enums.GameState;
import Spells.Earth;
import Spells.Fire;
import Spells.Ice;

public class Warrior extends Character {

    public Warrior(String name, int experience, int level) {
        this.name = name;
        this.experience = experience;
        this.level = level;
        fireImmunity = true;

        strength = MAX_STAT;

        abilities.add(new Earth());
        abilities.add(new Fire());
        abilities.add(new Ice());
    }

    @Override
    public void receiveDamage(int damage) {
        int newDamage = damage - DAMAGE_REDUCTION * (dexterity / 5);
        if (newDamage <= 0) {
            newDamage = 1;
        }

        currentHP -= newDamage;
        if (currentHP <= 0) {
            System.out.println("You lose Warrior... Common.Game Over!");
            isAlive = false;
            Game.getInstance().setGameState(GameState.FINISHED_BAD);

            // signal game over
        }
    }

    @Override
    public int getDamage() {
        return NORMAL_DAMAGE + (strength / 5) * DAMAGE_BUFF;
    }

}
