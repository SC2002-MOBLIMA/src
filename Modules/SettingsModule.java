package Modules;

import java.util.Scanner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.time.LocalDateTime;

import Databases.SettingsDB;
import Enums.AgeType;
import Enums.CinemaType;
import Enums.DateType;
import Enums.MovieType;

public class SettingsModule {
  private Scanner sc;

  public SettingsModule(Scanner sc) {
    this.sc = sc;
  }

  private void askNewPriceAndWriteToDB(SettingsDB settingsDB, ArrayList settingsList, int choice, String typeChoice) {
    System.out.println("Old Price was: " + ((HashMap) settingsList.get(choice - 1)).get(typeChoice));

    System.out.print("Set New Price: ");
    int newPrice = sc.nextInt();
    sc.nextLine();

    System.out.println("Writing to database...");
    ((HashMap) settingsList.get(choice - 1)).put(typeChoice, newPrice);
    settingsDB.write(settingsList);
  }

  public void run() {
    SettingsDB settingsDB = new SettingsDB();
    ArrayList settingsList = (ArrayList) settingsDB.read();

    boolean running = true;
    while (running) {
      System.out.println("***********************************************");
      System.out.println("MOBLIMA -- Admin -- Settings Module:");
      System.out.println("[1] Edit Prices for Movie Type");
      System.out.println("[2] Edit Prices for Cinema Class");
      System.out.println("[3] Edit Prices for Movie Goer Age");
      System.out.println("[4] Edit Prices for Day Type");
      System.out.println("[5] Edit Holiday Dates");
      System.out.println("[6] Back");
      System.out.print("Please Select Option: ");
      int choice = sc.nextInt();
      sc.nextLine();
      System.out.println("***********************************************");
      switch (choice) {
        case 1:
          int movieTypeChoice = 0;;
          while (movieTypeChoice < 1 || movieTypeChoice > 3) {
            System.out.println("\n[1] Regular");
            System.out.println("[2] 3D");
            System.out.println("[3] Blockbuster");

            System.out.print("\nPlease enter Movie Type: ");
            movieTypeChoice = sc.nextInt();
            sc.nextLine();
          }
          askNewPriceAndWriteToDB(settingsDB, settingsList, choice, MovieType.values()[movieTypeChoice-1].name());
          break;

        case 2:
          int cinemaClassChoice = 0;
          while (cinemaClassChoice < 1 || cinemaClassChoice > 3) {
            System.out.println("\n[1] Gold Class");
            System.out.println("[2] Deluxe");
            System.out.println("[3] Regular");

            System.out.print("\nPlease enter Cinema Class: ");
            cinemaClassChoice = sc.nextInt();
            sc.nextLine();
          }
          askNewPriceAndWriteToDB(settingsDB, settingsList, choice, CinemaType.values()[cinemaClassChoice-1].name());
          break;

        case 3:
          int movieGoerAgeChoice = 0;
          while (movieGoerAgeChoice < 1 || movieGoerAgeChoice > 3) {
            System.out.println("\n[1] Child");
            System.out.println("[2] Adult");
            System.out.println("[3] Senior");
            System.out.print("\nPlease enter Age Type: ");
            movieGoerAgeChoice = sc.nextInt();
            sc.nextLine();
          }
          askNewPriceAndWriteToDB(settingsDB, settingsList, choice, AgeType.values()[movieGoerAgeChoice-1].name());
          break;

        case 4:
          int dayTypeChoice = 0;
          while (dayTypeChoice < 1 || dayTypeChoice > 3) {
            System.out.println("\n[1] Weekday");
            System.out.println("[2] Weekend");
            System.out.println("[3] Public Holiday");
            System.out.print("\n Please enter Day Type");
            dayTypeChoice = sc.nextInt();
            sc.nextLine();
          }
          askNewPriceAndWriteToDB(settingsDB, settingsList, choice, DateType.values()[dayTypeChoice-1].name());
          break;
        case 5:
          System.out.println("Current Holiday Dates are: ");
          ArrayList<LocalDateTime> holidayDates = (ArrayList<LocalDateTime>)settingsList.get(4);
          for (LocalDateTime date: holidayDates) {
            System.out.println(date.toLocalDate());
          }
          System.out.print("\nEnter year: ");
          int year = sc.nextInt();
          sc.nextLine();
          System.out.print("Enter month: ");
          int month = sc.nextInt();
          sc.nextLine();
          System.out.print("Enter day: ");
          int day = sc.nextInt();
          sc.nextLine();

          int dateAlreadyExistsAtPosition = -1;
          for (int i = 0; i < holidayDates.size(); i++) {
            if (holidayDates.get(i).equals(LocalDateTime.of(year, month, day, 0, 0))) {
              dateAlreadyExistsAtPosition = i;
              break;
            }
          }

          if (dateAlreadyExistsAtPosition != -1) {
            ((ArrayList) settingsList.get(4)).remove(dateAlreadyExistsAtPosition);
            dateAlreadyExistsAtPosition = -1;
          } else {
            ((ArrayList) settingsList.get(4)).add(LocalDateTime.of(year, month, day, 0, 0));
          }
          Collections.sort((ArrayList) settingsList.get(4));
          settingsDB.write(settingsList);
          break;
        case 6:
          running = false;
          break;
        default:
          System.out.println("Invalid Choice, Please try again.\n");
          break;
      }
    }
  }
}

// // New Blank data for Settings DB
// ArrayList mockdata = new ArrayList();
// mockdata.add(new HashMap<String, Integer>());
// mockdata.add(new HashMap<String, Integer>());
// mockdata.add(new HashMap<String, Integer>());
// mockdata.add(new HashMap<String, Integer>());
// mockdata.add(new ArrayList());
// settingsDB.write(mockdata);
