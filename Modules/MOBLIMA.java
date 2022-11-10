package Modules;

import java.util.Scanner;

public class MOBLIMA {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n***********************************************");
        // System.out.println("************* WELCOME TO MOBLIMA **************");
        System.out.println(" __  __  ____  ____  _      _____ __  __");
        System.out.println("|  \\/  |/ __ \\|  _ \\| |    |_   _|  \\/  |   /\\");
        System.out.println("| \\  / | |  | | |_) | |      | | | \\  / |  /  \\");
        System.out.println("| |\\/| | |  | |  _ <| |      | | | |\\/| | / /\\ \\");
        System.out.println("| |  | | |__| | |_) | |____ _| |_| |  | |/ ____ \\");
        System.out.println("|_|  |_|\\____/|____/|______|_____|_|  |_/_/    \\_\\");
        System.out.println();
        int choice;
        boolean running = true;
        while (running) {
            System.out.println("***********************************************");
            System.out.println("MOBLIMA -- Main Menu:");
            System.out.println("[1] Movie Goer");
            System.out.println("[2] Admin");
            System.out.println("[3] Exit");
            System.out.print("Please Select Target Role: ");
            choice = sc.nextInt();
            sc.nextLine();
            System.out.println("***********************************************");
            switch (choice) {
                case 1:
                    MovieGoerModule movieGoerModule = new MovieGoerModule(sc);
                    movieGoerModule.run();
                    break;

                case 2:
                    AdminModule adminModule = new AdminModule(sc);
                    adminModule.run();
                    break;

                case 3:
                    System.out.println("Bye Bye!");
                    running = false;
                    break;

                default:
                    System.out.println("Error: Invalid Choice, Please try again.\n");
                    break;
            }
        }
        sc.close();
    }
}
