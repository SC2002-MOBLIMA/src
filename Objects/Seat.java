package Objects;

import java.io.Serializable;

public class Seat implements Serializable {
  private String id;
  private MovieGoer movieGoer;

  public Seat(String id) {
    this.id = id;
    this.movieGoer = null;
  }

  public MovieGoer getMovieGoer() {
    return movieGoer;
  }

  public String getId() {
    return id;
  }

  public void assignSeat(MovieGoer movieGoer) {
    this.movieGoer = movieGoer;
  }

  public boolean isAvailable() {
    if (movieGoer == null) {
      return true;
    } else {
      return false;
    }
  }
  
}
