package GameMap;

import Account.Account;
import Characters.Character;
import Common.Enemy;
import Enums.CellEntityType;
import Spells.Spell;

import java.util.ArrayList;
import java.util.Random;

public class Grid extends ArrayList<ArrayList<Cell>> {
    static final int MAX_GRID_SIZE = 10;
    static final int MIN_GRID_SIZE = 5;

    static final int MAX_PORTALS_ALLOWED = 1;
    static final int MIN_SANCTUARY_COUNT = 2;
    static final int MIN_ENEMY_COUNT = 4;

    private int width;
    private int length;
    private Account account;
    private Character playerCharacter;
    private Cell characterCell = null;

    public Grid instance;

    private Grid(int width, int length, Character playerCharacter, Account account) {
        this.width = width;
        this.length = length;
        this.playerCharacter = playerCharacter;
        this.account = account;
        instance = this;

    }

    private Grid(Character playerCharacter, Account account) {
        Random rand = new Random();
        width = rand.nextInt(MAX_GRID_SIZE - MIN_GRID_SIZE) + MIN_GRID_SIZE;
        length = rand.nextInt(MAX_GRID_SIZE - MIN_GRID_SIZE) + MIN_GRID_SIZE;
        this.playerCharacter = playerCharacter;
        this.account = account;
        instance = this;
    }

    static public Grid createRandomGrid(Character currentCharacter, Account account) {
        int portalCount = 0;
        int sanctuaryCount = 0;
        int enemyCount = 0;

        Grid gameMap = new Grid(currentCharacter, account);
        for (int i = 0; i < gameMap.width; i++ ) {
            gameMap.add(new ArrayList<Cell>());

            for (int j = 0; j < gameMap.length; j++ ) {
                gameMap.get(i).add(new Cell(i, j));

                Cell cell = gameMap.get(i).get(j);
                if (cell.getCellType() == CellEntityType.PORTAL &&
                    portalCount < MAX_PORTALS_ALLOWED) {
                    portalCount++;
                } else {
                    cell.setCellType(CellEntityType.VOID);
                }

                if (cell.getCellType() == CellEntityType.SANCTUARY) {
                    sanctuaryCount++;
                }

                if (cell.getCellType() == CellEntityType.ENEMY) {
                    enemyCount++;
                }

                if (cell.getCellType() == CellEntityType.VOID &&
                    gameMap.characterCell == null) {
                    gameMap.characterCell = cell;
                    cell.setVisited(true);
                }
            }
        }

        if (portalCount < MAX_PORTALS_ALLOWED) {
            for (int i = 0; i < gameMap.width; i++ ) {
                for (int j = 0; j < gameMap.length; j++ ) {
                    if (gameMap.get(i).get(j).getCellType() == CellEntityType.VOID &&
                            portalCount < MAX_PORTALS_ALLOWED) {
                        portalCount++;
                        gameMap.get(i).get(j).setCellType(CellEntityType.PORTAL);
                    }
                }
            }

        }

        if (sanctuaryCount < MIN_SANCTUARY_COUNT) {
            for (int i = 0; i < gameMap.width; i++ ) {
                for (int j = 0; j < gameMap.length; j++ ) {
                    if (gameMap.get(i).get(j).getCellType() == CellEntityType.VOID &&
                        sanctuaryCount < MIN_SANCTUARY_COUNT) {
                        sanctuaryCount++;
                        gameMap.get(i).get(j).setCellType(CellEntityType.SANCTUARY);
                    }
                }
            }
        }

        if (enemyCount < MIN_ENEMY_COUNT) {
            for (int i = 0; i < gameMap.width; i++ ) {
                for (int j = 0; j < gameMap.length; j++ ) {
                    if (gameMap.get(i).get(j).getCellType() == CellEntityType.VOID &&
                        enemyCount < MIN_ENEMY_COUNT) {
                        enemyCount++;
                        gameMap.get(i).get(j).setCellType(CellEntityType.ENEMY);
                    }
                }
            }
        }

        if (gameMap.characterCell == null) {
            for (int i = 0; i < gameMap.width; i++) {
                for (int j = 0; j < gameMap.length; j++) {
                    if (gameMap.get(i).get(j).getCellType() == CellEntityType.VOID) {
                        gameMap.characterCell = gameMap.get(i).get(j);
                    }
                }
            }

            if (gameMap.characterCell == null) {
                if (sanctuaryCount - 1 >= MIN_SANCTUARY_COUNT) {
                    sanctuaryCount--;
                    for (int i = 0; i < gameMap.width; i++) {
                        for (int j = 0; j < gameMap.length; j++) {
                            if (gameMap.get(i).get(j).getCellType() == CellEntityType.SANCTUARY) {
                                gameMap.characterCell = gameMap.get(i).get(j);
                            }
                        }
                    }

                }
            }

            if (gameMap.characterCell == null) {
                for (int i = 0; i < gameMap.width; i++) {
                    for (int j = 0; j < gameMap.length; j++) {
                        if (gameMap.get(i).get(j).getCellType() == CellEntityType.ENEMY) {
                            gameMap.characterCell = gameMap.get(i).get(j);
                        }
                    }
                }
            }
        }

        gameMap.characterCell.setVisited(true);
        return gameMap;
    }

