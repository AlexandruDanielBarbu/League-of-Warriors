import GameMap.Grid;

public class TestGrid {
    public static void main(String[] args) {
        Grid map = Grid.createRandomGrid(null, null);
        Grid map2 = Grid.createHardcodedGrid(null, null);

        System.out.println("Random Map");
        System.out.println(map.toString());

        System.out.println("Random Map Revealed");
        System.out.println(map.toString(true));

        System.out.println("Hardcoded Map");
        System.out.println(map2.toString());

        System.out.println("Hardcoded Map Revealed");
        System.out.println(map2.toString(true));

    }
}
