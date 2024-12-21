package Characters;

import Enums.CharacterType;

public class CharacterFactory {
    public static Character buildCharacter(CharacterType type, String name, int experience, int level) {
        switch (type) {
            case MAGE -> {
                return new Mage(name, experience, level);
            }
            case WARRIOR -> {
                return new Warrior(name, experience, level);
            }
            case ROGUE -> {
                return new Rogue(name, experience, level);
            }
            default -> {
                return null;
            }
        }
    }
}
