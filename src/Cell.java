import Enums.CellEntityType;

public class Cell {
    private int ox;
    private int oy;
    private CellEntityType cellType;
    private boolean visited = false;

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}
