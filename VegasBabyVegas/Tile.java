package VegasBabyVegas;

import java.io.Serializable;
import java.util.Random;
/**
 * This class describes the tile object, which are the slots that make up the game
 * Initializes some varaibles
 * Includes a setRandom function that randomly sets the color and shape of the tiles
 * Includes a toString function to print the tile in a string format
 * @param color defines the color of the tile
 * @param shape defines the shape of the tile
 */
public class Tile implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int shape;
    private int color;
    public int getShape() {
        return shape;
    }
    public int getColor() {
        return color;
    }
    public void setColor(int color) {
        this.color = color;
    }
    public void setShape(int shape) {
        this.shape = shape;
    }
    public Tile() {
        color = 0;
        shape = 0;
    }
    public Tile(int color, int shape) {
        setColor(color);
        setShape(shape);
    }
    public void setRandom(Random rnd) {
        color = rnd.nextInt(5);
        shape = rnd.nextInt(2);
    }
    
    @Override
    public String toString() {
        return String.format("%d %d", color, shape);
    }
}

