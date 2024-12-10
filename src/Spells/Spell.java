package Spells;

abstract public class Spell {
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
