package Modules;

import java.util.Scanner;

import java.util.ArrayList;
import java.util.Collections;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import Databases.SettingsDB;
import Enums.AgeType;
import Enums.CinemaType;
import Enums.DateType;
import Enums.MovieType;
import Enums.SeatType;
import Objects.Settings;

public class SettingsModule {
  private Scanner sc;
  private Settings settingsObj;

  public SettingsModule(Scanner sc) {
    this.sc = sc;
  }

  private void displayHolidayDates() {
    System.out.println("Current Holiday Dates are: ");
    ArrayList<LocalDate> holidayDates = settingsObj.getHolidayDates();
    DateTimeFormatter dFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    for (LocalDate date: holidayDates) {
      System.out.println(date.format(dFormatter));
    }
  }

  private void askNewPriceAndWriteToDB(SettingsDB settingsDB, int choice, String typeChoice) {
    int price = 0;
    switch (choice) {
      case 1:
        price = settingsObj.getMovieTypePrice(typeChoice);
        break;

      case 2:
        price = settingsObj.getCinemaClassPrice(typeChoice);
        break;

      case 3:
        price = settingsObj.getAgeTypePrice(typeChoice);
        break;

      case 4:
        price = settingsObj.getDayTypePrice(typeChoice);
        break;

      case 5:
        price = settingsObj.getSeatTypePrice(typeChoice);
        break;
    
      default:
        break;
    }
    System.out.println("Old Price was: " + price);

    System.out.print("Set New Price: ");
    int newPrice = sc.nextInt();
    sc.nextLine();

    System.out.println("Writing to database...");
    switch (choice) {
      case 1:
        settingsObj.setMovieTypePrice(typeChoice, newPrice);
        break;

      case 2:
        settingsObj.setCinemaClassPrice(typeChoice, newPrice);
        break;

      case 3:
        settingsObj.setAgeTypePrice(typeChoice, newPrice);
        break;

      case 4:
        settingsObj.setDayTypePrice(typeChoice, newPrice);
        break;

      case 5:
        settingsObj.setSeatTypePrice(typeChoice, newPrice);
    
      default:
        break;
    }
    settingsDB.write(settingsObj);
  }

