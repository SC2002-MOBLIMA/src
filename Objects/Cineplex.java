package Objects;

import java.util.ArrayList;
public class Cineplex {
  private String cineplexName;
  private ArrayList<Cinema> cinemaList;
  public Cineplex(String cineplexName) {
    this.cineplexName = cineplexName;
  };
  public String getCineplexName() {
    return cineplexName;
  }
  public ArrayList<Cinema> getListOfCinemas() {
    return cinemaList;
  }
}
