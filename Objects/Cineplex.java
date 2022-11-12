package Objects;

import java.io.Serializable;
import java.util.ArrayList;

/** 
 * Represents a contructor of a Cineplex object
 * @author S Jivaganesh
 * @version 1.0
 * @since 2022-11-11
 */

public class Cineplex implements Serializable {
  
  private String cineplexName;
  private ArrayList<Cinema> cinemaList;

  public Cineplex(String cineplexName, ArrayList<Cinema> cinemaList) {
    this.cineplexName = cineplexName;
    this.cinemaList = cinemaList;
  }
  
  
  /** 
   * Returns the Cineplex Name when called
   * @return String
   */
  public String getCineplexName() {
    return cineplexName;
  }

  
  /** 
   * Returns a List of Cinemas in the Cineplex when called
   * @return ArrayList<Cinema>
   */
  public ArrayList<Cinema> getListOfCinemas() {
    return cinemaList;
  }

  /**
   * Prints the list of cinemas
   */
  public void displayCinemas(){
    for (int i=0; i<cinemaList.size(); i++){
      System.out.println("Cinema " + cinemaList.get(i).getCinemaNum() + "\n");
    }
  }

  
  /** 
   * Removes a Showing from a Cinema in the Cineplex
   * @param movie represents the Movie object to identify which Showing to remove from the Cinema 
   */
  public void removeMovieShowings(Movie movie) {
    for (Cinema c: cinemaList) {
      c.removeMovieShowings(movie);
    }
  }
}
