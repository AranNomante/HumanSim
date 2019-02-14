import java.util.Scanner;

public class Driver {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Human test = new Human();
        printInfo();
        while (true) {
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                if (value == 1) {
                    test.simulate();
                    printInfo();
                } else if (value == 0) break;
                else {
                    System.out.println("Enter only 1 or 0");
                    scanner.next();
                }
            } else {
                System.out.println("Wrong format please enter an integer");
                scanner.next();
            }
        }
    }

    private static void printInfo() {
        System.out.println("Welcome to the human simulator that I have created out of boredom");
        System.out.println("Please enter 1 to simulate");
        System.out.println("Enter 0 to exit");
    }
}
