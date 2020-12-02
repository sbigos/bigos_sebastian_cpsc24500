package VegasBabyVegas;

import java.util.ArrayList;
import java.util.Random;
/**
 * This class randomizes the tiles in the ArrayList of tiles
 */
public class TileRandomizer { 
    public static ArrayList<Tile> randomizeTiles(Random rnd, ArrayList<Tile> tiles) {
        rnd = new Random();
        for (Tile tile : tiles) {
            tile.setRandom(rnd);
        }
        return tiles;
    }
}
