package Objects;
import java.util.ArrayList;
import Enums.CinemaType;
import java.time.LocalDateTime;
import Enums.MovieStatus;

import java.util.ArrayList;

public class Cinema {
  
    private int cinemaNum;
    private String cinemaCode;
    private CinemaType cinemaType;
    private ArrayList<Showing> showList;

    public Cinema(int cinemaNum, String cinemaCode, CinemaType cinemaType){
        this.cinemaNum = cinemaNum;
        this.cinemaCode  = cinemaCode;
        this.cinemaType = cinemaType;
    }

    public ArrayList<Showing> getShowList(){
        return showList;
    }

    public int getCinemaNum(){
        return cinemaNum;
    }

    public String getCinemaCode(){
        return cinemaCode;
    }

    public CinemaType getCinemaType(){
        return cinemaType;
    }

    public void displayShowList(){
        for(int i=0; i<showList.size(); i++){
            System.out.println("[" + (i+1) + "]: " + showList.get(i).getMovieTitle() + " " + showList.get(i).getFormattedTime());
            // System.out.println(showList.get(i).getMovieTitle());
            // System.out.println("Show Timte: " + showList.get(i).getShowTime());
        }
    }

    public void displayAvailableShows(){ //Come back to finish this
        for(int i=0;i<showList.size(); i++){
            if((showList.get(i).getMovie()).getStatus() == 3){
                System.out.println("[" + (i+1) + "]: " + showList.get(i).getMovieTitle() + " " + showList.get(i).getFormattedTime());
                // System.out.println(showList.get(i).getMovieTitle());
                // System.out.println("Show Timte: " + showList.get(i).getShowTime());

            }
        }
    }

    public Showing searchShow(int id){
        for(int i=0; i<showList.size(); i++){
            int check = showList.get(i).getId();
            if(check == id){
               return showList.get(i);
            }
        }
        return null;
    }

    public void addShow(Movie movie, LocalDateTime showTime, MovieStatus status){
        Showing show = new Showing(movie, showTime);
        showList.add(show);
    }

    public void removeShow(Showing show){
        showList.remove(show);
    }

}