    public static Grid createHardcodedGrid(Character currentCharacter, Account account) {

        Grid gameMap = new Grid(5, 5, currentCharacter, account);

        for (int i = 0; i < 5; i++) {
            gameMap.add(new ArrayList<Cell>());
        }

        gameMap.get(0).add(new Cell(0, 0, CellEntityType.VOID));
        gameMap.get(0).add(new Cell(0, 1, CellEntityType.VOID));
        gameMap.get(0).add(new Cell(0, 2, CellEntityType.VOID));
        gameMap.get(0).add(new Cell(0, 3, CellEntityType.SANCTUARY));
        gameMap.get(0).add(new Cell(0, 4, CellEntityType.VOID));

        gameMap.get(1).add(new Cell(1, 0, CellEntityType.VOID));
        gameMap.get(1).add(new Cell(1, 1, CellEntityType.VOID));
        gameMap.get(1).add(new Cell(1, 2, CellEntityType.VOID));
        gameMap.get(1).add(new Cell(1, 3, CellEntityType.SANCTUARY));
        gameMap.get(1).add(new Cell(1, 4, CellEntityType.VOID));

        gameMap.get(2).add(new Cell(2, 0, CellEntityType.SANCTUARY));
        gameMap.get(2).add(new Cell(2, 1, CellEntityType.VOID));
        gameMap.get(2).add(new Cell(2, 2, CellEntityType.VOID));
        gameMap.get(2).add(new Cell(2, 3, CellEntityType.VOID));
        gameMap.get(2).add(new Cell(2, 4, CellEntityType.VOID));

        gameMap.get(3).add(new Cell(3, 0, CellEntityType.VOID));
        gameMap.get(3).add(new Cell(3, 1, CellEntityType.VOID));
        gameMap.get(3).add(new Cell(3, 2, CellEntityType.VOID));
        gameMap.get(3).add(new Cell(3, 3, CellEntityType.VOID));
        gameMap.get(3).add(new Cell(3, 4, CellEntityType.ENEMY));

        gameMap.get(4).add(new Cell(4, 0, CellEntityType.VOID));
        gameMap.get(4).add(new Cell(4, 1, CellEntityType.VOID));
        gameMap.get(4).add(new Cell(4, 2, CellEntityType.VOID));
        gameMap.get(4).add(new Cell(4, 3, CellEntityType.SANCTUARY));
        gameMap.get(4).add(new Cell(4, 4, CellEntityType.PORTAL));

        gameMap.characterCell = gameMap.get(0).get(0);
        gameMap.characterCell.setVisited(true);

        return gameMap;
    }

    private void cellAction(Cell cell) {
        final int XP_MULTIPLIER = 5;

        if (!cell.isVisited()) {
            Random rand = new Random();

            switch (cell.getCellType()) {
                case VOID -> {
                    // do nothing;
                }
                case SANCTUARY -> {
                    // add random to life and mana to player
                    playerCharacter.healthRegen(rand.nextInt(playerCharacter.MAX_HP));
                    playerCharacter.manaRegen(rand.nextInt(playerCharacter.MAX_MANA));
                }
                case PORTAL -> {
                    playerCharacter.setLevel(playerCharacter.getLevel() + 1);
                    playerCharacter.setExperience(playerCharacter.getExperience() + XP_MULTIPLIER * playerCharacter.getLevel());
                    account.setNoPlayedGames(account.getNoPlayedGames() + 1);
                }
                case ENEMY ->
                    // enemy encounter
                        System.out.println("Battling enemy");
                default -> {
                }
                // nothing here
            }
        }
    }

