package Modules;

import java.util.Scanner;
import java.io.*;
import java.util.*;
import Databases.MovieDB;
import Databases.MovieGoerDB;

import java.util.ArrayList;
import Objects.Cinema;
import Objects.Showing;
import Objects.MovieGoer;
import Objects.Movie;
import Enums.MovieStatus;
import Comparators.SortByRating;
import Comparators.SortBySales;

public class MovieGoerModule {
  private Scanner sc;
  private boolean running;
  private boolean signedIn;
  private MovieGoer movieGoerObject;
  private ArrayList<Movie> allmovies;

  public MovieGoerModule(Scanner sc) {
    this.sc = sc;
  }

  public void printMovieSearch(String phrase, Boolean detailed, ArrayList<Movie> movies) {
    for (Movie m : movies) {
      if (m.getStatus() == (MovieStatus.NOW_SHOWING) && m.getTitle().contains(phrase)) {
        if (detailed == false) {
          System.out.println(m.getTitle());
        } else {
          System.out.println(m);
        }
      }
    }
  }

  public void printMovieByRating() {
    int counter = 0;
    MovieDB movieDB = new MovieDB();
    @SuppressWarnings("unchecked")
    ArrayList<Movie> movieList = (ArrayList<Movie>) movieDB.read();
    Collections.sort(movieList, new SortByRating());
    for (Movie m : movieList) {
      while (counter < 5) {
        System.out.println(m.getTitle());
        counter++;
      }
    }
  }

  public void printMovieBySales() {
    int counter = 0;
    MovieDB movieDB = new MovieDB();
    @SuppressWarnings("unchecked")
    ArrayList<Movie> movieList = (ArrayList<Movie>) movieDB.read();
    Collections.sort(movieList, new SortBySales());
    for (Movie m : movieList) {
      while (counter < 5) {
        System.out.println(m.getTitle());
        counter++;
      }
    }
  }

  public void run() {
    MovieDB movieDB = new MovieDB();
    MovieGoerDB movieGoerDB = new MovieGoerDB();
    @SuppressWarnings("unchecked")
    ArrayList<Movie> readMovies = (ArrayList<Movie>) movieDB.read();
    allmovies = readMovies;
    String keywords = "";
    System.out.print("Please enter your username: ");
    String username = sc.next();
    if (movieGoerDB.checkMovieGoerExists(username)) {
      int input = 0;
      System.out.println("MovieGoer Module Running...");
      while (input != 9) {
        System.out.println("[1] Search Movies\n"
            + "[2] List movies\n"
            + "[3] View movie details\n"
            + "[4] Book Seats\n"
            + "[5] View Booking history\n"
            + "[6] List top 5 movies based on sales\n"
            + "[7] List top 5 movies based on ratings\n"
            + "[8] Exit");
        System.out.print("Please select an option: ");
        input = sc.nextInt();
        switch (input) {
          case 1:
            System.out.println("Please enter the keywords: ");
            keywords = sc.nextLine();
            printMovieSearch(keywords, true, allmovies);
            keywords = "";
            break;
          case 2:
            printMovieSearch(keywords, true, allmovies);
            break;
          case 3:
            printMovieSearch(keywords, true, allmovies);
            break;
          case 4:
            BookingModule bookingModule = new BookingModule(sc, movieGoerObject);
            bookingModule.run();
            break;
          case 5:
            System.out.println(movieGoerObject.getTransactionList());
            break;
          case 6:
            printMovieBySales();
            break;
          case 7:
            printMovieByRating();
            break;
          case 8:
            break;
        }
      }
    }

  }
}
