package GameMap;

import Account.Account;
import Characters.Character;
import Common.Enemy;
import Common.Game;
import CustomExceptions.InvalidPlayerMove;
import Enums.BattleStatus;
import Enums.CellEntityType;
import Enums.GameState;
import Enums.Skills;
import Spells.Spell;

import java.util.ArrayList;
import java.util.Random;

public class Grid extends ArrayList<ArrayList<Cell>> {
    //region Constants
    static final int MAX_GRID_SIZE = 10;
    static final int MIN_GRID_SIZE = 5;

    static final int MIN_SANCTUARY_COUNT = 2;
    static final int MIN_ENEMY_COUNT = 4;

    static final int XP_MULTIPLIER = 5;
    //endregion

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    private int width;
    private int length;
    private Account account;
    private Character playerCharacter;
    private Cell characterCell = null;

    public Grid instance;

    //region Grid Generation
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

    public static Grid createRandomGrid(Character currentCharacter, Account account) {
        int sanctuaryCount = 0;
        int enemyCount = 0;

        // generate empty map
        Grid gameMap = new Grid(currentCharacter, account);
        for (int i = 0; i < gameMap.width; i++ ) {
            gameMap.add(new ArrayList<Cell>());

            for (int j = 0; j < gameMap.length; j++ ) {
                gameMap.get(i).add(new Cell(j, i, CellEntityType.VOID));
            }
        }

        // add player and portal
        gameMap.characterCell = gameMap.get(0).get(0);
        gameMap.characterCell.setVisited(true);
        gameMap.get(gameMap.width - 1).get(gameMap.length - 1).setCellType(CellEntityType.PORTAL);

        // add enemies
        while (enemyCount < MIN_ENEMY_COUNT) {
            Random rand = new Random();
            int y = rand.nextInt(gameMap.width);
            int x = rand.nextInt(gameMap.length);

            Cell cell = gameMap.get(y).get(x);
            if (cell.getCellType() == CellEntityType.VOID &&
                cell != gameMap.get(0).get(0) &&
                cell != gameMap.get(gameMap.width - 1).get(gameMap.length - 1)) {
                cell.setCellType(CellEntityType.ENEMY);
                enemyCount++;
            }
        }

        // add sanctuaries
        while (sanctuaryCount < MIN_SANCTUARY_COUNT) {
            Random rand = new Random();
            int y = rand.nextInt(gameMap.width);
            int x = rand.nextInt(gameMap.length);
            Cell cell = gameMap.get(y).get(x);
            if (cell.getCellType() == CellEntityType.VOID &&
                cell != gameMap.get(0).get(0) &&
                cell != gameMap.get(gameMap.width - 1).get(gameMap.length - 1)) {
                cell.setCellType(CellEntityType.SANCTUARY);
                sanctuaryCount++;
            }
        }

        sanctuaryCount--;
        enemyCount--;

        // fill the void spaces
        for (int i = 0; i < gameMap.width; i++ ) {
            for (int j = 0; j < gameMap.length; j++ ) {

                if ((i == 0 && j == 0) ||
                    (i == gameMap.width - 1 && j == gameMap.length - 1)) {
                    continue;
                }

                Random rand = new Random();
                int choice = rand.nextInt(4);

                switch (choice) {
                    case 0 -> {
                        gameMap.get(i).get(j).setCellType(CellEntityType.ENEMY);
                        enemyCount++;
                    }
                    case 1 -> {
                        gameMap.get(i).get(j).setCellType(CellEntityType.SANCTUARY);
                        sanctuaryCount++;
                    }
                    default -> {
                    }
                }
            }
        }

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
    //endregion

    private void cellAction(Cell cell) {
        if (!cell.isVisited()) {
            Random rand = new Random();

            switch (cell.getCellType()) {
                case VOID -> {
                    // do nothing;
                    Game.getInstance().getAchievements().get(0).setCompleted(true);
                }
                case SANCTUARY -> {
                    Game.getInstance().getAchievements().get(1).setCompleted(true);
                    // add random to life and mana to player
                    int hp = rand.nextInt(playerCharacter.MAX_HP);
                    int mana = rand.nextInt(playerCharacter.MAX_MANA);
                    playerCharacter.healthRegen(hp);
                    playerCharacter.manaRegen(mana);
                    System.out.println("Player received " + hp + " HP, " + mana + " Mana.");
                }
                case PORTAL -> {
                    playerCharacter.setLevel(playerCharacter.getLevel() + 1);
                    playerCharacter.setExperience(playerCharacter.getExperience() + XP_MULTIPLIER * playerCharacter.getLevel());
                    account.setNoPlayedGames(account.getNoPlayedGames() + 1);
                    Game.getInstance().setGameState(GameState.FINISHED_GOOD);
                    playerCharacter.addSkillPoints(1, Skills.CHARISMA);
                    playerCharacter.addSkillPoints(1, Skills.STRENGTH);
                    playerCharacter.addSkillPoints(1, Skills.DEXTERITY);
                    if (mapDiscovered()){
                        Game.getInstance().getAchievements().get(2).setCompleted(true);
                    }
                }
                case ENEMY -> {
                    // enemy encounter
                    System.out.println("Battling enemy");
//                    Enemy enemy = new Enemy();
//                    battleEnemy(enemy);
                    // Battle enemy inside UI
                }
                default -> {
                    // nothing here
                }
            }
        }
    }

    private boolean mapDiscovered(){
        boolean discovered= true;
        for (int i = 0; i < width; i++){
            for (int j = 0; j < length; j++){
                if (!get(i).get(j).isVisited()) {
                    discovered = false;
                    break;
                }
            }
        }
        return discovered;
    }

    //region Player - Enemy battle system
    static public BattleStatus playerTurn(Character playerCharacter, Enemy enemy, Spell spell) {
        if (playerCharacter.isAlive()) {
            if (spell == null) {
                System.out.println("Normal attack.\n");
                if (playerCharacter.canUseAbility(null, enemy)) {
                    enemy.receiveDamage(playerCharacter.getDamage());
                }
            } else {
                System.out.println("Spell attack.\n");
                if (playerCharacter.canUseAbility(spell, enemy)) {
                    enemy.accept(spell);
                    playerCharacter.setCurrentMana(playerCharacter.getCurrentMana() - playerCharacter.specialManaCost(spell.getDamage()));
                }
            }
            return enemyTurn(playerCharacter, enemy);
        } else {
            // player is no longer alive
            return BattleStatus.BATTLE_FINISHED_BAD;
        }
    }

    static public BattleStatus enemyTurn(Character playerCharacter, Enemy enemy) {
        if (enemy.isAlive()) {
            System.out.println("Enemy turn");

            Random rand = new Random();
            int choice = rand.nextInt(4);

            if (enemy.getCurrentMana() <= enemy.MAX_MANA / 10) {
                choice = 0;
            }

            System.out.println("choice of enemy: " + choice + "\n");

            if (choice == 0){
                if (enemy.canUseAbility(null, playerCharacter)){
                    playerCharacter.receiveDamage(enemy.getDamage());
                }
            } else {
                Spell spell = enemy.getAbilities().get(choice - 1);
                if (enemy.canUseAbility(spell, playerCharacter)){
                    playerCharacter.accept(spell);
                    enemy.setCurrentMana((enemy.getCurrentMana()) - spell.getDamage());
                }
            }
            if (!playerCharacter.isAlive())
                return BattleStatus.BATTLE_FINISHED_BAD;
            return BattleStatus.BATTLE_CONTINUE;
        } else {
            return BattleStatus.BATTLE_FINISHED_GOOD;
        }
    }
    //endregion


    public void battleEnemy(Enemy enemy){
        int turn = 0;
        while (playerCharacter.isAlive() && enemy.isAlive()) {
            System.out.println("Your health: " + playerCharacter.getCurrentHP() + " Your mana: " + playerCharacter.getCurrentMana()
                               + "\nEnemy health: " + enemy.getCurrentHP() + " Enemy mana: " + enemy.getCurrentMana());

            if (turn % 2 == 0){
                System.out.println("Player turn...");
                System.out.println("0. Normal attack Damage: " + playerCharacter.NORMAL_DAMAGE);
                int index = 1;
                for(Spell spell : playerCharacter.getAbilities()){
                    System.out.println(index + ". " + spell);
                    index++;
                }
                System.out.print("\nChoose attack: ");
                int choice = -1;
                while (choice == -1) {
                    try {
                        choice = Integer.parseInt(System.console().readLine());
                        if (choice < 0 || choice - 1 >= playerCharacter.getAbilities().size()) {
                            choice = -1;
                            System.out.println("Invalid choice");
                        }
                    } catch (Exception e) {
                        choice = -1;
                        System.out.println("Invalid choice");
                    }

                }

                if (choice == 0){
                    System.out.println("Normal attack.\n");
                    if(playerCharacter.canUseAbility(null, enemy)){
                        enemy.receiveDamage(playerCharacter.getDamage());
//                        playerCharacter.setCurrentMana(playerCharacter.getCurrentMana() - playerCharacter.specialManaCost(playerCharacter.NORMAL_DAMAGE_COST));
                    }
                } else {
                    if (choice - 1 <= playerCharacter.getAbilities().size() - 1){
                        System.out.println("You selected: [" + choice + ". " + playerCharacter.getAbilities().get(choice - 1) + "]\n");

                        Spell spell = playerCharacter.getAbilities().get(choice -1);
                        if (playerCharacter.canUseAbility(spell, enemy)){
                            enemy.accept(spell);
                            playerCharacter.setCurrentMana(playerCharacter.getCurrentMana() - playerCharacter.specialManaCost(spell.getDamage()));
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
//                        enemy.setCurrentMana(enemy.getCurrentMana() - enemy.NORMAL_DAMAGE_COST);
                    }
                } else {
                    Spell spell = enemy.getAbilities().get(choice - 1);
                    if (enemy.canUseAbility(spell, playerCharacter)){
                        playerCharacter.accept(spell);
                        enemy.setCurrentMana((enemy.getCurrentMana()) - spell.getDamage());
                    }
                }
            }

            turn++;
        }

        if (playerCharacter.isAlive()){
            Random rand = new Random();
//            int hp = rand.nextInt(playerCharacter.MAX_HP);
//            int mana = rand.nextInt(playerCharacter.MAX_MANA);
            int hp = playerCharacter.getCurrentHP()*2;
            int mana = playerCharacter.MAX_MANA;
            int xp = rand.nextInt(5) + 1;
            playerCharacter.healthRegen(playerCharacter.getCurrentHP()*2);
            playerCharacter.manaRegen(playerCharacter.MAX_MANA);
            playerCharacter.setExperience(playerCharacter.getExperience() + xp);
            System.out.println("Player received " + hp + " HP, " + mana + " Mana and " + xp  + " extra XP");
        }
    }

    public void setPlayerCharacter(Character playerCharacter) {
        this.playerCharacter = playerCharacter;
    }

    public Character getPlayerCharacter() {
        return playerCharacter;
    }

    //region Player navigation
    public void goNorth() {
        try{
            characterCell = get(characterCell.getX() - 1).get(characterCell.getY());
            cellAction(characterCell);
            Cell previouscell = get(characterCell.getX() + 1).get(characterCell.getY());
            previouscell.setVisited(true);
        } catch (Exception e) {
            throw new InvalidPlayerMove("Player cannot go north");
        }

    }

    public void goSouth() {
        try{
            characterCell = get(characterCell.getX() + 1).get(characterCell.getY());
            cellAction(characterCell);
            Cell previouscell = get(characterCell.getX() - 1).get(characterCell.getY());
            previouscell.setVisited(true);
        } catch (Exception e) {
            throw new InvalidPlayerMove("Player cannot go south");
        }
    }

    public void goWest() {
        try {
            characterCell = get(characterCell.getX()).get(characterCell.getY() - 1);
            cellAction(characterCell);
            Cell previouscell = get(characterCell.getX()).get(characterCell.getY() + 1);
            previouscell.setVisited(true);
        } catch (Exception e) {
            throw new InvalidPlayerMove("Player cannot go west");
        }
    }

    public void goEast() {
        try {
            characterCell = get(characterCell.getX()).get(characterCell.getY() + 1);
            cellAction(characterCell);
            Cell previouscell = get(characterCell.getX()).get(characterCell.getY() - 1);
            previouscell.setVisited(true);
        } catch (Exception e) {
            throw new InvalidPlayerMove("Player cannot go east");
        }
    }
    //endregion

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