    public void battleEnemy(Enemy enemy){
        int turn = 0;
        while (playerCharacter.isAlive() && enemy.isAlive()) {
            System.out.println("Your health: " + playerCharacter.getCurrentHP() + " Your mana: " + playerCharacter.getCurrentMana()
                               + "\nEnemy health: " + enemy.getCurrentHP() + " Enemy mana: " + enemy.getCurrentMana());

            if (turn % 2 == 0){
                System.out.println("Player turn...");
                System.out.println("0. Normal attack Damage: " + playerCharacter.NORMAL_DAMAGE + " Mana cost: " + playerCharacter.NORMAL_DAMAGE_COST);
                int index = 1;
                for(Spell spell : playerCharacter.getAbilities()){
                    System.out.println(index + ". " + spell);
                    index++;
                }
                System.out.print("\nChoose attack: ");
                int choice = Integer.parseInt(System.console().readLine());

                if (choice == 0){
                    System.out.println("Normal attack.\n");
                    if(playerCharacter.canUseAbility(null, enemy)){
                        enemy.receiveDamage(playerCharacter.getDamage());
                        playerCharacter.setCurrentMana(playerCharacter.getCurrentMana() - playerCharacter.NORMAL_DAMAGE_COST);
                    }
                } else {
                    if (choice - 1 <= playerCharacter.getAbilities().size() - 1){
                        System.out.println("You selected: [" + choice + ". " + playerCharacter.getAbilities().get(choice - 1) + "]\n");

                        Spell spell = playerCharacter.getAbilities().get(choice -1);
                        if (playerCharacter.canUseAbility(spell, enemy)){
                            enemy.receiveDamage(spell.getDamage());
                            playerCharacter.setCurrentMana(playerCharacter.getCurrentMana() - spell.getDamage());
                        }
                    }
                }
            } else {
                System.out.println("Enemy turn");

                Random rand = new Random();
                int choice = rand.nextInt(4);
                System.out.println("choice of enemy: " + choice + "\n");

                if (choice == 0){
                    if (enemy.canUseAbility(null, playerCharacter)){
                        playerCharacter.receiveDamage(enemy.getDamage());
                        enemy.setCurrentMana(enemy.getCurrentMana() - enemy.NORMAL_DAMAGE_COST);
                    }
                } else {
                    Spell spell = enemy.getAbilities().get(choice - 1);
                    if (enemy.canUseAbility(spell, playerCharacter)){
                        playerCharacter.receiveDamage(spell.getDamage());
                        enemy.setCurrentMana((enemy.getCurrentMana()) - spell.getDamage());
                    }
                }
            }

            turn++;
        }
    }

    public void setPlayerCharacter(Character playerCharacter) {
        this.playerCharacter = playerCharacter;
    }

    public Character getPlayerCharacter() {
        return playerCharacter;
    }

    // if player cannot move an exception is thrown and Game class handles it
    public void goNorth() {
        characterCell = get(characterCell.getX() - 1).get(characterCell.getY());
        characterCell.setVisited(true);
        cellAction(characterCell);
    }

    public void goSouth() {
        characterCell = get(characterCell.getX() + 1).get(characterCell.getY());
        characterCell.setVisited(true);
        cellAction(characterCell);
    }

    public void goWest() {
        characterCell = get(characterCell.getX()).get(characterCell.getY() - 1);
        characterCell.setVisited(true);
        cellAction(characterCell);
    }

    public void goEast() {
        characterCell = get(characterCell.getX()).get(characterCell.getY() + 1);
        characterCell.setVisited(true);
        cellAction(characterCell);
    }


    @Override
    public String toString() {
        String output = "";

        for (int i = 0; i < width; i++ ) {
            for (int j = 0; j < length; j++ ) {
                Cell cell = get(i).get(j);
                if (cell.isVisited()) {
                    if (cell == characterCell) {
                        output += "P ";
                    } else {
                        output += cell.getCellType().toString().charAt(0) + " ";
                    }
                } else {
                    output += "* ";
                }
            }
            output += "\n";
        }
        return output;
    }


    public String toString(boolean reveal) {
        String output = "";

        for (int i = 0; i < width; i++ ) {
            for (int j = 0; j < length; j++ ) {
                Cell cell = get(i).get(j);
                if (cell == characterCell) {
                    output += "P ";
                } else {
                    output += cell.getCellType().toString().charAt(0) + " ";
                }
            }
            output += "\n";
        }
        return output;
    }

    public Cell getCharacterCell() {
        return characterCell;
    }
}
