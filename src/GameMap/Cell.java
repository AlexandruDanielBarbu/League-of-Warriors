package GameMap;

import Enums.CellEntityType;

import java.util.Random;

public class Cell {
    private int x;
    private int y;
    private CellEntityType cellType;
    private boolean visited = false;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;

        Random rand = new Random();
        cellType = CellEntityType.values()[rand.nextInt(CellEntityType.values().length)];
    }

    public Cell(int x, int y, CellEntityType cellType) {
        this.x = x;
        this.y = y;
        this.cellType =cellType;
    }

    public void setCellType(CellEntityType cellType) {
        this.cellType = cellType;
    }

    public CellEntityType getCellType() {
        return cellType;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + "), Type: " + cellType + ", Visited: " + visited;
    }
}
