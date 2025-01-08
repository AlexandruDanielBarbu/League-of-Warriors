package Characters;

import Common.Entity;
import Enums.Skills;

import java.util.ArrayList;

abstract public class Character extends Entity {
    protected final int MAX_STAT = 10;

    //region Details

    protected String name;
    protected int experience;
    protected int level;

    public String getName() {
        return name;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    //endregion

    //region Special attributes
    protected int strength = 0;
    protected int charisma = 0;
    protected int dexterity = 0;

    public ArrayList<Integer> getAttributes() {
        ArrayList<Integer> attributes = new ArrayList<>();
        attributes.add(strength);
        attributes.add(charisma);
        attributes.add(dexterity);

        return attributes;
    }

    public boolean addSkillPoints(int points, Skills skill) {
        switch (skill) {
            case STRENGTH -> {
                if (strength == MAX_STAT) {
                    return false;
                }
                strength += points;
                if (strength > MAX_STAT) {
                    strength = MAX_STAT;
                }
            }
            case DEXTERITY -> {
                if (dexterity == MAX_STAT) {
                    return false;
                }
                dexterity += points;
                if (dexterity > MAX_STAT) {
                    dexterity = MAX_STAT;
                }
            }
            case CHARISMA -> {
                if (charisma == MAX_STAT) {
                    return false;
                }
                charisma += points;
                if (charisma > MAX_STAT) {
                    charisma = MAX_STAT;
                }
            }
        }
        return true;
    }

    public int specialManaCost(int manaCost){
        int newManaCost = manaCost - MANA_REDUCTION * (charisma / 5);
        if (newManaCost < 0){ newManaCost = 3; }
        return newManaCost;
    }
    //endregion

    @Override
    public String toString() {
        return "Name: " + name +
               " Experience: " + experience +
               " Level: " + level +
               " Strength: " + strength +
               " Profession " + getClass() +
               " Immunity [Fire,Earth,Ice]: " + "[" + fireImmunity + earthImmunity + iceImmunity + "]";
    }

}
