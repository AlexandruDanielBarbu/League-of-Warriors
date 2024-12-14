import Characters.Warrior;
import Enums.Skills;

public class TestPlayer {
    public static void main(String[] args) {
        Warrior warrior = new Warrior("Alex", 0, 1);

        //region HP and MANA manipulation
        System.out.println("Print player params");
        System.out.printf("Player HP: %s\nPlayer MANA: %s%n", warrior.getCurrentHP(), warrior.getCurrentMana());


        System.out.println("Print player after force set to 1");
        warrior.setCurrentHP(1);
        warrior.setCurrentMana(1);
        System.out.printf("Player HP: %s\nPlayer MANA: %s%n", warrior.getCurrentHP(), warrior.getCurrentMana());


        System.out.println("Print player after regens");
        warrior.healthRegen(warrior.getCurrentHP()*2); // double the health + previous health
        warrior.manaRegen(warrior.MAX_MANA); // max mana
        System.out.printf("Player HP: %s\nPlayer MANA: %s%n", warrior.getCurrentHP(), warrior.getCurrentMana());


        System.out.println("Print player after full regens");
        warrior.healthRegen(warrior.MAX_HP); // full health
        System.out.printf("Player HP: %s\nPlayer MANA: %s%n", warrior.getCurrentHP(), warrior.getCurrentMana());
        //endregion


        //region Attributes manipulation
        System.out.println("Player gets 10 points to charisma");
        System.out.println(warrior.getAttributes());

        warrior.addSkillPoints(1, Skills.CHARISMA);
        System.out.println(warrior.getAttributes());

        warrior.addSkillPoints(2, Skills.CHARISMA);
        System.out.println(warrior.getAttributes());

        warrior.addSkillPoints(3, Skills.CHARISMA);
        System.out.println(warrior.getAttributes());

        warrior.addSkillPoints(4, Skills.CHARISMA);
        System.out.println(warrior.getAttributes());

        System.out.println("Overadd to akill, 10 is max");
        warrior.addSkillPoints(5, Skills.CHARISMA);
        System.out.println(warrior.getAttributes());
        //endregion
    }
}
