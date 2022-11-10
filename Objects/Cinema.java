package Objects;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

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
    int showsAvailable = 0;
    for (int i = 0; i < showList.size(); i++) {
      Showing show = showList.get(i);
      Movie movie = show.getMovie();
      System.out.println("[" + (i + 1) + "]: " + movie.getTitle() + " " + show.getFormattedTime());
      showsAvailable = 1;
    }

    if (showsAvailable == 0) {
      System.out.println("No Shows Found.");
    }
  }

  public void displayAvailableShows() { // Come back to finish this
    int index = 1;
    int showsAvailable = 0;
    for (Showing showing: showList) {
      Movie movie = showing.getMovie();
      if (movie.getStatus() == MovieStatus.NOW_SHOWING) {
        System.out.println("[" + index + "]: " + movie.getTitle() + " " + showing.getFormattedTime());
        index++;
        showsAvailable = 1;
      }
    }
    if (showsAvailable == 0) {
      System.out.println("No Shows Found.");
    }
  }

  public Showing searchShow(int searchIndex) {
    int index = 1;
    for (int i = 0; i < showList.size(); i++) {
      Showing s = showList.get(i);
      Movie m = s.getMovie();
      if (m.getStatus() == MovieStatus.NOW_SHOWING) {
        if (index == searchIndex) {
          return s;
        }
        index++;
      }
    }
    return null;
  }

  public void addShow(Movie movie, LocalDateTime showTime, DayType dayType) {
    Showing show;
    switch (cinemaType) {
      case DELUXE:
        show = new DeluxeShowing(movie, showTime, dayType);
        showList.add(show);
        break;

      case REGULAR:
        show = new RegularShowing(movie, showTime, dayType);
        showList.add(show);
        break;

      case GOLD_CLASS:
        show = new GoldShowing(movie, showTime, dayType);
        showList.add(show);
;       break;
    
      default:
        break;
    }
  }

  public void removeShow(Showing show) {
    showList.remove(show);
  }

  public void removeMovieShowings(Movie movie) {
    Iterator<Showing> i = showList.iterator();

    while (i.hasNext()) {
      Showing s = i.next();
      Movie m = s.getMovie();
      if (m.equals(movie)) {
        i.remove();
      }
    }
  }
}
