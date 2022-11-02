package Modules;

import java.util.Scanner;
import java.util.ArrayList;

import Databases.AdminDB;
import Objects.Admin;

public class AdminModule {
  private Scanner sc;
  private Admin adminObj;
  private boolean isLoggedIn;
  
  public AdminModule(Scanner sc) {
    this.sc = sc;
    this.isLoggedIn = false; // TODO: toggle this to false
  }

  public void run() {
    System.out.println("***********************************************");
    System.out.println("MOBLIMA -- Admin Module:");
    AdminDB adminDB = new AdminDB();
    @SuppressWarnings("unchecked")
    ArrayList<Admin> adminList = (ArrayList<Admin>)adminDB.read();
    
    while (!isLoggedIn) {
      System.out.print("Please enter your username: ");
      String username = sc.next();
      System.out.print("Please enter your password: ");
      String password = sc.next();

      System.out.println("Admin: " + username + " Password: " + password);

      for (Admin admin: adminList) {
        System.out.println("Admin: " + admin.getUsername() + " Password: " + admin.getPassword());
        if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
          isLoggedIn = true;
          adminObj = admin;
          System.out.println("\nWelcome, " + username + "!\n");
          break;
        }
      }
      if (!isLoggedIn) {
        System.out.println("Invalid Credentials, Please try again.\n");
      }
    }

    boolean running = true;
    while (running) {
      System.out.println("***********************************************");
      System.out.println("MOBLIMA -- Admin Module:");
      System.out.println("[1] Create/Update/Remove movie listing");
      System.out.println("[2] Create/Update/Remove cinema showtimes and the movies to be shown");
      System.out.println("[3] Configure system settings");
      System.out.println("[4] Back");
      System.out.print("Please Select Option: ");
      int choice = sc.nextInt();
      System.out.println("***********************************************");
      switch (choice) {
        case 1:
          MovieListingModule movieListingModule = new MovieListingModule(sc);
          movieListingModule.run();
          break;
  
        case 2:
          CineplexModule cineplexModule = new CineplexModule(sc);
          cineplexModule.run();
          break;
  
        case 3:
          SettingsModule settingsModule = new SettingsModule(sc);
          settingsModule.run();
          break;
        
        case 4:
          running = false;
          break;
      
        default:
          System.out.println("Invalid Choice, Please try again.\n");
          break;
      }
    }
  }
}
