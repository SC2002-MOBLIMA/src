package Databases;

import java.util.ArrayList;
import Objects.Cineplex;

public class CineplexDB {
  public CineplexDB() {}
  public static ArrayList<Cineplex> getCineplexList() {
    // TODO: change this
    ArrayList<Cineplex> temp = new ArrayList<Cineplex>();
    temp.add(new Cineplex("PlaceholderCineplex"));
    return temp;
  }
  public static void setCineplexList() {}
}
