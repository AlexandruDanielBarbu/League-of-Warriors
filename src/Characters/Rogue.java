package Characters;

import Enums.GameState;
import Spells.Earth;
import Spells.Fire;
import Spells.Ice;
import Common.Game;

public class Rogue extends Character {
    public Rogue(String name, int experience, int level) {
        this.name = name;
        this.experience = experience;
        this.level = level;
        earthImmunity = true;

        dexterity = MAX_STAT;

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
            System.out.println("You lose Rogue... Common.Game Over!");
            isAlive = false;
            Game.getInstance().setGameState(GameState.FINISHED_BAD);
        }
    }

    @Override
    public int getDamage() {
        return NORMAL_DAMAGE + (strength / 5) * DAMAGE_BUFF;
    }
}
