package VegasBabyVegas;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * This class read files from the user and loads them to the panel
 * Supports .txt, .bin, .xml
 */
public class TileReader {
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