  public void run() {
    SettingsDB settingsDB = new SettingsDB();
    settingsObj = (Settings)settingsDB.read();
    
    if (settingsObj == null) {
      settingsObj = new Settings();
    }

    boolean running = true;
    while (running) {
      System.out.println("***********************************************");
      System.out.println("MOBLIMA -- Admin -- Settings Module:");
      System.out.println("[1] Display All Prices");
      System.out.println("[2] Display Holiday Dates");
      System.out.println("[3] Edit Prices for Movie Type");
      System.out.println("[4] Edit Prices for Cinema Class");
      System.out.println("[5] Edit Prices for Movie Goer Age");
      System.out.println("[6] Edit Prices for Day Type");
      System.out.println("[7] Edit Prices for Seat Type");
      System.out.println("[8] Edit Holiday Dates");
      System.out.println("[9] Back");
      System.out.print("Please Select Option: ");
      int choice = sc.nextInt();
      sc.nextLine();
      System.out.println("***********************************************");
      switch (choice) {
        case 1:
          System.out.println("Movie Types\t| Cinema Classes\t| Movie Goer Age\t| Days");
          for (int i = 0; i<3; i++) {
            String movieType = MovieType.values()[i].name();
            String cinemaClass = CinemaType.values()[i].name();
            String ageType = AgeType.values()[i].name();
            String dayType = DateType.values()[i].name();
            if (i == 0) {
              System.out.println(
                movieType + ": " + settingsObj.getMovieTypePrice(movieType) + "\t| " +
                cinemaClass + ": " + settingsObj.getCinemaClassPrice(cinemaClass) + "\t| " +
                ageType + ": " + settingsObj.getAgeTypePrice(ageType) + "\t\t| " +  
                dayType + ": " + settingsObj.getDayTypePrice(dayType) + "\t"
              );
            } else {
              System.out.println(
                movieType + ": " + settingsObj.getMovieTypePrice(movieType) + "\t| " +
                cinemaClass + ": " + settingsObj.getCinemaClassPrice(cinemaClass) + "\t\t| " +
                ageType + ": " + settingsObj.getAgeTypePrice(ageType) + "\t\t| " +  
                dayType + ": " + settingsObj.getDayTypePrice(dayType) + "\t"
              );
            }
          }
          break;

        case 2:
          displayHolidayDates();
          break;

        case 3:
          int movieTypeChoice = 0;;
          while (movieTypeChoice < 1 || movieTypeChoice > 3) {
            System.out.println("\n[1] Regular");
            System.out.println("[2] 3D");
            System.out.println("[3] Blockbuster");

            System.out.print("\nPlease enter Movie Type: ");
            movieTypeChoice = sc.nextInt();
            sc.nextLine();
          }
          askNewPriceAndWriteToDB(settingsDB, choice, MovieType.values()[movieTypeChoice-1].name());
          break;

        case 4:
          int cinemaClassChoice = 0;
          while (cinemaClassChoice < 1 || cinemaClassChoice > 3) {
            System.out.println("\n[1] Gold Class");
            System.out.println("[2] Deluxe");
            System.out.println("[3] Regular");

            System.out.print("\nPlease enter Cinema Class: ");
            cinemaClassChoice = sc.nextInt();
            sc.nextLine();
          }
          askNewPriceAndWriteToDB(settingsDB, choice, CinemaType.values()[cinemaClassChoice-1].name());
          break;

        case 5:
          int movieGoerAgeChoice = 0;
          while (movieGoerAgeChoice < 1 || movieGoerAgeChoice > 3) {
            System.out.println("\n[1] Child");
            System.out.println("[2] Adult");
            System.out.println("[3] Senior");
            System.out.print("\nPlease enter Age Type: ");
            movieGoerAgeChoice = sc.nextInt();
            sc.nextLine();
          }
          askNewPriceAndWriteToDB(settingsDB, choice, AgeType.values()[movieGoerAgeChoice-1].name());
          break;

        case 6:
          int dayTypeChoice = 0;
          while (dayTypeChoice < 1 || dayTypeChoice > 3) {
            System.out.println("\n[1] Weekday");
            System.out.println("[2] Weekend");
            System.out.println("[3] Public Holiday");
            System.out.print("\n Please enter Day Type");
            dayTypeChoice = sc.nextInt();
            sc.nextLine();
          }
          askNewPriceAndWriteToDB(settingsDB, choice, DateType.values()[dayTypeChoice-1].name());
          break;

        case 7:
          int seatTypeChoice = 0;
          while (seatTypeChoice < 1 || seatTypeChoice > 4) {
            System.out.println("[1] Regular");
            System.out.println("[2] Couple");
            System.out.println("[3] Elite");
            System.out.println("[4] Ultima");
            seatTypeChoice = sc.nextInt();
            sc.nextLine();
          }
          askNewPriceAndWriteToDB(settingsDB, choice, SeatType.values()[seatTypeChoice-1].name());
          break;

        case 8:
          displayHolidayDates();
          System.out.print("\nEnter Holiday Date to add/remove (dd-MM-yyyy, eg. 01-01-2022): ");
          String inputDateString = sc.nextLine();

          int dateAlreadyExistsAtPosition = -1;
          ArrayList<LocalDate> holidayDates = settingsObj.getHolidayDates();
          DateTimeFormatter dFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
          LocalDate inputDate = LocalDate.parse(inputDateString, dFormatter);

          for (int i = 0; i < holidayDates.size(); i++) {
            if (holidayDates.get(i).equals(inputDate)) {
              dateAlreadyExistsAtPosition = i;
              break;
            }
          }

          if (dateAlreadyExistsAtPosition != -1) {
            holidayDates.remove(dateAlreadyExistsAtPosition);
            dateAlreadyExistsAtPosition = -1;
          } else {
            holidayDates.add(inputDate);
          }
          Collections.sort(holidayDates);
          settingsObj.setHolidayDates(holidayDates);
          settingsDB.write(settingsObj);
          break;

        case 9:
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
