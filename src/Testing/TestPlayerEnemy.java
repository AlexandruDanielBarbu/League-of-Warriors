import Characters.Mage;
import Characters.Rogue;
import Characters.Warrior;
import Common.Enemy;
import GameMap.Grid;

public class TestPlayerEnemy {
    public static void main(String[] args) {
        Warrior warrior = new Warrior("Kratos", 1,1);
        Rogue rogue = new Rogue("Roger", 1,1);
        Mage mage = new Mage("Mage", 1,1);

        Enemy enemy = new Enemy(100, 100);

        System.out.println(warrior);
        System.out.println(rogue);
        System.out.println(mage);
        System.out.println(enemy);

        Grid grid = Grid.createRandomGrid(warrior, null);

        grid.battleEnemy(enemy);
    }
}
