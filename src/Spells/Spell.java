package Spells;

abstract public class Spell {
    //region Constants
    protected final int MAX_DAMAGE = 20;
    protected final int MAX_MANA_COST = 15;
    protected final int MIN_DAMAGE = 10;
    protected final int MIN_MANA_COST = 3;
    //endregion

    protected int damage;
    protected int manaCost;

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getManaCost() {
        return manaCost;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

    public String toString(){
        return "Spell type: " + getClass() + " Damage: " + damage + " Mana cost: " + manaCost;
    }

    // more needs to be done here
}
