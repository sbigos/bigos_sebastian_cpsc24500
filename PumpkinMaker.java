import javax.swing.JFrame; //all of my imports
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;

class PumpkinPanel extends JPanel { 
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private int pumpkinLeft; //all of my private variables initialized
    private int pumpkinTop;
    private int pumpkinWidth;
    private int pumpkinHeight;
    private String pumpkinEye;
    private String pumpkinNose;
    private String pumpkinMouth;

    public void setPumpkinLeft(int l) { //all of my get and set functions for each variable
        if (l < 100) {
            pumpkinLeft = 100;
        } else {
            pumpkinLeft = l;
        }
    }
    public int getPumpkinLeft() {
        return pumpkinLeft;
    }
    public void setPumpkinTop(int t) {
        if (t < 100) {
            pumpkinTop = 100;
        } else {
            pumpkinTop = t;
        }
    }
    public int getPumpkinTop() {
        return pumpkinTop;
    }
    public void setPumpkinWidth(int w) {
        if (w < 100) {
            pumpkinWidth = 100;
        } else {
            pumpkinWidth = w;
        }
    } 
    public int getPumkinWidth() {
        return pumpkinWidth;
    }
    public void setPumpkinHeight(int h) {
        if (h < 100) {
            pumpkinHeight = 100;
        } else {
            pumpkinHeight = h;
        }
    }
    public int getPumpkinHeight() {
        return pumpkinHeight;
    }
    public void setPumpkinEye(String e) {
        if (!e.equalsIgnoreCase("C") && !e.equalsIgnoreCase("S") && !e.equalsIgnoreCase("T")) {
            pumpkinEye = "C";
        } else {
            pumpkinEye = e;
        }
    }
    public String getPumpkinEye() {
        return pumpkinEye;
    }
    public void setPumpkinNose(String n) {
        if (!n.equalsIgnoreCase("C") && !n.equalsIgnoreCase("S") && !n.equalsIgnoreCase("T")) {
            pumpkinNose = "S";
        } else {
            pumpkinNose = n;
        }
    }
    public String getPumpkinNose() {
        return pumpkinNose;
    }
    public void setPumpkinMouth(String m) {
        if (!m.equalsIgnoreCase("O") && !m.equalsIgnoreCase("R")) {
            pumpkinMouth = "O";
        } else {
            pumpkinMouth = m;
        }
    }
    public String getPumpkinMouth() {
        return pumpkinMouth;
    }
    public PumpkinPanel() { //default constructor that is the initial pumpkin
        pumpkinLeft = 200;
        pumpkinTop = 100;
        pumpkinWidth = 100;
        pumpkinHeight = 100;
        pumpkinEye = "C";
        pumpkinNose = "S";
        pumpkinMouth = "O";
    }
    public PumpkinPanel(int l, int t, int w, int h, String e, String n, String m) {
        pumpkinLeft = l; //constructor that uses the variables that user enters for new pumpkin
        pumpkinTop = t;
        pumpkinWidth = w;
        pumpkinHeight = h;
        pumpkinEye = e;
        pumpkinNose = n;
        pumpkinMouth = m;
    }
    @Override
    public void paintComponent(Graphics g) {//paint function
        super.paintComponent(g);
        g.setColor(Color.ORANGE); //draws orange pumpkin
        g.fillOval(pumpkinLeft,pumpkinTop,pumpkinWidth,pumpkinHeight);
        g.setColor(Color.WHITE); //draws the stem on top 
        g.fillRect(pumpkinLeft+((pumpkinWidth/2)-(pumpkinWidth/12)),pumpkinTop-(pumpkinHeight/6),pumpkinWidth/6,pumpkinHeight/6);
        

        if (pumpkinEye.equalsIgnoreCase("C")) { //draws eyes
            g.fillOval(pumpkinLeft * 10/8,pumpkinTop * 9/8,pumpkinWidth * 2/8,pumpkinHeight * 1/8);
            g.fillOval(pumpkinLeft * 13/8,pumpkinTop * 9/8,pumpkinWidth * 2/8,pumpkinHeight * 1/8);
        } else if (pumpkinEye.equalsIgnoreCase("S")) {
            g.fillRect(pumpkinLeft * 10/8,pumpkinTop * 9/8,pumpkinWidth * 2/8,pumpkinHeight * 1/8);
            g.fillRect(pumpkinLeft * 13/8,pumpkinTop * 9/8,pumpkinWidth * 2/8,pumpkinHeight * 1/8);
        } else if (pumpkinEye.equalsIgnoreCase("T")) {
            
        }
        if (pumpkinNose.equalsIgnoreCase("S")) { //draws nose
            g.fillRect(pumpkinLeft * 12/8,pumpkinTop * 11/8,pumpkinWidth * 1/8, pumpkinHeight * 1/8);
        } else if (pumpkinNose.equalsIgnoreCase("C")) {
            g.fillOval(pumpkinLeft * 12/8,pumpkinTop * 11/8,pumpkinWidth * 1/8, pumpkinHeight * 1/8);
        } else if (pumpkinNose.equalsIgnoreCase("T")) {

        }
        if (pumpkinMouth.equalsIgnoreCase("O")) { //draws mouth
            g.fillOval(pumpkinLeft * 10/8,pumpkinTop * 13/8,pumpkinWidth * 5/8, pumpkinHeight *1/8);
       } else if (pumpkinMouth.equalsIgnoreCase("R")) {
            g.fillRect(pumpkinLeft * 10/8,pumpkinTop * 13/8,pumpkinWidth * 5/8, pumpkinHeight *1/8);
       }
    }
}

