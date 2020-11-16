import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This class describes the tile object, which are the slots that make up the game
 * Initializes some varaibles
 * Includes a setRandom function that randomly sets the color and shape of the tiles
 * Includes a toString function to print the tile in a string format
 * @param color defines the color of the tile
 * @param shape defines the shape of the tile
 */
class Tile implements Serializable {
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
    public void setRandom(int color, int shape) {
        Random rnd = new Random();
        color = rnd.nextInt(5);
        shape = rnd.nextInt(2);
    }
    
    @Override
    public String toString() {
        return String.format("%d %d", color, shape);
    }
}
/**
 * This class defines the panel, which displays the tiles to the screen
 * Adds tiles to the ArrayList of tiles 
 * Goes through the list and prints the correct tile
 */
class TilePanel extends JPanel implements MouseListener {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private ArrayList<Tile> tiles;
    public ArrayList<Tile> getTiles() {
        return tiles;
    }
    public void setTiles(ArrayList<Tile> tiles) {
        this.tiles = tiles;
    }
    public TilePanel() {
        tiles = new ArrayList<Tile>();
        tiles.add(new Tile(0,0));
        tiles.add(new Tile(0,0));
        tiles.add(new Tile(0,0));
        tiles.add(new Tile(0,0));
        addMouseListener(this);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        tiles.get(0).setRandom(0, 1);
        repaint();
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
/**
 * This class writes the tiles to the file type desired by the user
 * Suporrts .txt, .bin, and .xml
 */
class TileWriter {
    public boolean writeToText(String fname, ArrayList<Tile> tiles) {
        File f = new File(fname);
        return writeToText(f,tiles);
    }
    public boolean writeToText(File f, ArrayList<Tile> tiles) {
        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(f)));
            for (Tile tile : tiles) {
                pw.println(tile);
            }
            pw.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public boolean writeToBinary(String fname, ArrayList<Tile> tiles) {
        File f = new File(fname);
        return writeToBinary(f, tiles);
    }
    public boolean writeToBinary(File f, ArrayList<Tile> tiles) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
            oos.writeObject(tiles);
            oos.close();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    public boolean writeToXML(String fname, ArrayList<Tile> tiles) {
        File f = new File(fname);
        return writeToXML(f, tiles);
    }
    public boolean writeToXML(File f, ArrayList<Tile> tiles) {
        try {
            XMLEncoder enc = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(f)));
            enc.writeObject(tiles);
            enc.close();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean write(String fname, ArrayList<Tile> tiles) {
        File f = new File(fname);
        return write(f, tiles);
    }
    public boolean write(File f, ArrayList<Tile> tiles) {
        String fname = f.getName().toUpperCase();
        if (fname.endsWith(".TXT")) {
            return writeToText(f, tiles);
        }
        if (fname.endsWith(".BIN")) {
            return writeToBinary(f, tiles);
        }
        if (fname.endsWith(".XML")) {
            return writeToXML(f, tiles);
        }
        return false;
    }
}
/**
 * This class read files from the user and loads them to the panel
 * Supports .txt, .bin, .xml
 */
class TileReader {
    public ArrayList<Tile> readFromText(String fname) {
        File f = new File(fname);
        return readFromText(f);
    }
    public ArrayList<Tile> readFromText(File f) {
        try {
            ArrayList<Tile> result = new ArrayList<Tile>();
            Scanner fsc = new Scanner(f);
            String line;
            String[] parts;
            int color, shape;
            Tile tile;
            while (fsc.hasNextLine()) {
                line = fsc.nextLine().trim();
                if (line.length() > 0) {
                    parts = line.split(" ");
                    color = Integer.parseInt(parts[0]);
                    shape = Integer.parseInt(parts[1]);
                    tile = new Tile(color, shape);
                    result.add(tile);
                }
            }
            fsc.close();
            return result; 
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public ArrayList<Tile> readFromBinary(String fname) {
        File f = new File(fname);
        return readFromBinary(f);
    }
    public ArrayList<Tile> readFromBinary(File f) {
        try {
            ArrayList<Tile> tilesRead;
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
            tilesRead = (ArrayList<Tile>)ois.readObject();
            ois.close();
            return tilesRead;
        } catch (Exception ex) {
            return null;
        }
    }
    public ArrayList<Tile> readFromXML(String fname) {
        File f = new File(fname);
        return readFromXML(f);
    }
    public ArrayList<Tile> readFromXML(File f) {
        try {
            ArrayList<Tile> tilesRead;
            XMLDecoder dec = new XMLDecoder(new BufferedInputStream(new FileInputStream(f)));
            tilesRead = (ArrayList<Tile>)dec.readObject();
            dec.close();
            return tilesRead;
        } catch (Exception ex) {
            return null;
        }
    }

    public ArrayList<Tile> read(String fname) {
        File f = new File(fname);
        return read(f);
    }
    public ArrayList<Tile> read(File f) {
        String fname = f.getName().toUpperCase();
        if (fname.endsWith(".TXT")) {
            return readFromText(f);
        }
        if (fname.endsWith(".BIN")) {
            return readFromBinary(f);
        }
        if (fname.endsWith(".XML")) {
            return readFromXML(f);
        }
        return null;
    }
}
/**
 * This class sets up the frame for the panel
 * The menu contains:
 * File- Load Tiles, Save Tiles, Print, Restart, and Exit
 * Help- About
 * Also sets up the buttons and wagering text field
 * Buttons: Max, Mid, and Min
 */
class SlotMachineFrame extends JFrame {
    /**
    	 *
    	 */
    private static final long serialVersionUID = 1L;

    private TilePanel pan;
	public void setupMenu() {
		JMenuBar mbar = new JMenuBar();
		JMenu mnuFile = new JMenu("File");
		JMenuItem miLoad = new JMenuItem("Load Tiles");
		miLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                TileReader tr = new TileReader();
                JFileChooser jfc = new JFileChooser();
                if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    ArrayList<Tile> tilesRead = tr.readFromText(jfc.getSelectedFile());
                    if (tilesRead == null) {
                        JOptionPane.showMessageDialog(null, "Could not read tiles from file.");
                    } else {
                        pan.setTiles(tilesRead);
                        repaint();
                    }
                }
            }
        });
        JMenuItem miSave = new JMenuItem("Save Tiles");
        miSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser();
                TileWriter tw = new TileWriter();
                if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    if (tw.write(jfc.getSelectedFile(), pan.getTiles()) == true) {
                        JOptionPane.showMessageDialog(null, "Wrote tiles to file.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to write tiles to file.");
                    }
                }   
            }
        });
        JMenuItem miPrint = new JMenuItem("Print");
        JMenuItem miRestart = new JMenuItem("Restart");
		JMenuItem miExit = new JMenuItem("Exit");
		miExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
        mnuFile.add(miLoad);
        mnuFile.add(miSave);
        mnuFile.add(miPrint);
        mnuFile.add(miRestart);
		mnuFile.add(miExit);
		mbar.add(mnuFile);
		JMenu mnuHelp = new JMenu("Help");
		JMenuItem miAbout = new JMenuItem("About");
		miAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Application created by Sebastian Bigos\nGithub Project Link: https://github.com/sbigos/bigos_sebastian_cpsc24500/blob/master/VegasBaby.java");
			}
		});
		mnuHelp.add(miAbout);
		mbar.add(mnuHelp);
		setJMenuBar(mbar);
    }
    public void setupLook() {
        setTitle("Vegas Baby!");
        setBounds(100,100,800,400);
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        pan = new TilePanel();
        c.add(pan,BorderLayout.CENTER);
        JPanel panSouth = new JPanel();
        panSouth.setLayout(new FlowLayout());
        JButton btnMax = new JButton("Max");
        panSouth.add(btnMax);
        JButton btnMid= new JButton("Mid");
        panSouth.add(btnMid);
        JButton btnMin = new JButton("Min");
        panSouth.add(btnMin);
        panSouth.add(new JLabel("$"));
        JTextField txtMoney = new JTextField(6);
        panSouth.add(txtMoney);
        c.add(panSouth,BorderLayout.SOUTH);
        setupMenu();
    }
    public SlotMachineFrame() {
        setupLook();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
/**
 * The main function that runs all the code
 */
public class VegasBaby {
    public static void main(String[] args) {
        SlotMachineFrame frm = new SlotMachineFrame();
        frm.setVisible(true);
    }
}
