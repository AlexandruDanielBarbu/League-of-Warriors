import Enums.CellEntityType;
import GameMap.Cell;

/**
 * Tests if a cell is correctly created and if cellType can be modified.
 * */
public class TestCell {
    public static void main(String[] args) {
        // generate random cells
        Cell cell = new Cell(0,0);
        Cell cell2 = new Cell(0,1);
        Cell cell3 = new Cell(0,2);
        System.out.println(cell);
        System.out.println(cell2);
        System.out.println(cell3);

        //generate forced cells
        Cell cell4 = new Cell(1,0, CellEntityType.values()[0]);
        Cell cell5 = new Cell(1,1, CellEntityType.values()[1]);
        Cell cell6 = new Cell(1,2, CellEntityType.values()[2]);
        System.out.println(cell4);
        System.out.println(cell5);
        System.out.println(cell6);
    }
}