class PumpkinMakerFrame extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public void centerFrame() { //function to center frame on screen
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dim = tk.getScreenSize();
        int screenWidth = (int)dim.getWidth();
        int screenHeight = (int)dim.getHeight();
        int frameWidth = 800;
        int frameHeight = 480;
        int left = (screenWidth-frameWidth)/2;
        int top = (screenHeight-frameHeight)/2;
        setBounds(left,top,frameWidth,frameHeight);
    }

    public void setupLook() {//sets up the frame, adds all the labels, text fields, and button
        setTitle("Pumpkin Maker");
        centerFrame();
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        PumpkinPanel panCenter = new PumpkinPanel();
        panCenter.setPumpkinLeft(100);
        c.add(panCenter,BorderLayout.CENTER);
        JPanel panSouth = new JPanel();
        panSouth.setLayout(new FlowLayout());
        JLabel lblLeft = new JLabel("Left: ");
        panSouth.add(lblLeft);
        JTextField fieldLeft = new JTextField(4);
        panSouth.add(fieldLeft);
        JLabel lblTop = new JLabel("Top: ");
        panSouth.add(lblTop);
        JTextField fieldTop = new JTextField(4);
        panSouth.add(fieldTop);
        JLabel lblWidth = new JLabel("Width: ");
        panSouth.add(lblWidth);
        JTextField fieldWidth = new JTextField(4);
        panSouth.add(fieldWidth);
        JLabel lblHeight = new JLabel("Height: ");
        panSouth.add(lblHeight);
        JTextField fieldHeight = new JTextField(4);
        panSouth.add(fieldHeight);
        JLabel lblEye = new JLabel("Eye (C S T): ");
        panSouth.add(lblEye);
        JTextField fieldEye = new JTextField(2);
        panSouth.add(fieldEye);
        JLabel lblNose = new JLabel("Nose (C S T): ");
        panSouth.add(lblNose);
        JTextField fieldNose = new JTextField(2);
        panSouth.add(fieldNose);
        JLabel lblMouth = new JLabel("Mouth (O R): ");
        panSouth.add(lblMouth);
        JTextField fieldMouth = new JTextField(2);
        panSouth.add(fieldMouth);
        JButton btnDraw = new JButton("Draw");
        btnDraw.addActionListener( new ActionListener() { //gets all the variables the user enters when button is pressed
            public void actionPerformed(ActionEvent e) {
                try { //tries to get the imput but shows error message if its not an int for the pumpkin bounds
                    panCenter.setPumpkinLeft(Integer.parseInt(fieldLeft.getText()));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: Enter a whole number for left.");
                    panCenter.setPumpkinLeft(200); 
                }
                try {
                    panCenter.setPumpkinTop(Integer.parseInt(fieldTop.getText()));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: Enter a whole number for Top.");
                    panCenter.setPumpkinTop(100); 
                }
                try {
                    panCenter.setPumpkinWidth(Integer.parseInt(fieldWidth.getText()));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: Enter a whole number for Width.");
                    panCenter.setPumpkinWidth(100);
                }
                try {
                    panCenter.setPumpkinHeight(Integer.parseInt(fieldHeight.getText()));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: Enter a whole number for Height");
                    panCenter.setPumpkinHeight(100);
                }
                
                panCenter.setPumpkinEye(fieldEye.getText());
                panCenter.setPumpkinNose(fieldNose.getText());
                panCenter.setPumpkinMouth(fieldMouth.getText());
                repaint();
            }
        }
        );
        panSouth.add(btnDraw);
        c.add(panSouth,BorderLayout.SOUTH);
    }
    public PumpkinMakerFrame() {//the entire frame
        setupLook();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
public class PumpkinMaker {
    public static void main(String[] args) {//opens the frame so user can use it
        PumpkinMakerFrame frm = new PumpkinMakerFrame();
        frm.setVisible(true);
    }
}
