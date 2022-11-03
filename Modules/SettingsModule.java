package Modules;

import java.util.Scanner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.time.LocalDateTime;

import Databases.SettingsDB;

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
          String movieTypeChoice = "null";
          while (!movieTypeChoice.equals("REGULAR") &&
              !movieTypeChoice.equals("THREE_D")
              && !movieTypeChoice.equals("BLOCKBUSTER")) {
            System.out.println("Movie Type: (REGULAR, THREE_D, BLOCKBUSTER)");
            movieTypeChoice = sc.nextLine();
          }
          askNewPriceAndWriteToDB(settingsDB, settingsList, choice, movieTypeChoice);
          break;

        case 2:
          String cinemaClassChoice = "null";
          while (!cinemaClassChoice.equals("GOLD_CLASS")
              && !cinemaClassChoice.equals("DELUXE") && !cinemaClassChoice.equals("REGULAR")) {
            System.out.println("Movie Type: (GOLD_CLASS, DELUXE, REGULAR)");
            cinemaClassChoice = sc.nextLine();
          }
          askNewPriceAndWriteToDB(settingsDB, settingsList, choice, cinemaClassChoice);
          break;

        case 3:
          String movieGoerAgeChoice = "null";
          while (!movieGoerAgeChoice.equals("CHILD")
              && !movieGoerAgeChoice.equals("ADULT") && !movieGoerAgeChoice.equals("SENIOR")) {
            System.out.println("Movie Type: (CHILD, ADULT, SENIOR)");
            movieGoerAgeChoice = sc.nextLine();
          }
          askNewPriceAndWriteToDB(settingsDB, settingsList, choice, movieGoerAgeChoice);
          break;

        case 4:
          String dayTypeChoice = "null";
          while (!dayTypeChoice.equals("WEEKDAY")
              && !dayTypeChoice.equals("WEEKEND") && !dayTypeChoice.equals("PUBLIC_HOLIDAY")) {
            System.out.println("Movie Type: (WEEKDAY, WEEKEND, PUBLIC_HOLIDAY)");
            dayTypeChoice = sc.nextLine();
          }
          askNewPriceAndWriteToDB(settingsDB, settingsList, choice, dayTypeChoice);
          break;
        case 5:
          System.out.println("Current Holiday Dates are: ");
          ArrayList holidayDates = (ArrayList) settingsList.get(4);
          for (int i = 0; i < holidayDates.size(); i++) {
            System.out.println(holidayDates.get(i));
          }
          System.out.print("Enter year: ");
          int year = sc.nextInt();
          sc.nextLine();
          System.out.print("Enter month: ");
          int month = sc.nextInt();
          sc.nextLine();
          System.out.print("Enter day: ");
          int day = sc.nextInt();
          sc.nextLine();
          System.out.print("Enter hour: ");
          int hour = sc.nextInt();
          sc.nextLine();
          System.out.print("Enter minute: ");
          int minute = sc.nextInt();
          sc.nextLine();

          int dateAlreadyExistsAtPosition = -1;
          for (int i = 0; i < holidayDates.size(); i++) {
            if (holidayDates.get(i).equals(LocalDateTime.of(year, month, day, hour, minute))) {
              dateAlreadyExistsAtPosition = i;
              break;
            }
          }

          if (dateAlreadyExistsAtPosition != -1) {
            ((ArrayList) settingsList.get(4)).remove(dateAlreadyExistsAtPosition);
            dateAlreadyExistsAtPosition = -1;
          } else {
            ((ArrayList) settingsList.get(4)).add(LocalDateTime.of(year, month, day, hour, minute));
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