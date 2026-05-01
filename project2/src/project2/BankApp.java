package project2;

import java.util.Scanner;

public class BankApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        BankAccount acc = new BankAccount(1000); // initial balance
        int choice = 0;

        do {
            System.out.println("\nBank System");
            System.out.print("1.Deposit 2.Withdraw 3.Check Balance 4.Exit\n");
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
                    System.out.print("Enter amount: ");
                    double d = sc.nextDouble();
                    acc.deposit(d);
                    System.out.println("Deposited");
                    break;

                case 2:
                    System.out.print("Enter amount: ");
                    double w = sc.nextDouble();
                    try {
                        acc.withdraw(w);
                        System.out.println("Withdrawn");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    System.out.println("Balance: " + acc.getBalance());
                    break;

                case 4:
                    System.out.println("Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice");
            }

        } while (choice != 4);

        sc.close();
    }
}