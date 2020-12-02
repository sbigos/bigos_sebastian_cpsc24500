package VegasBabyVegas;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
/**
 * This class writes the tiles to the file type desired by the user
 * Suporrts .txt, .bin, and .xml
 */
public class TileWriter {
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

