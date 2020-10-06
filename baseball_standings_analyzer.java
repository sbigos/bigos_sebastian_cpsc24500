import java.util.Scanner; //imported all my tools
import java.util.ArrayList;
import java.io.*;
public class baseball_standings_analyzer {
    public static void printHeader() { //function that prints the header
        System.out.println("*****************************************");
        System.out.println("*      BASEBALL STANDINGS ANALYZER      *");
        System.out.println("*****************************************");
    }
    
    public static int showMenuGetChoice(Scanner sc) {
        int choice; //function that shows user the menu and gets the choice
        do {
            try {
                System.out.println("Which standings would you like to see? ");
                System.out.println("1. AL East");
                System.out.println("2. AL Central");
                System.out.println("3. AL West");
                System.out.println("4. NL East");
                System.out.println("5. NL Central");
                System.out.println("6. NL West");
                System.out.println("7. Overall");
                System.out.println("8. Exit");
                System.out.println("Enter the number of your choice: ");
                choice = sc.nextInt();
                if (choice < 0 || choice > 8) {
                }
            } catch (Exception ex) { //repeats if enters non-number between 1-8
                    System.out.println("You must enter a number between 1 and 8.");
                    sc.nextLine();
                    choice = 0;
            }
        } while (choice < 0 || choice > 8);
        return choice;
    }

    public static double getWinPct(String line) { //function that gets win %
        String[] parts = line.split("\t");
        double wins = Double.parseDouble(parts[1]);
        double losses = Double.parseDouble(parts[2]);
        double winPct = wins / (wins + losses);
        return winPct; 
    }
/* attempted games behind function but couldnt understand how to compare wins with the top team
    public static double getGamesBehind(String line) {
        String[] parts = line.split("\t");
        double gamesBehind = (integer.parseInt(parts[]))
    }
*/
    public static void printStats(ArrayList<String> games) { //function that gets all stats and prints them
        String[] parts;
        double winPct;
        System.out.println("Team       Wins Losses  Pct.   ");
        System.out.println("-------------------------------");
        for (String game : games) {
            parts = game.split("\t");
            winPct = getWinPct(game);
            System.out.printf("%-13s%s%5s%6.3f\n", parts[0], parts[1], parts[2], winPct);
        }
    }

    public static void insertByWinPct(ArrayList<String> overall, String line) {
        double thisWinPct = getWinPct(line); //function that gets overall teams and puts them in win % order
        double otherWinPct;
        int pos = -1;
        for (int i = 0; i < overall.size(); i++) {
            otherWinPct = getWinPct(overall.get(i));
            if (thisWinPct > otherWinPct) {
                pos = i;
                break;
            }
        }
        if (pos < 0) {
            overall.add(line);
        } else {
            overall.add(pos, line);
        }
    }
    public static void main(String[] args) { //main code
        Scanner sc = new Scanner(System.in); //gets scanner
        printHeader(); //prints heading
        System.out.println(" "); //probably coulda put all this in header function but its whatever
        System.out.println("This program reads a file that contains");
        System.out.println("current baseball standings and adds to");
        System.out.println("more detailed statistics. It also prints");
        System.out.println("overall baseball standings in the American");
        System.out.println("and National Leagues.");
        System.out.println(" ");
        System.out.print("Enter the name of the standings file: ");
        String fname = sc.nextLine(); //gets name of file
        ArrayList<String> alEast = new ArrayList<String>(); //created all array lists for each option
        ArrayList<String> alCentral = new ArrayList<String>();
        ArrayList<String> alWest = new ArrayList<String>();
        ArrayList<String> nlEast = new ArrayList<String>();
        ArrayList<String> nlCentral = new ArrayList<String>();
        ArrayList<String> nlWest = new ArrayList<String>();
        ArrayList<String> target = null;
        ArrayList<String> overall = new ArrayList<String>();
        String line, division; //declared some variables
        String[] parts;
        int choice;
        boolean goAhead;
        try {
            Scanner fsc = new Scanner(new File(fname));
            while (fsc.hasNextLine()) {
                line = fsc.nextLine();
                parts = line.split("\t"); //splits line into parts at tab
                if (parts[0].equalsIgnoreCase("LEAGUE")) {
                    division = parts[1].toUpperCase(); //makes sure each division is the right one when chosen
                    if (division.equalsIgnoreCase("AL EAST")) {
                        target = alEast;
                    } else if (division.equalsIgnoreCase("AL CENTRAL")) {
                        target = alCentral;
                    } else if (division.equalsIgnoreCase("AL WEST")) {
                        target = alWest;
                    } else if (division.equalsIgnoreCase("NL EAST")) {
                        target = nlEast;
                    } else if (division.equalsIgnoreCase("NL CENTRAL")) {
                        target = nlCentral;
                    } else if (division.equalsIgnoreCase("NL WEST")) {
                        target = alCentral;
                    }
                } else { //adds the teams by win pct for option 7
                    target.add(line);
                    insertByWinPct(overall, line);
                }
            }
            fsc.close(); //closes file
            goAhead = true;
        } catch (Exception ex) { //cant read file
            System.out.println("Couldn't read the file.");
            goAhead = false;
        }
        if (goAhead) {
            do {
                choice = showMenuGetChoice(sc);// gets choice and prints stats for each division when chosen
                if (choice == 1) {
                    printStats(alEast);
                } else if (choice == 2) {
                    printStats(alCentral);
                } else if (choice == 3) {
                    printStats(alWest);
                } else if (choice == 4) {
                    printStats(nlEast);
                } else if (choice == 5) {
                    printStats(nlCentral);
                } else if (choice == 6) {
                    printStats(nlWest);
                } else if (choice == 7) { //prints overall
                    System.out.println("Team        Wins  Losses       ");
                    System.out.println("-------------------------------");
                    for (String team : overall) {
                        System.out.println(team);
                    }
                }
            } while (choice != 8);
        }    
    }
}
