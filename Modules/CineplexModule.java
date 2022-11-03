package Modules;

import java.util.Scanner;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.DayOfWeek;
import java.time.*;

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
      cineplexList = (ArrayList<Cineplex>)cineplexDB.read();
      for (int i = 0; i < cineplexList.size(); i++) {
        System.out.println("[" + (i+1) + "] " + cineplexList.get(i).getCineplexName());
      }
      int name = sc.nextInt();

      if(!(name<1 || name>cineplexList.size())){
        cineplexReq = cineplexList.get(name-1);
        main = false;
        break;
      }     
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
      System.out.println("[1] Show Showing");
      System.out.println("[2] Add Showing");
      System.out.println("[3] Remove Showing");
      System.out.println("[4] Update Showing");
      System.out.println("[5] Back");
      int select = sc.nextInt();
      System.out.println("************************************************************");

      switch (select) {
        
        case 1:
          showShow();
          break;
        
        case 2:
          addShow();
          break;

        case 3:
          removeShow();
          break;

        case 4:
          updateShow();
          break;

        case 5:
          main_final = false;
          break;
      }
    }
  }

  private void selectMovie(){
    boolean main = true;
    while (main) {
      MovieDB movieDB = new MovieDB();
      @SuppressWarnings("unchecked")
      ArrayList<Movie> movieList = (ArrayList<Movie>) movieDB.read();
      System.out.println("Key in the number of the movie that you would like");
      for (int i = 0; i < movieList.size(); i++) {
        System.out.println("[" + (i + 1) + "] " + movieList.get(i).getTitle());
      }
      int selection = sc.nextInt();
      System.out.println("************************************************************");
      if (!(selection < 1 || selection > movieList.size())) {
        main = false;
        movieReq = movieList.get(selection - 1);
      } else {
        System.out.println("Error: Key in a valid value");
      } 
    }
  }

  public void addShow() {
    selectMovie();
    ArrayList<Showing> showList = cinemaReq.getShowList();
    LocalDateTime dateTime = LocalDateTime.now();
    while (true) {
      try {
        System.out.println("Key in the Date and Time of the show in the following format (yyyyMMddHHmm): ");
        String input = sc.next();
        System.out.println("************************************************************");
  
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        dateTime = LocalDateTime.parse(input, myFormatObj);

        if(dateTime.compareTo(movieReq.getEndOfShowingDate())<0){
          break;
        }
        else{
          System.out.println("Date keyed in exceeds the end of showing of movie. Please key in the data again")
        }
      } catch (Exception e) {
        System.out.println("Error: Invalid date format. Please try again");
      }
    }

    DayOfWeek dayofWeek= DayOfWeek.from(dateTime);
    int day = dayofWeek.getValue();

    DateType inputDateType = DateType.WEEKDAY;
    if(day == 6 || day == 7){
      inputDateType = DateType.WEEKEND;
      showReq = new Showing(movieReq, dateTime, DateType.WEEKEND);
    }
    else{
      showReq = new Showing(movieReq, dateTime, DateType.WEEKDAY);
      showList.add(showReq);
      System.out.println("Show has been sucessfully added");
    }
    CineplexDB cineplexDB = new CineplexDB();
    cineplexDB.write(cineplexList);
  }

  public void showShow(){
    boolean main = true;
    ArrayList<Showing> showList = cinemaReq.getShowList();
    if(showList.size()==0){
      System.out.println("There are no showings available");
    }
    else{
      cinemaReq.displayShowList();
    }
    System.out.println("************************************************************");
  }

  public void removeShow() {
    boolean main = true;
    while (main) {
      ArrayList<Showing> showList = cinemaReq.getShowList();
      if(showList.size()==0){
        System.out.println("There are no showings to remove");
        break;
      }
      System.out.println("Key in the number of the show that you would like to remove: ");
      cinemaReq.displayShowList();
      int selection = sc.nextInt();
      System.out.println("************************************************************");
      if (!(selection < 1 || selection > showList.size())) {
        Showing selectedShow = showList.get(selection-1);
        cinemaReq.removeShow(selectedShow);
        main = false;
        System.out.println("Selection has been sucessfully removed");
      } else {
        System.out.println("Error: Key in a valid value");
      }
    }
  }

  public void updateShow() {
    System.out.println("Updating Showing...");
    ArrayList<Showing> showList = cinemaReq.getShowList();
    boolean main = true; 
    while(main){
      if(showList.size()==0){
        System.out.println("There are no showings to remove");
        break;
      }
      System.out.println("Key in the number of the show that you would like to update: ");
      cinemaReq.displayShowList();
      int selection = sc.nextInt();
      Showing show = showList.get(selection-1);
      System.out.println("************************************************************");
      if (!(selection < 1 || selection > showList.size())) {
        System.out.println("Update: ");
        System.out.println("[1] Movie");
        System.out.println("[2] Showtime");
        int choice = sc.nextInt();

        switch(choice){

          case 1:
            System.out.println("Updating movie of Showing..");
            selectMovie();
            show.setMovie(movieReq);
            System.out.println("Movie has been updated");
            main = false;
            break;

          case 2:
            boolean main_loop = true;
            System.out.println("Updating showtime of Showing...");
            if(main_loop){ 
              LocalDateTime dateTime = null;
              movieReq = show.getMovie();
              System.out.println("Key in the new show time in the following format (yyyyMMddHHmm): ");
              String date = sc.next();
              DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
              try{
                dateTime = LocalDateTime.parse(date, myFormatObj);
                if(dateTime.compareTo(movieReq.getEndOfShowingDate())<0){
                  show.setShowTime(dateTime);
                  System.out.println("Showtime has been updated");
                  main_loop = false;
                  break;
                }
                else{
                  System.out.println("Showtime keyed in exceeds movie's end of showing date. Please key in the date again");
                }
              } catch (Exception e) {
                System.out.println("Error: Invalid date format. Please try again");
              }
            }
            break;

          default:

            break;
        }
      } else {
        System.out.println("Error: Key in a valid value");
      }
    }
  }
}
