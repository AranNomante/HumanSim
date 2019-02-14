import java.util.Scanner;

public class Driver {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Human test=new Human();
        printInfo();
        while (true) {
            if (scanner.hasNextInt()) {
                int value=scanner.nextInt();
                if (value==0)break;
                else if(value>0){
                    test.simulate(value);
                    printInfo();
                }else {
                    System.out.println("Negative integers will not be accepted");
                    scanner.next();
                }
            } else {
                System.out.println("Wrong format please enter an integer");
                scanner.next();
            }
        }
    }
    private static void printInfo(){
        System.out.println("Welcome to the human simulator that I have created out of boredom");
        System.out.println("Please enter any positive integer to watch the simulation happen");
        System.out.println("Values 2 to 100 are advised more than 100 will require more time");
        System.out.println("Target value 1 is default test number 0 will always succeed");
        System.out.println("Target value 10612 is the maximum value");
        System.out.println("Enter 0 to exit");
    }
}
