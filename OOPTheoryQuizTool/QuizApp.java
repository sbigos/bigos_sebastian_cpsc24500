package OOPTheoryQuizTool;
/**
 * This class contains the main function that runs the quiz game
 */
import java.util.ArrayList;
import java.util.Scanner;
public class QuizApp {
    public static void printHeader() {//prints the header
        System.out.println("*****************************************************************");
        System.out.println("*           OOP Theory and Concept Questions Quiz               *");
        System.out.println("*****************************************************************");
    }
    public static int showMenuGetUserChoice(Scanner sc) {//prints the menu and gets the choice of the user
        System.out.println("Here are your choices:");
        System.out.println("1. Take a quiz.");
        System.out.println("2. See questions and answers.");
        System.out.println("3. Exit.");
        System.out.println("Enter the number of your choice: ");
        int result = sc.nextInt();
        sc.nextLine();
        return result;
    }
    public static void main(String[] args) {
        ArrayList<Question> questions = new ArrayList<Question>();//this holds quiz questions we make
        Scanner sc = new Scanner(System.in);
        String question, choices, answer;
        int userChoice;
        printHeader();
        System.out.println("Enter name of file containing questions: ");
        String fname = sc.nextLine();//gets the JSON file from user
        do {
            userChoice = showMenuGetUserChoice(sc);
            if (userChoice == 1) {

            }
            else if (userChoice == 2) {
                
            }
        } while (userChoice != 3);//ends program when user chooses 3
        System.out.println("*****************************************************************");
        System.out.println("*              Thank you for Taking the Quiz!                   *");
        System.out.println("*****************************************************************");
    }
}
