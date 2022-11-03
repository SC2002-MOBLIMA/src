package Modules;
import java.util.Scanner;


public class MOBLIMA {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("***********************************************");
        System.out.println("************* WELCOME TO MOBLIMA **************");
        System.out.println("***********************************************");
        int choice;
        boolean running = true;
        while (running) {

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
            System.out.println("Invalid Choice, Please try again!\n");
            break;
        }
        }
    }
}
