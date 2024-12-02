package Characters;

public class Warrior extends Character {

    public Warrior(String name, int experience, int level) {
        this.name = name;
        this.experience = experience;
        this.level = level;
    }

    @Override
    public void receiveDamage(int damage) {

    }

    @Override
    public int getDamage() {
        return 0;
    }

}
