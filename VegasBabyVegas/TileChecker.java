package VegasBabyVegas;

import java.util.ArrayList;
/**
 * This class containts 2 functions:
 * checkColor: checks if the colors are the same for all the tiles
 * checkShape: checks if the shapes are the same for all the tiles
 * Both return a 1 if they are all the same and 0 if not
 */
public class TileChecker {
    private static int shape;
    private static int color;
    private static ArrayList<Integer> shapes;
    private static ArrayList<Integer> colors;

    public static int checkColor(ArrayList<Tile> tiles) {
        for (Tile tile : tiles) {
            color = tile.getColor();
            colors.add(color);
        }
        if (colors.get(0) != colors.get(1)) {
            color = 0;
        } else if (colors.get(0) == colors.get(1) && colors.get(0) == colors.get(2) && colors.get(0) == colors.get(3)) {
            color = 1;
        }
        return color;
    }

    public static int checkShape(ArrayList<Tile> tiles) {
        for (Tile tile : tiles) {
            shape = tile.getShape();
            shapes.add(shape);
        }
        if (shapes.get(0) != shapes.get(1)) {
            shape = 0;
        } else if (shapes.get(0) == shapes.get(1) && shapes.get(0) == shapes.get(2) && shapes.get(0) == shapes.get(3)) {
            shape = 1;
        }
        return shape;
    }
}
