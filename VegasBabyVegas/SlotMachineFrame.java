package VegasBabyVegas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JFrame;
/**
 * This class sets up the frame for the panel
 * The menu contains:
 * File- Load Tiles, Save Tiles, Print, Restart, and Exit
 * Help- About
 * Also sets up the buttons and wagering text field
 * Buttons: Max, Mid, and Min
 */
public class SlotMachineFrame extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JButton btnMax, btnMid, btnMin;
    private JTextField txtMoney;
    private Random rnd;
    private float balance = 5;
    private int numColor, numShape;
    private TilePanel pan;
	public void setupMenu() {
		JMenuBar mbar = new JMenuBar();
		JMenu mnuFile = new JMenu("File");
		JMenuItem miLoad = new JMenuItem("Load Tiles");
		miLoad.addActionListener(new ActionListener() {
            //Load tiles allows you to load a file of tiles to the program
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
            //Save tiles allows you to save the tiles to a file 
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser();
                TileWriter tw = new TileWriter();
                if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    if (tw.write(jfc.getSelectedFile(), TilePanel.getTiles()) == true) {
                        JOptionPane.showMessageDialog(null, "Wrote tiles to file.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to write tiles to file.");
                    }
                }   
            }
        });
        JMenuItem miPrint = new JMenuItem("Print");
        miPrint.addActionListener(new ActionListener(){
            //Prints out the tiles to the console
            public void actionPerformed(ActionEvent e) {
                for (Tile tile : TilePanel.getTiles()) {
                    System.out.println(tile);
                }
            }
        });
        JMenuItem miRestart = new JMenuItem("Restart");
        miRestart.addActionListener(new ActionListener() {
            //Restarts the program by enabling all buttons and setting balance back to $5
            public void actionPerformed(ActionEvent e) {
                balance = 5;
                txtMoney.setText(String.format("%.2f", balance));
                btnMax.setEnabled(true);
                btnMid.setEnabled(true);
                btnMin.setEnabled(true);
            }
        });
        JMenuItem miExit = new JMenuItem("Exit");
		miExit.addActionListener(new ActionListener() {
            //Exits app
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
            //Shows info about the app
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Application created by Sebastian Bigos\nGithub Project Link: https://github.com/sbigos/bigos_sebastian_cpsc24500/blob/master/VegasBaby.java");
			}
		});
		mnuHelp.add(miAbout);
		mbar.add(mnuHelp);
		setJMenuBar(mbar);
    }
    public void setupLook() {
        //sets up the look of the app
        setTitle("Vegas Baby!");
        setBounds(100,100,800,400);
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        pan = new TilePanel();
        c.add(pan,BorderLayout.CENTER);
        JPanel panSouth = new JPanel();
        panSouth.setLayout(new FlowLayout());
        JButton btnMax = new JButton("Max");
        btnMax.addActionListener(new ActionListener() {
            //wagers the max
            public void actionPerformed(ActionEvent e) {
                ArrayList<Tile> tiles = TilePanel.getTiles();
                TileRandomizer.randomizeTiles(rnd, tiles);
                pan.repaint();
                numShape = TileChecker.checkShape(tiles);
                numColor = TileChecker.checkColor(tiles);
                if (numColor == 1) {
                    balance = balance * 25;
                } else if (numColor == 1 && numShape == 1) {
                    balance = balance * 100;
                } else {
                    balance = 0;
                }
                txtMoney.setText(String.format(".2f",balance));
            }
        });
        panSouth.add(btnMax);
        JButton btnMid= new JButton("Mid");
        btnMid.addActionListener(new ActionListener() {
            //wagers half
            public void actionPerformed(ActionEvent e) {
                ArrayList<Tile> tiles = TilePanel.getTiles();
                TileRandomizer.randomizeTiles(rnd, tiles);
                pan.repaint();
                numShape = TileChecker.checkShape(tiles);
                numColor = TileChecker.checkColor(tiles);
                if (numColor == 1) {
                    balance = balance * 10;
                } else if (numColor == 1 && numShape == 1) {
                    balance = balance * 50;
                } else {
                    balance = balance / 2;
                }
            }
        });
        panSouth.add(btnMid);
        JButton btnMin = new JButton("Min");
        panSouth.add(btnMin);
        btnMin.addActionListener(new ActionListener() {
            //wagers a tenth
            public void actionPerformed(ActionEvent e) {
                ArrayList<Tile> tiles = TilePanel.getTiles();
                TileRandomizer.randomizeTiles(rnd, tiles);
                pan.repaint();
                numShape = TileChecker.checkShape(tiles);
                numColor = TileChecker.checkColor(tiles);
                if (numColor == 1) {
                    balance = balance * 5;
                } else if (numColor == 1 && numShape == 1) {
                    balance = balance * 10;
                } else {
                    balance = balance / 10;
                }
            }
        });
        if (balance == 0) {
            btnMax.setEnabled(false);
            btnMid.setEnabled(false);
            btnMin.setEnabled(false);
        }
        panSouth.add(new JLabel("$"));
        JTextField txtMoney = new JTextField(6);
        txtMoney.setText(String.format("%.2f",balance));
        panSouth.add(txtMoney);
        c.add(panSouth,BorderLayout.SOUTH);
        setupMenu();
    }
    public SlotMachineFrame() {
        setupLook();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
