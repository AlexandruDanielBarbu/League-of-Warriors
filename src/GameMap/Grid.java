package GameMap;

import Characters.Character;
import Enums.CellEntityType;

import java.util.ArrayList;
import java.util.Random;

public class Grid extends ArrayList<ArrayList<Cell>> {
    static final int MAX_GRID_SIZE = 10;
    static final int MIN_GRID_SIZE = 5;

    private int width;
    private int length;
    private Character currentCharacter;
    private Cell characterCell = null;

    public Grid instance;

    private Grid(int width, int length, Character currentCharacter) {
        this.width = width;
        this.length = length;
        this.currentCharacter = currentCharacter;
        instance = this;
    }

    private Grid(Character currentCharacter) {
        Random rand = new Random();
        width = rand.nextInt(MAX_GRID_SIZE - MIN_GRID_SIZE) + MIN_GRID_SIZE;
        length = rand.nextInt(MAX_GRID_SIZE - MIN_GRID_SIZE) + MIN_GRID_SIZE;
        this.currentCharacter = currentCharacter;
        instance = this;
    }

    static public Grid createRandomGrid(Character currentCharacter) {
        Grid gameMap = new Grid(currentCharacter);
        for (int i = 0; i < gameMap.width; i++ ) {
            gameMap.add(new ArrayList<Cell>());

            for (int j = 0; j < gameMap.length; j++ ) {
                gameMap.get(i).add(new Cell(i, j));
                Cell cell = gameMap.get(i).get(j);
                if (cell.getCellType() == CellEntityType.VOID &&
                    gameMap.characterCell == null) {
                    gameMap.characterCell = cell;
                    cell.setVisited(true);
                }
            }
        }
        return gameMap;
    }

    public static Grid createHarcodedGrid(Character currentCharacter) {

        Grid gameMap = new Grid(5, 5, currentCharacter);

        for (int i = 0; i < 5; i++) {
            gameMap.add(new ArrayList<Cell>());
        }

        gameMap.get(0).add(new Cell(0, 0, CellEntityType.PLAYER));
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

        return gameMap;
    }

    // if player cannot move an exception is thrown and Game class handles it
    void goNorth() {

    }

    void goSouth() {

    }

    void goWest() {

    }

    void goEast() {

    }

    @Override
    public String toString() {
        String output = "";

        for (int i = 0; i < width; i++ ) {
            for (int j = 0; j < length; j++ ) {
                Cell cell = get(i).get(j);
                if (cell.isVisited()) {
                    output += cell.getCellType().toString().charAt(0) + " ";
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
                output += cell.getCellType().toString().charAt(0) + " ";
            }
            output += "\n";
        }
        return output;
    }
}
