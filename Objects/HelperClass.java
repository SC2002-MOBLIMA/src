package Objects;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import Databases.MovieDB;
import Enums.MovieStatus;
import Enums.MovieType;

public class HelperClass {
  public LocalDate parseDate(String date) {
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyyMMdd");

    try {
      LocalDate editted_date = LocalDate.parse(date, myFormatObj);
      return editted_date;
    } catch (Exception e) {
      return null;
    }
  }

  public LocalTime parseTime(String time) {
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("hhMM");

    try {
      LocalTime editted_time = LocalTime.parse(time, myFormatObj);
      return editted_time;
    } catch (Exception e) {
      return null;
    }
  }

  public void searchMovies(String phrase, boolean detailed) {
    MovieDB movieDB = new MovieDB();
    ArrayList<Movie> movieList = (ArrayList<Movie>) movieDB.read();

    for (int i = 0; i < movieList.size(); i++) {
      if ((movieList.get(i).getTitle().toLowerCase()).contains(phrase.toLowerCase())) {
        if (detailed) {
          System.out.println("Movie Title: " + movieList.get(i).getTitle());
          MovieStatus select = movieList.get(i).getStatus();
          switch (select) {
            case COMING_SOON:
              System.out.println("Movie Status: Coming Soon");
              break;

            case PREVIEW:
              System.out.println("Movie Status: Preview");
              break;

            case NOW_SHOWING:
              System.out.println("Movie Status: Now Showing");
              break;

            case END_OF_SHOWING:
              System.out.println("Movie Status: End of Showing");
              break;

            default:
              break;
          }
          System.out.println("Synopsis: " + movieList.get(i).getSynopsis());
          System.out.println("Director: " + movieList.get(i).getDirector());
          ArrayList<String> castList = movieList.get(i).getCast();
          System.out.println("Cast: ");

          for (int j = 0; j < castList.size(); j++) {
            System.out.println(castList.get(j));
          }
          MovieType select_next = movieList.get(i).getType();
          switch (select_next) {
            case REGULAR:
              System.out.println("Movie Type: Regular");
              break;

            case THREE_D:
              System.out.println("Movie Type: 3D");
              break;

            case BLOCKBUSTER:
              System.out.println("Movie Type: Blockbuster");
              break;
          }
          System.out.println("----------------");
        } else {
          System.out.println("Movie Title: " + movieList.get(i).getTitle());
        }
      }
    }
  }
}
