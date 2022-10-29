package Modules;
import java.util.Scanner;


public class MOBLIMA {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.println("***********************************************");
    System.out.println("************* WELCOME TO MOBLIMA **************");
    System.out.println("***********************************************");
    int choice;
    do {
      System.out.println("MOBLIMA -- Main Menu:");
      System.out.println("[1] Movie Goer");
      System.out.println("[2] Admin");
      System.out.print("Please Select Target Role: ");
      choice = sc.nextInt();
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
      
        default:
          System.out.println("Invalid Choice, Please Try Again!\n");
          break;
      }
    } while (choice != 1 && choice != 2);
    
  }
}
