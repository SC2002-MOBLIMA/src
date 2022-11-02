package Objects;

import java.util.ArrayList;

enum Agetype {
    CHILD,
    ADULT,
    SENIOR
}

public class MovieGoer {

    private String name;
    private String mobile;
    private Agetype agetype;
    private String email;
    private int movieGoerID;
    private ArrayList<MovieTicket> movieTicketList;

    public MovieGoer(String name, String mobile, Agetype agetype, String email, int movieGoerID,
            ArrayList<MovieTicket> movieTicketList) {
        this.name = name;
        this.mobile = mobile;
        this.agetype = agetype;
        this.email = email;
        this.movieGoerID = movieGoerID;
        this.movieTicketList = movieTicketList;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getMobile() {
        return this.mobile;
    }

    public int getMovieGoerID() {
        return this.movieGoerID;
    }

    public Agetype getAgeType() {
        return this.agetype;
    }

    public ArrayList<MovieTicket> getMovieTicketList() {
        return this.movieTicketList;
    }

    public void addMovieTicket(MovieTicket movieTicket) {
        movieTicketList.add(movieTicket);
    }
}
