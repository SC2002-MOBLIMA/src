package Objects;

import java.io.Serializable;
import java.util.ArrayList;

public class Cineplex implements Serializable {
  
  private String cineplexName;
  private ArrayList<Cinema> cinemaList;

  public Cineplex(String cineplexName, ArrayList<Cinema> cinemaList) {
    this.cineplexName = cineplexName;
    this.cinemaList = cinemaList;
  }
  
  public String getCineplexName() {
    return cineplexName;
  }

  public ArrayList<Cinema> getListOfCinemas() {
    return cinemaList;
  }

  public void displayCinemas(){
    for (int i=0; i<cinemaList.size(); i++){
      System.out.println("Cinema " + cinemaList.get(i).getCinemaNum() + "\n");
    }
  }

  public void removeMovieShowings(Movie movie) {
    for (Cinema c: cinemaList) {
      c.removeMovieShowings(movie);
    }
  }
}
