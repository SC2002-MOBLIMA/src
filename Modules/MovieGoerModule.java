package Modules;

import java.util.Scanner;
import java.io.*;
import java.util.*;
import Databases.MoviesDB;

import java.util.ArrayList;
import Objects.Cinema;
import Objects.Showing;
import Objects.MovieGoer;
import Objects.Movie;
import Objects.MovieStatus;
import Comparators.SortByRating;

public class MovieGoerModule implements Comparator<Movie> {
  private Scanner sc;
  private boolean running;
  private boolean signedIn;
  private MovieGoer movieGoerObject;
  private static ArrayList<Movie> allmovies;

  public void printMovieSearch(String phrase, Boolean detailed, ArrayList<Movie> movies) {
    for (Movie m : movies) {
      if (m.movieStatus == (MovieStatus.SHOWING) && m.title.contains(phrase)) {
        if (detailed == false) {
          System.out.println(m.title);
        } else {
          System.out.println(m);
        }
      }
    }
  }

  public void printMovieByRating() {
    allmovies = MoviesDB.getMovies();
    Collections.sort(allmovies);
  }

  public void run() {
    System.out.println("MovieGoer Module Running...");
    System.out.println("(1)-Search Movies\n"
        + "(2)-List movies\n"
        + "(3)- View movie details\n"
        + "(4)-Check seat availability\n"
        + "(5)-Book Seats\n"
        + "(6)-View Booking history\n"
        + "(7)-List top 5 movies based on sales\n"
        + "(8)-List top 5 movies based on ratings\n");
  }

}
