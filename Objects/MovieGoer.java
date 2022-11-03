package Objects;

import java.io.Serializable;
import java.util.ArrayList;

import Enums.AgeType;

public class MovieGoer implements Serializable {
    private String name;
    private String mobile;
    private AgeType agetype;
    private String email;
    private ArrayList<MovieTicket> movieTicketList;

    public MovieGoer(String name, String mobile, AgeType agetype, String email) {
        this.name = name;
        this.mobile = mobile;
        this.agetype = agetype;
        this.email = email;
        this.movieTicketList = new ArrayList<MovieTicket>();
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

    public AgeType getAgeType() {
        return this.agetype;
    }

    public ArrayList<MovieTicket> getMovieTicketList() {
        return this.movieTicketList;
    }

    public void addMovieTicket(MovieTicket movieTicket) {
        movieTicketList.add(movieTicket);
    }
}
