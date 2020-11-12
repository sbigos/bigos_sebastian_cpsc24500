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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

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

class Tile {
    private int x;
    private int y;
    private int radius;
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getRadius() {
        return radius;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setRadius(int rad) {
        if (rad < 0) {
            radius = 1;
        } else {
            radius = rad;
        }
    }
    public Tile() {
        x = -1;
        y = -1;
        radius = 1;
    }
    public Tile(int x, int y, int rad) {
        setX(x);
        setY(y);
        setRadius(rad);
    }
/*    public int setRandom(int color, int shape) {
        Random rnd = new Random();
        color = rnd.nextInt(4);
        shape = rnd.nextInt(1);
        return color;
       }
*/    
    @Override
    public String toString() {
        return String.format("%d %d %d", x, y, radius);
    }
}

class TilePanel extends JPanel {
    private ArrayList<Tile> tiles;
    public ArrayList<Tile> getTiles() {
        return tiles;
    }
    public void setTiles(ArrayList<Tile> tiles) {
        this.tiles = tiles;
    }
    public TilePanel() {
        tiles = new ArrayList<Tile>();
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.YELLOW);
        g.fillRect(20, 100, 100, 100);
        g.fillOval(200, 100, 100, 100);
        g.setColor(Color.BLUE);
        g.fillRect(400, 100, 100, 100);
        g.fillOval(600, 100, 100, 100);
    }
}

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
            return false;
        }
    }
}

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
//		miClear.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {

        JMenuItem miSave = new JMenuItem("Save Tiles");
        miSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser();
                TileWriter tw = new TileWriter();
                if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    if (tw.writeToText(jfc.getSelectedFile(), pan.getTiles()) == true) {
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
				JOptionPane.showMessageDialog(null, "Application created by Sebastian Bigos\n");
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

public class VegasBaby {
    public static void main(String[] args) {
        SlotMachineFrame frm = new SlotMachineFrame();
        frm.setVisible(true);
    }
}
