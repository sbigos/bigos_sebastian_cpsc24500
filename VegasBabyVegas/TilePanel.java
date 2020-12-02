package VegasBabyVegas;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Graphics;
/**
 * This class defines the panel, which displays the tiles to the screen
 * Adds tiles to the ArrayList of tiles 
 * Goes through the list and prints the correct tile
 * @param Random rnd to use the random function
 * @param ArrayList<Tile> tiles to use it to print to screen
 */
public class TilePanel extends JPanel implements MouseListener {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Random rnd;
    private static ArrayList<Tile> tiles;

    public static ArrayList<Tile> getTiles() {
        return tiles;
    }
    public void setTiles(ArrayList<Tile> tiles) {
        TilePanel.tiles = tiles;
    }
    //i used the solution code for this, to print the tiles to the screen randomly
    public TilePanel() {
        tiles = new ArrayList<Tile>();
        Tile tile;
        rnd = new Random();
        for (int i=0; i<4; i++) {
            tile = new Tile();
            tile.setRandom(rnd);
            tiles.add(tile);
        }
        addMouseListener(this);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    @Override
    public void mousePressed(MouseEvent e) {
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Tile tile : tiles) {
            if (tile.getColor() == 0) {
                g.setColor(Color.YELLOW);
            } else if (tile.getColor() == 1) {
                g.setColor(Color.GREEN);
            } else if (tile.getColor() == 2) {
                g.setColor(Color.ORANGE);
            } else if (tile.getColor() == 3) {
                g.setColor(Color.RED);
            } else if (tile.getColor() == 4) {
                g.setColor(Color.BLUE);
            }
            if (tiles.indexOf(tile) == 0) {
                if (tile.getShape() == 0) {
                    g.fillOval(20, 100, 100, 100);
            } else {
                g.fillRect(20, 100, 100, 100);
            }
            } else if (tiles.indexOf(tile) == 1) {
                if (tile.getShape() == 0) {
                    g.fillOval(200, 100, 100, 100);
            } else {
                g.fillRect(200, 100, 100, 100);
            }
            } else if (tiles.indexOf(tile) == 2) {
                if (tile.getShape() == 0) {
                    g.fillOval(400, 100, 100, 100);
            } else {
                g.fillRect(400, 100, 100, 100);
            } 
            } else {
                if (tile.getShape() == 0) {
                    g.fillOval(600, 100, 100, 100);
                } else {
                    g.fillRect(600, 100, 100 ,100);
                }
            }
        }
    }
}

