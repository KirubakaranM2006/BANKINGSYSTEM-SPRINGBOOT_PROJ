package calculatordemo;
import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        do {
            // ✅ Single-line menu
            System.out.println("\nCalculator");
            System.out.print("1.Add 2.Sub 3.Mul 4.Div\n");
            System.out.print("5.Sqrt 6.Power 7.Exit\n");
            System.out.print("Enter choice: ");

            if (sc.hasNextInt()) {
                choice = sc.nextInt();
            } else {
                System.out.println("Invalid input!");
                sc.next();
                continue;
            }

            switch (choice) {

                case 1:
                    System.out.print("Enter 2 numbers: ");
                    int a1 = sc.nextInt();
                    int b1 = sc.nextInt();
                    System.out.println("Result: " + (a1 + b1));
                    break;

                case 2:
                    System.out.print("Enter 2 numbers: ");
                    int a2 = sc.nextInt();
                    int b2 = sc.nextInt();
                    System.out.println("Result: " + (a2 - b2));
                    break;

                case 3:
                    System.out.print("Enter 2 numbers: ");
                    int a3 = sc.nextInt();
                    int b3 = sc.nextInt();
                    System.out.println("Result: " + (a3 * b3));
                    break;

                case 4:
                    System.out.print("Enter 2 numbers: ");
                    int a4 = sc.nextInt();
                    int b4 = sc.nextInt();
                    if (b4 == 0)
                        System.out.println("Cannot divide by zero");
                    else
                        System.out.println("Result: " + (a4 / b4));
                    break;

                case 5:
                    System.out.print("Enter number: ");
                    int n = sc.nextInt();
                    System.out.println("Result: " + Math.sqrt(n));
                    break;

                case 6:
                    System.out.print("Enter base & exponent: ");
                    int base = sc.nextInt();
                    int exp = sc.nextInt();
                    System.out.println("Result: " + Math.pow(base, exp));
                    break;

                case 7:
                    System.out.println("Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice");
            }

        } while (choice != 7);

        sc.close();
    }
}