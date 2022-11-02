package Objects;
import java.util.ArrayList;
import Enums.CinemaType;
import java.time.LocalDateTime;
import Enums.MovieStatus;

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

    public void searchShow(String keyword){
        int counter = 0;
        for(int i=0; i<showList.size(); i++){
            String movietitle = showList.get(i).getMovieTitle();
            if(movietitle.contains(keyword)){
                System.out.println("[" + (i+1) + "]: " + showList.get(i).getMovieTitle() + " " + showList.get(i).getFormattedTime());
                counter+=1;
                // System.out.println("Show Time: " + showList.get(i).getFormattedTime() + "\n");
            }
            if(counter==0){
                System.out.println("No movies found");
            }
        }
    }

    public void addShow(Movie movie, LocalDateTime showTime, MovieStatus status){
        Showing show = new Showing(movie, showTime);
        showList.add(show);
    }

    public void removeShow(Showing show){
        showList.remove(show);
    }

}
