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
    private ArrayList<MovieTicket> TransactionList;

    public MovieGoer(String name, String mobile, Agetype agetype, String email, int customerID,
            ArrayList<MovieTicket> TransactionList) {
        this.name = name;
        this.mobile = mobile;
        this.agetype = agetype;
        this.email = email;
        this.customerID = customerID;
        this.TransactionList = TransactionList;
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

    public ArrayList<MovieTicket> getTransactionList() {
        return this.TransactionList;
    }

    public void addTransaction(MovieTicket movieTicket) {
        TransactionList.add(movieTicket);
    }
}
