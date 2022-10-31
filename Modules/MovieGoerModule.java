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

  public void printMovieByRating(ArrayList<Movie> movies) {
    allmovies = MoviesDB.getMovies();
  }

  public void run() {
    System.out.println("MovieGoer Module Running...");
  }

}
