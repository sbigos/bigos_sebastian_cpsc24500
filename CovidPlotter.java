import java.awt.BorderLayout; //import statements
import java.awt.Container;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Scanner;

import javax.swing.JFrame;

import org.math.plot.Plot2DPanel;

public class CovidPlotter { //function to print header
	public static void printHeader() {
		System.out.println("**************************************************************");
		System.out.println("*           INTERNATIONAL COVID-19 MORTALITY RATES           *");
		System.out.println("**************************************************************");
	}
	
	public static LinkedHashMap<String,double[]> readDataDaily(Scanner fsc) {
		LinkedHashMap<String,double[]> result = new LinkedHashMap<String,double[]>();
		fsc.nextLine(); //function to get daily death hash map
		String[] parts;
		String line;
		double[] cumulativeDeaths;
		double[] dailyDeaths;
		String country;
		while (fsc.hasNextLine()) {
			line = fsc.nextLine();
			parts = line.split("\t");
			country = parts[0];
			cumulativeDeaths = new double[parts.length-1];
			dailyDeaths = new double[parts.length-1];
			for (int i = 2; i < parts.length-1; i++) {
				cumulativeDeaths[i-1] = Double.parseDouble(parts[i]);
				dailyDeaths[i-1] = Double.parseDouble(parts[i]);
				dailyDeaths[i-1] = dailyDeaths[i-1] - cumulativeDeaths[i-2];
			} //subtracts to get daily deaths
			result.put(country,dailyDeaths);
		}
		return result;
	}
	
	public static LinkedHashMap<String,double[]> readDataCumulative(Scanner fsc) {
		LinkedHashMap<String,double[]> result = new LinkedHashMap<String,double[]>();
		fsc.nextLine();//function to get cumulative deaths in hash map
		String[] parts;
		String line;
		double[] cumulativeDeaths;
		String country;
		while (fsc.hasNextLine()) {
			line = fsc.nextLine();
			parts = line.split("\t");
			country = parts[0];
			cumulativeDeaths = new double[parts.length-1];
			for (int i = 1; i < parts.length-1; i++) {
				cumulativeDeaths[i-1] = Double.parseDouble(parts[i]);
			}//counts up total 
			result.put(country,cumulativeDeaths);
		}
		return result;
	}
	
	public static double[] getDays(int howMany) {//function to get total num of days
		double[] result = new double[howMany];
		for (int i = 0; i < howMany; i++) {
			result[i] = i;
		}
		return result;
	}
	
	public static void setUpShowFrameDaily(Plot2DPanel plot) {
		JFrame frm = new JFrame();//function to get frame for daily deaths
		frm.setTitle("Daily Deaths");
		frm.setBounds(100,100,500,500);
		frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container c = frm.getContentPane();
		c.setLayout(new BorderLayout());
		c.add(plot,BorderLayout.CENTER);
		frm.setVisible(true);
	}
	
	public static void setUpShowFrameCumulative(Plot2DPanel plot) {
		JFrame frm = new JFrame();//function to get frame for cumulative deaths
		frm.setTitle("Cumulative Deaths");
		frm.setBounds(100,100,500,500);
		frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container c = frm.getContentPane();
		c.setLayout(new BorderLayout());
		c.add(plot,BorderLayout.CENTER);
		frm.setVisible(true);
	}
	
	public static void main(String[] args) {
		LinkedHashMap<String,double[]> accounts = null;
		LinkedHashMap<String,double[]> accounts2 = null;
		String countries;
		String type; //initialize all variables needed
		String[] parts;
		double[] data;
		Scanner sc = new Scanner(System.in);
		printHeader();
		System.out.print("Enter name of data file: ");
		String fname = sc.nextLine();//loads data file that was typed in
		try {
			Scanner fsc = new Scanner(new File(fname));
			accounts = readDataDaily(fsc);//gets daily deaths
			//accounts2 = readDataCumulative(fsc);
			//tried doing this but it wouldnt work and couldnt figure out how to make it work
			//got cumulative graph to work but couldnt get them to both work, only 1
			fsc.close();
		} catch (Exception ex) {
			accounts = null;
		}
		if (accounts == null) {
			System.out.println("Failed to load the data");
		} else {//tells you if it worked or not
			System.out.println("Loaded succesffully"); 
			do {
				System.out.println("Enter countries to plot, separated by commas (or quit to end): ");
				countries = sc.nextLine();
				if (!countries.equalsIgnoreCase("quit")) {
					System.out.print("[D]aily or [C]umulative? ");
					type = sc.nextLine(); //gets user option
					Plot2DPanel plot = new Plot2DPanel();
					plot.addLegend("SOUTH"); //sets up the panel with plot and legend
					parts = countries.split(",");
					for (String part : parts) {
						part = part.trim(); //gets countries and sees if in the data
						if (accounts.containsKey(part) == false) {
							System.out.printf("%s is not in the data set.\n",part);
						} else {
							if (type.equalsIgnoreCase("D")) {
								data = accounts.get(part); //if D then daily plot
								plot.addLinePlot(part, getDays(data.length), data);
							} else if (type.equalsIgnoreCase("C")) {
								data = accounts2.get(part); //if C then cumulative plot
								plot.addLinePlot(part, getDays(data.length), data);
							}
							/*
							data = accounts.get(part);
							plot.addLinePlot(part, getDays(data.length),data);
							*/
						}
					}
					setUpShowFrameDaily(plot); //shows actual graph and window
				}
			} while (!countries.equalsIgnoreCase("quit"));
			System.out.println("Stay safe!"); //ends program
		}
	}
}

