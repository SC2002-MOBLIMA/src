package Modules;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import Databases.MovieDB;
import Enums.MovieStatus;
import Enums.MovieType;
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
          getNewMovieListing(movieList, movieDB);
          break;

        case 2:
          updateMovieListing(movieList, movieDB);
          break;

        case 3:
          removeMovieListing(movieList, movieDB);
          break;

        case 4:
          running = false;
          System.out.println("Movie Listing Module stoppping");
          System.out.println("***********************************************");
          break;
      }
    }
  }

  private void updateMovieListing(ArrayList<Movie> movieList, MovieDB movieDB) {
    System.out.println("***********************************************");
    System.out.println("MOBLIMA -- Movie Listing Module ( Update Existing Movie Listing):");
    System.out.println("What is the title of the movie?");
    String title = sc.next();
    for (Movie m : movieList) {
      if (m.getTitle().equalsIgnoreCase(title)) {
        movieList.remove(m);
        Movie updatedMovie = getUpdatedMovie(m);
        movieList.add(updatedMovie);
      }
    }
    movieDB.write(movieList);
    System.out.println("Movie Listing successfully updated");
    System.out.println("***********************************************");
  }

  private Movie getUpdatedMovie(Movie movie) {
    boolean run = true;

    do {
      System.out.println("******************************");
      System.out.println("Possible List of Updates:");
      System.out.println("[1] Status");
      System.out.println("[2] Sale Count");
      System.out.println("[3] Type");
      System.out.println("[4] End Of Showing Date");
      System.out.println("[5] Back/ Done");
      System.out.println("******************************");

      System.out.println("What do you want to update?");
      int choice = sc.nextInt();

      switch (choice) {
        case 1:
          System.out.println("Input new status (COMING_SOON, PREVIEW, NOW_SHOWING)");
          MovieStatus status = MovieStatus.valueOf(sc.next());
          movie.setStatus(status);
          break;
        case 2:
          System.out.println("Input new sale count");
          int saleCount = sc.nextInt();
          movie.setSaleCount(saleCount);
          break;
        case 3:
          System.out.println("Input new type (REGULAR, THREE_D, BLOCKBUSTER)");
          MovieType type = MovieType.valueOf(sc.next());
          movie.setType(type);
          break;
        case 4:
          System.out.println("Input new End of Showing Date (YYYY-MM-DDT00:00:00)");
          String dateString = sc.next();
          LocalDateTime endOfShowingDate = LocalDateTime.parse(dateString);
          movie.setEndOfShowingDate(endOfShowingDate);
          break;
        case 5:
          run = false;
          break;
        default:
          System.out.println("Invalid choice");
          break;
      }
    } while (run);

    return movie;
  }

  // Update the status of the Movie Listing as "END_OF_SHOWING"
  private void removeMovieListing(ArrayList<Movie> movieList, MovieDB movieDB) {
    System.out.println("***********************************************");
    System.out.println("MOBLIMA -- Movie Listing Module (Remove Movie Listing):");
    System.out.println("What is the title of the movie?");
    String title = sc.next();
    for (Movie m : movieList) {
      if (m.getTitle().equalsIgnoreCase(title)) {
        movieList.remove(m);
        m.setStatus(MovieStatus.COMING_SOON);
        movieList.add(m);
      }
    }
    movieDB.write(movieList);
    System.out.println("Movie Listing successfully removed");
    System.out.println("***********************************************");

  }

  private void getNewMovieListing(ArrayList<Movie> movieList, MovieDB movieDB) {
    System.out.println("***********************************************");
    System.out.println("MOBLIMA -- Movie Listing Module (Create New Movie Listing):");
    System.out.println("Input Movie Title:");
    String title = sc.nextLine();
    System.out.println("Input Movie Status (COMING_SOON, PREVIEW, NOW_SHOWING, END_OF_SHOWING):");
    MovieStatus status = MovieStatus.valueOf(sc.next());
    System.out.println("Input Movie Synopsis:");
    String synopsis = sc.nextLine();

    System.out.println("Input Movie Director:");
    String director = sc.nextLine();

    ArrayList<String> cast = getCast();

    System.out.println("Input Movie Type:");
    MovieType type = MovieType.valueOf(sc.next());

    System.out.println("Input Movie End Of Showing Date (yyyyMMdd):");
    String dateString = sc.next();
    LocalDateTime endOfShowingDate = LocalDateTime.parse(dateString);

    Movie newMovie = new Movie(title, status, synopsis, director, cast, type, endOfShowingDate);

    movieList.add(newMovie);
    movieDB.write(movieList);

    System.out.println("New Movie Listing successfully added");
    System.out.println("***********************************************");
  }

  private ArrayList<String> getCast() {
    ArrayList<String> cast = new ArrayList<String>();

    int numberOfCast = 0;

    boolean run = true;
    do {
      System.out.println("Input number of cast members:");
      numberOfCast = sc.nextInt();
      if (numberOfCast < 2) {
        System.out.println("There must be more than at least 2 cast members!");
      } else {
        run = false;
      }
    } while (run);

    for (int i = 0; i < numberOfCast; i++) {
      System.out.println("Input Movie Cast:");
      String castMember = sc.nextLine();
      cast.add(castMember);
    }

    return cast;
  }
}
