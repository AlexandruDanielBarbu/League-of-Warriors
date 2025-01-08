package Common;

import Interfaces.Battle;
import Interfaces.Element;
import Interfaces.Visitor;
import Spells.Earth;
import Spells.Fire;
import Spells.Ice;
import Spells.Spell;

import java.util.ArrayList;

abstract public class Entity implements Battle, Element<Entity> {
    //region CONSTANTS
    public final int MAX_HP = 100;
    public final int MAX_MANA = 100;

    public final int NORMAL_DAMAGE = 20;
    public final int NORMAL_DAMAGE_COST = 10;

    protected final int DAMAGE_REDUCTION = 5;
    protected final int DAMAGE_BUFF = 5;
    public final int MANA_REDUCTION = 5;
    //endregion

    //region Abilities of the entity
    protected ArrayList<Spell> abilities = new ArrayList<Spell>();

    public ArrayList<Spell> getAbilities() {
        return abilities;
    }

    public void setAbilities(ArrayList<Spell> abilities) {
        this.abilities = abilities;
    }
    //endregion

    //region HP and MANA
    protected boolean isAlive = true;
    protected int currentHP = MAX_HP;
    protected int currentMana = MAX_MANA;

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(int newHP) {
        currentHP = newHP;
    }

    public int getCurrentMana() {
        return currentMana;
    }

    public void setCurrentMana(int newMana) {
        if (newMana < 0) currentMana = 0;
        if (newMana > MAX_MANA) currentMana = MAX_MANA;
        else currentMana = newMana;
    }

    public void healthRegen(int hp) {
        currentHP += hp;

        if (currentHP > MAX_HP) {
            currentHP = MAX_HP;
        }
    }

    public void manaRegen(int mana) {
        currentMana += mana;

        if (currentMana > MAX_MANA) {
            currentMana = MAX_MANA;
        }
    }
    //endregion

    //region Immunities
    protected boolean fireImmunity = false;
    protected boolean iceImmunity = false;
    protected boolean earthImmunity = false;

    public boolean isFireImmunity() {
        return fireImmunity;
    }

    public boolean isIceImmunity() {
        return iceImmunity;
    }

    public boolean isEarthImmunity() {
        return earthImmunity;
    }
    //endregion

    public boolean canUseAbility(Spell ability, Entity enemy) {
        if (ability == null) {
            if (currentMana >= NORMAL_DAMAGE_COST) {
                return true;
            } else return false;
        }

        boolean canUse = true;

        if ((currentMana < ability.getManaCost()) ||
            (ability instanceof Ice && enemy.iceImmunity) ||
            (ability instanceof Earth && enemy.earthImmunity) ||
            (ability instanceof Fire && enemy.fireImmunity) ||
            (enemy.getCurrentHP() <= 0)) {
            canUse = false;
        }

        return canUse;
    }

    public void accept(Visitor<Entity> visitor) {
        visitor.visit(this);
    }
}
