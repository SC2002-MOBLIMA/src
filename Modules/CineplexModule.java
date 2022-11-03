package Modules;

import java.util.Scanner;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import Databases.MovieDB;
import Databases.CineplexDB;

import Enums.DateType;
import Objects.Cineplex;
import Objects.Cinema;
import Objects.Showing;
import Objects.Movie;

public class CineplexModule {
  private Scanner sc;
  private ArrayList<Cineplex> cineplexList;
  private Cineplex cineplexReq;
  private Cinema cinemaReq;
  private Showing showReq;
  private Movie movieReq;

  public CineplexModule(Scanner sc) {
    this.sc = sc;
  }

  boolean main = true;

  public void run() {

    boolean main = true;
    while (main) {
      // System.out.println("************************************************************");
      System.out.println("Please key in the number of the cineplex name that you would like to edit");
      
      CineplexDB cineplexDB = new CineplexDB();
      @SuppressWarnings("unchecked")
      ArrayList<Cineplex> cineplexList = (ArrayList<Cineplex>)cineplexDB.read();

      for (int i = 0; i < cineplexList.size(); i++) {
        System.out.println("[" + (i+1) + "] " + cineplexList.get(i).getCineplexName());
      }
      int name = sc.nextInt();

      if(!(name<1 || name>cineplexList.size())){
        cineplexReq = cineplexList.get(name-1);
        main = false;
        break;
      } 
        // if (cineplexList.get(i).getCineplexName() == name) {
        //   cineplexReq = cineplexList.get(i);
        //   main = false;
        // }
      
      System.out.println("Error: Cineplex not found. Please try again");
    }

    boolean main_cinema = true;
    while (main_cinema) {
      System.out.println("************************************************************");
      System.out.println("Please key in the cinema number that you would like to edit");
      ArrayList<Cinema> cinemaList = cineplexReq.getListOfCinemas();
      for (int i = 0; i < cinemaList.size(); i++) {
        System.out.println("[" + cinemaList.get(i).getCinemaNum() + "]");
      }
      int num = sc.nextInt();
      System.out.println("************************************************************");

      for (int i = 0; i < cinemaList.size(); i++) {
        if (cinemaList.get(i).getCinemaNum() == num) {
          cinemaReq = cinemaList.get(i);
          main_cinema = false;
        }
      }
      if(main_cinema){
        System.out.println("Error: Cinema not found. Please try again");
      }
    }

    boolean main_final = true;
    while (main_final) {
      System.out.println("[1] Add Showing");
      System.out.println("[2] Remove Showing");
      System.out.println("[3] Update Showing");
      System.out.println("[4] Back");
      int select = sc.nextInt();
      System.out.println("************************************************************");

      switch (select) {
        case 1:
          addShow();
          break;

        case 2:
          removeShow();
          break;

        case 3:
          updateShow();
          break;

        case 4:
          main_final = false;
          break;
      }
    }
  }

  public void addShow() {

    boolean main = true;
    while (main) {
      MovieDB movieDB = new MovieDB();
      @SuppressWarnings("unchecked")
      ArrayList<Movie> movieList = (ArrayList<Movie>) movieDB.read(); // Resolve tomorrow
      System.out.println("Key in the number of the movie that you would like to add");
      for (int i = 0; i < movieList.size(); i++) {
        System.out.println("[" + (i + 1) + "] " + movieList.get(i).getTitle());
      }
      int selection = sc.nextInt();
      System.out.println("************************************************************");
      if (!(selection < 1 || selection > movieList.size())) {
        main = false;
        movieReq = movieList.get(selection - 1);
        break;
      } else {
        System.out.println("Error: Key in a valid value");
      }
    }

    boolean main_next = true;
    while (main_next) {
      ArrayList<Showing> showList = cinemaReq.getShowList();
      System.out.println("Key in the Date and Time of the show in the following format (yyyyMMddHHmm): ");
      String input = sc.next();
      System.out.println("************************************************************");

      DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
      try {
        // TODO: Find out how to validate
        LocalDateTime dateTime = LocalDateTime.parse(input, myFormatObj);

        System.out.println("Pick a DateType: ");
        System.out.println("[1] Weekend");
        System.out.println("[2] Weekday");
        System.out.println("[3] Public Holiday");
        int dateint = sc.nextInt();
        System.out.println("************************************************************");

        switch(dateint){
          case 1:
            showReq = new Showing(movieReq, dateTime, DateType.WEEKEND);
            showList.add(showReq);
            System.out.println("Show has been sucessfully added");
            main_next = false;
            break;
          
          case 2:
            showReq = new Showing(movieReq, dateTime, DateType.WEEKDAY);
            showList.add(showReq);
            System.out.println("Show has been sucessfully added");
            main_next = false;
            break;
          
          case 3:
            showReq = new Showing(movieReq, dateTime, DateType.PUBLIC_HOLIDAY);
            showList.add(showReq);
            System.out.println("Show has been sucessfully added");
            main_next = false;
            break;
          
          default:
            System.out.println("Show cannot be added");
            break;

        }
      } catch (Exception e) {
        System.out.println("Error: Invalid date format. Please try again");
      }
    }
  }

  public void removeShow() {

    boolean main = true;
    while (main) {
      ArrayList<Showing> showList = cinemaReq.getShowList();
      System.out.println("Key in the number of the show that you would like to remove: ");
      cinemaReq.displayShowList();
      int selection = sc.nextInt();
      System.out.println("************************************************************");
      if (!(selection < 1 || selection > showList.size())) {
        showList.remove(selection - 1);
        main = false;
        System.out.println("Selection has been sucessfully removed");
      } else {
        System.out.println("Error: Key in a valid value");
      }
    }
  }

  public void updateShow() {
    ArrayList<Showing> showList = cinemaReq.getShowList();
    System.out.println("Key in the number of the show that you would like to update: ");
    cinemaReq.displayShowList();
    int selection = sc.nextInt();
    System.out.println("************************************************************");
    if (!(selection < 1 || selection > showList.size())) {
      // TODO: Discuss tomorrow
    } else {
      System.out.println("Error: Key in a valid value");
    }
  }
}
