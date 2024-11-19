import java.util.ArrayList;

public class Grid extends ArrayList<ArrayList<Cell>> {

    private int width;
    private int length;
    private Character currentCharacter;
    private Cell characterCell;

    private Grid(int width, int length) {
        this.width = width;
        this.length = length;
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
