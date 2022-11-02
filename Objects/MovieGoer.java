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
    private int customerID;
    private ArrayList<MovieTicket> movieTicketList;

    public MovieGoer(String name, String mobile, Agetype agetype, String email, int customerID,
            ArrayList<MovieTicket> movieTicketList) {
        this.name = name;
        this.mobile = mobile;
        this.agetype = agetype;
        this.email = email;
        this.customerID = customerID;
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

    public int getCustomerID() {
        return this.customerID;
    }

    public Agetype getAgeType() {
        return this.agetype;
    }

    public ArrayList<MovieTicket> getmovieTicketList() {
        return this.movieTicketList;
    }

    public void addTransaction(MovieTicket movieTicket) {
        movieTicketList.add(movieTicket);
    }
}
