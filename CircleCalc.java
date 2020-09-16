import java.util.Random;
public class CircleCalc {
    
    /**
     * This function simply weclomes the user to the program.
     */

    public static void welcome() {
        System.out.println("@@@@@@@@@@@@@@@@@@");
        System.out.println("    CircleCalc    ");
        System.out.println("@@@@@@@@@@@@@@@@@@");
    }

    /**
     * This function computes the area of a circle with a random radius.
     * @param r the radius of the circle
     * @return the area of the cirlce
     */

    public static double computeArea(int r) {
        double area = (r * r) * Math.PI;
        return area;
    }

    /**
     * The function computes the circumference of a circle with a random radius.
     * @param r the radius of the circle
     * @return the circumference of the circle
     */

    public static double computeCircum(int r) {
        double circum = 2 * r * Math.PI;
        return circum;
    }
    public static void main(String[] args) {
        welcome();
        Random rnd = new Random();
        int radius;
        radius = rnd.nextInt(50);
        double area = computeArea(radius);
        double circumference = computeCircum(radius);
        System.out.printf("The area of a circle with radius %d is %.2f.\n", radius,area);
        System.out.printf("The circumference is %.2f.", circumference);
    }
}
