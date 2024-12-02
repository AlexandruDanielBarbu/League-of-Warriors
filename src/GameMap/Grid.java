package GameMap;

import Characters.Character;
import Enums.CellEntityType;

import java.util.ArrayList;

public class Grid extends ArrayList<ArrayList<Cell>> {

    public static Grid instance;
    private int width;
    private int length;
    private Character currentCharacter;
    private Cell characterCell;

    private Grid(int width, int length) {
        this.width = width;
        this.length = length;
        instance = this;
    }

    private Grid() {
        this (0,0);
    }

    static public Grid createGrid(int width, int length){

        // maximum 10x10 dimension of the grid
        Grid gameGrid = new Grid(width, length);
        // do some magic here
        return gameGrid;
    }

    static public Grid createGrid(){

        Grid gameGrid = new Grid(5, 5);

        for (int i = 0; i < 5; i++) {
            gameGrid.add(new ArrayList<Cell>());
        }

        gameGrid.get(0).add(new Cell(0, 0, CellEntityType.PLAYER));
        gameGrid.get(0).add(new Cell(0, 1, CellEntityType.VOID));
        gameGrid.get(0).add(new Cell(0, 2, CellEntityType.VOID));
        gameGrid.get(0).add(new Cell(0, 3, CellEntityType.SANCTUARY));
        gameGrid.get(0).add(new Cell(0, 4, CellEntityType.VOID));

        gameGrid.get(1).add(new Cell(1, 0, CellEntityType.VOID));
        gameGrid.get(1).add(new Cell(1, 1, CellEntityType.VOID));
        gameGrid.get(1).add(new Cell(1, 2, CellEntityType.VOID));
        gameGrid.get(1).add(new Cell(1, 3, CellEntityType.SANCTUARY));
        gameGrid.get(1).add(new Cell(1, 4, CellEntityType.VOID));

        gameGrid.get(2).add(new Cell(2, 0, CellEntityType.SANCTUARY));
        gameGrid.get(2).add(new Cell(2, 1, CellEntityType.VOID));
        gameGrid.get(2).add(new Cell(2, 2, CellEntityType.VOID));
        gameGrid.get(2).add(new Cell(2, 3, CellEntityType.VOID));
        gameGrid.get(2).add(new Cell(2, 4, CellEntityType.VOID));

        gameGrid.get(3).add(new Cell(3, 0, CellEntityType.VOID));
        gameGrid.get(3).add(new Cell(3, 1, CellEntityType.VOID));
        gameGrid.get(3).add(new Cell(3, 2, CellEntityType.VOID));
        gameGrid.get(3).add(new Cell(3, 3, CellEntityType.VOID));
        gameGrid.get(3).add(new Cell(3, 4, CellEntityType.ENEMY));

        gameGrid.get(4).add(new Cell(4, 0, CellEntityType.VOID));
        gameGrid.get(4).add(new Cell(4, 1, CellEntityType.VOID));
        gameGrid.get(4).add(new Cell(4, 2, CellEntityType.VOID));
        gameGrid.get(4).add(new Cell(4, 3, CellEntityType.SANCTUARY));
        gameGrid.get(4).add(new Cell(4, 4, CellEntityType.PORTAL));

        return gameGrid;
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
}
