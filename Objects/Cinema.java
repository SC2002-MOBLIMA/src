package Objects;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

import Enums.CinemaType;
import Enums.DateType;
import Enums.MovieStatus;

public class Cinema implements Serializable {

  private int cinemaNum;
  private String cinemaCode;
  private CinemaType cinemaType;
  private ArrayList<Showing> showList;

  public Cinema(int cinemaNum, String cinemaCode, CinemaType cinemaType) {
    this.cinemaNum = cinemaNum;
    this.cinemaCode = cinemaCode;
    this.cinemaType = cinemaType;
    this.showList = new ArrayList<Showing>();
  }

  public ArrayList<Showing> getShowList() {
    return this.showList;
  }

  public int getCinemaNum() {
    return this.cinemaNum;
  }

  public String getCinemaCode() {
    return this.cinemaCode;
  }

  public CinemaType getCinemaType() {
    return this.cinemaType;
  }

  public void displayShowList() {
    for (int i = 0; i < showList.size(); i++) {
      Showing show = showList.get(i);
      Movie movie = show.getMovie();
      System.out.println("[" + (i + 1) + "]: " + movie.getTitle() + " " + show.getFormattedTime());
      // System.out.println(showList.get(i).getMovieTitle());
      // System.out.println("Show Timte: " + showList.get(i).getShowTime());
    }
  }

  public void displayAvailableShows() { // Come back to finish this
    // int index = 1;
    int showsAvailable = 0;
    for (Showing showing: showList) {
      Movie movie = showing.getMovie();
      if (movie.getStatus() == MovieStatus.NOW_SHOWING) {
        System.out.println("[" + showing.getId() + "]: " + movie.getTitle() + " " + showing.getFormattedTime());
        // index++;
        showsAvailable = 1;
      }
    }
    if (showsAvailable == 0) {
      System.out.println("No Shows Found.");
    }
  }

  public Showing searchShow(int id) {
    for (int i = 0; i < showList.size(); i++) {
      int check = showList.get(i).getId();
      if (check == id) {
        return showList.get(i);
      }
    }
    return null;
  }

  public void addShow(Movie movie, LocalDateTime showTime, DateType dateType) {
    Showing show = new Showing(movie, showTime, dateType);
    showList.add(show);
  }

  public void removeShow(Showing show) {
    showList.remove(show);
  }
}
