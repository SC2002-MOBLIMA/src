package Objects;
import java.util.ArrayList;

public class Cineplex {
  
  private String cineplexName;
  private ArrayList<Cinema> cinemaList;

  public Cineplex(String cineplexName) {
    this.cineplexName = cineplexName;
  }
  
  public String getCineplexName() {
    return cineplexName;
  }

  public ArrayList<Cinema> getListOfCinemas() {
    return cinemaList;
  }

  public void displayCinemas(){
    for(int i=0; i<cinemaList.size(); i++){
      System.out.println("Cinema " + cinemaList.get(i).getCinemaNum() + "\n");
    }
  }

  public void addCinema(Cinema cinema){
    cinemaList.add(cinema);
  }

  public void deleteCinema(Cinema cinema){
    cinemaList.remove(cinema);
  }
}
