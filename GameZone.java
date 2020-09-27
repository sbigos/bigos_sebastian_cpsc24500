// Sebastian Bigos
// Welcome to the Game Zone :)
import java.util.Scanner;
import java.util.Random;
public class GameZone {
    public static void printHeading() { //prints the welcome heading
        System.out.println("******************************************");
        System.out.println("*        Welcome to the Game Zone        *");
        System.out.println("******************************************");
    }

    public static int showMenuAndGetChoice(final Scanner sc) { // displays menu and gets choice from user
        int choice;
        do {
            try {
                System.out.println("What would you like to play?");
                System.out.println("1. Twenty-one");
                System.out.println("2. Rock Paper Scissors");
                System.out.println("3. Neither - I'm done!");
                System.out.println("Please enter the number of your choice: ");
                choice = sc.nextInt();
                if (choice < 0 || choice > 3) { // re runs the menu if user inputs not a 1,2, or 3
                }
            } catch (final Exception ex) {
                System.out.println("You must enter a number.");
                sc.nextLine();
                choice = 0;
            }
        } while (choice < 0 || choice > 3);
        return choice;
    }

    public static void printGoodbye() {
        System.out.println("******************************************");
        System.out.println("*          Thanks for playing!           *");
        System.out.println("******************************************");
    }
    public static void main(final String[] args) {
        final Scanner sc = new Scanner(System.in); // get scanners, randoms, and initialize variables
        final Random rnd = new Random();
        int choice, card, total, dealer, user, cpu;
        printHeading();
        do {
            choice = showMenuAndGetChoice(sc);
            if (choice == 1) { //runs 21 game
                String doAgain = "y";
                total = 0;
                do {
                    card = rnd.nextInt(11) + 1; //draws card, + 1 bc rnd int can get 0 so it draws 1-11
                    total = total + card;
                    System.out.printf("You drew %d.\n", card);
                    System.out.printf("Your current total is %d.\n", total);
                    System.out.print("Do you want to draw another card? (y or n) ");
                    doAgain = sc.next().trim(); //keeps going until choose no
                } while (doAgain.equalsIgnoreCase("y"));
                dealer = 13 + rnd.nextInt(8); //dealers score
                System.out.printf("The computer drew %d.\n", dealer); 
                if (total > 21) { //compares your score with dealer and prints the result
                    System.out.println("You lost.");
                } else if (total < dealer) {
                    System.out.println("You lost.");
                } else if (total == dealer) {
                    System.out.println("You tied.");
                } else if (total > dealer) {
                    System.out.println("You won!");
                }
            } else if (choice == 2) { //runs rock, paper, scissors
                user = rnd.nextInt(3); //generates your choice
                cpu = rnd.nextInt(3); //generates computers choice
                if (user == 0 && cpu == 0) { //rock = 0, paper = 1, scissors = 2, compares choices and prints result
                    System.out.println("You played Rock, and the computer played Rock.");
                    System.out.println("It was a tie");
                } else if (user == 0 && cpu == 1) {
                    System.out.println("You played Rock, and the computer played Paper.");
                    System.out.println("You lost.");
                } else if (user == 0 && cpu == 2) {
                    System.out.println("You played Rock, and the computer played Scissors.");
                    System.out.println("You won.");
                } else if (user == 1 && cpu == 0) {
                    System.out.println("You played Paper, and the computer played Rock.");
                    System.out.println("You won.");
                } else if (user == 1 && cpu == 1) {
                    System.out.println("You played Paper, and the computer played Paper.");
                    System.out.println("It was a tie.");
                } else if (user == 1 && cpu == 2) {
                    System.out.println("You played Paper, and the computer played Scissors.");
                    System.out.println("You lost.");
                } else if (user == 2 && cpu == 0) {
                    System.out.println("You played Scissors, and the computer played Rock.");
                    System.out.println("You lost.");
                } else if (user == 2 && cpu == 1) {
                    System.out.println("You played Scissors, and the computer played Paper.");
                    System.out.println("You won.");
                } else if (user == 2 && cpu == 2){
                    System.out.println("You played Scissors, and the computer played Scissors.");
                    System.out.println("It was a tie.");
                }
            }
        } while (choice != 3); //ends when choice is 3
        printGoodbye();
    }
}
