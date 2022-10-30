package Modules;

import java.util.ArrayList;
import java.util.Scanner;

import Databases.CineplexDB;
import Objects.Cineplex;
import Objects.Cinema;

public class BookingModule {
  private Scanner sc;
  private Cineplex cineplexObj;
  private ArrayList<Cineplex> cineplexList;
  private ArrayList<Cinema> cinemaList;

  public BookingModule(Scanner sc) {
    this.sc = sc;
  }

  public void run() {
    System.out.println("***********************************************");
    System.out.println("MOBLIMA -- Booking Module:");

    cineplexList = CineplexDB.getCineplexList();
    selectCineplex();

    System.out.println("***********************************************");
    System.out.println("MOBLIMA -- Booking Module (Selected Cineplex: " + cineplexObj.getCineplexName() + " ):");
    System.out.println("[1] Display All Cinema Showings");
    System.out.println("[2] Display Cinema Showings");
    System.out.println("[3] Check Showing Seat Availability");
    System.out.println("[4] Select, Book & Purchase Tickets");
    System.out.println("[5] Reselect Cineplex");
    System.out.println("[6] Back");

    Cinema cinemaObj;

    int choice = sc.nextInt();
    switch (choice) {
      case 1:
        for (Cinema cinema: cinemaList) {
          System.out.println("Cinema " + cinema.getCinemaNumber() + ":");
          cinema.displayShowList();
          System.out.println("");
        }
        break;

      case 2:
        cinemaObj = selectCinema();
        cinemaObj.displayShowList();
        System.out.println("");
        
      case 3:
        cinemaObj = selectCinema();
        break;

      default:
        break;
    }
  }

  private void selectCineplex() {
    int choice;
    do {
      System.out.println("Please select your cineplex of choice: ");
      int index = 0;
      for (Cineplex cineplex: cineplexList) {
        index++;
        System.out.println("[" + index + "]: " + cineplex.getCineplexName());
      }
      int cineplexSize = cineplexList.size();
      choice = sc.nextInt();
      choice = choice < 1 || choice > cineplexSize ? 0 : choice;
      if (choice == 0) {
        System.out.println("Invalid choice, Please try again.\n");
      }
    } while (choice == 0);
    cineplexObj = cineplexList.get(choice-1);
    cinemaList = cineplexObj.getListOfCinemas();
  }

  private Cinema selectCinema() {
    int cinemaSize = cinemaList.size();
    int cinemaChoice;
    do {
      System.out.print("Please select your cinema of choice (1-" + cinemaSize + "): ");
      cinemaChoice = sc.nextInt();
      cinemaChoice = cinemaChoice < 1 || cinemaChoice > cinemaSize ? 0 : cinemaChoice;
      if (cinemaChoice == 0) {
        System.out.println("Invalid choice, Please try again.\n");
      }
    } while (cinemaChoice == 0);
    Cinema cinema = cinemaList.get(cinemaChoice);
    return cinema;
  }
}
