public class Flooring {
    /**
     * This function calculates the total cost.
     * @param num total packages that are being purchased
     * @return the total cost
     */
    public static double determineCost(int num) {
         double totalCost = num * 24.99;
         return totalCost;
    }
    /**
     * This is my main code.
     * It is unnecessarily long as I included every step of my work.
     * @param args
     */
    public static void main(String[] args) {
        int totalArea = 25 * 20;
        int triangleArea = 10 * 12 / 2;
        int neededArea = totalArea - triangleArea;
        int neededAreaInches = neededArea * 12;
        double twentyPercentExtra = neededAreaInches * .2;
        int twentyPercentExtraInt = (int)twentyPercentExtra;
        int totalNeededAreaInches = neededAreaInches + twentyPercentExtraInt;
        int boardArea = 30 * 6;
        int packageArea = boardArea * 6;
        float packagesNeeded = (float)totalNeededAreaInches / (float)packageArea;
        double totalPackagesNeeded = Math.ceil(packagesNeeded);
        int totalPackagesNeededInt = (int)totalPackagesNeeded;
        double cost = determineCost(totalPackagesNeededInt);
        System.out.println("You will need to buy a total of " + totalPackagesNeededInt + " packages that will cost you a total of $" + cost + ".");
    }
}
