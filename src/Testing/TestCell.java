import Enums.CellEntityType;
import GameMap.Cell;

/**
 * Tests if a cell is correctly created and if cellType can be modified.
 * */
public class TestCell {
    public static void main(String[] args) {
        Cell cell = new Cell(0,0);
        Cell cell2 = new Cell(0,1);
        Cell cell3 = new Cell(0,2);

        System.out.println(cell);
        System.out.println(cell2);
        System.out.println(cell3);

        cell.overwriteCellType(CellEntityType.values()[0]);
        cell2.overwriteCellType(CellEntityType.values()[1]);
        cell3.overwriteCellType(CellEntityType.values()[2]);

        System.out.println(cell);
        System.out.println(cell2);
        System.out.println(cell3);
    }
}
