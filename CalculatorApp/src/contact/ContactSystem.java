package contact;

import java.util.Scanner;

public class ContactSystem {

    static String[] names = new String[5];
    static String[] phones = new String[5];
    static int count = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("\nContact System");
            System.out.print("1.Add 2.View 3.Update 4.Delete 5.Exit\n");
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
                    if (count < 5) {
                        System.out.print("Enter name: ");
                        names[count] = sc.next();
                        System.out.print("Enter phone: ");
                        phones[count] = sc.next();
                        count++;
                        System.out.println("Contact Added");
                    } else {
                        System.out.println("List Full");
                    }
                    break;

                case 2:
                    if (count == 0) {
                        System.out.println("No contacts");
                    } else {
                        for (int i = 0; i < count; i++) {
                            System.out.println(i + ". " + names[i] + " - " + phones[i]);
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter index to update: ");
                    int u = sc.nextInt();
                    if (u < count) {
                        System.out.print("New name: ");
                        names[u] = sc.next();
                        System.out.print("New phone: ");
                        phones[u] = sc.next();
                        System.out.println("Updated");
                    } else {
                        System.out.println("Invalid index");
                    }
                    break;

                case 4:
                    System.out.print("Enter index to delete: ");
                    int d = sc.nextInt();
                    if (d < count) {
                        for (int i = d; i < count - 1; i++) {
                            names[i] = names[i + 1];
                            phones[i] = phones[i + 1];
                        }
                        count--;
                        System.out.println("Deleted");
                    } else {
                        System.out.println("Invalid index");
                    }
                    break;

                case 5:
                    System.out.println("Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice");
            }

        } while (choice != 5);

        sc.close();
    }
}