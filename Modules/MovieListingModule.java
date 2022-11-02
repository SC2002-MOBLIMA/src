package Modules;

import java.util.ArrayList;
import java.util.Scanner;

import Databases.MovieDB;
import Objects.Movie;

public class MovieListingModule {
  private Scanner sc;

  public MovieListingModule(Scanner sc) {
    this.sc = sc;
  }

  public void run() {
    boolean running = true;
    while (running) {
      System.out.println("***********************************************");
      System.out.println("MOBLIMA -- Movie Listing Module:");
      MovieDB movieDB = new MovieDB();
      @SuppressWarnings("unchecked")
      ArrayList<Movie> movieList = (ArrayList<Movie>) movieDB.read();

      System.out.println("[1] Create New Movie Listing");
      System.out.println("[2] Update Movie Listing");
      System.out.println("[3] Remove Movie Listing");
      System.out.println("[4] Back");
      int choice = sc.nextInt();

      switch (choice) {
        case 1:
          break;
        case 2:
          break;
        case 3:
          System.out.println("What is the title of the movie?");
          String title = sc.next();
          for (Movie m : movieList) {
            if (m.getTitle().equalsIgnoreCase(title)) {
              movieList.remove(m);
            }
          }
          movieDB.write(movieList);
          break;
        case 4:
          running = false;
          break;
      }
    }

  }
}
