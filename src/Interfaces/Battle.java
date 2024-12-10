package Interfaces;

public interface Battle {
    // entity is damageg, loosing life in the process
    public void receiveDamage(int damage);

    // calculate the damage the entity does
    public int getDamage();
}
