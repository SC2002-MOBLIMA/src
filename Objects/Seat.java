package Objects;

import java.io.Serializable;

import Enums.SeatType;

public class Seat implements Serializable {
    private String id;
    private MovieGoer movieGoer;
    private SeatType seatType;

    public Seat(String id, SeatType seatType) {
        this.id = id;
        this.movieGoer = null;
        this.seatType = seatType;
    }

    public MovieGoer getMovieGoer() {
        return movieGoer;
    }

    public String getId() {
        return id;
    }

    public void assignSeat(MovieGoer movieGoer) {
        this.movieGoer = movieGoer;
    }

    
    public void printSeat() {

        // Seat Legend:
        // REGULAR: [ ][ ] | [X][ ] | [X][X]
        // COUPLE:  [    ] | [X   ] | [X  X]
        // ELITE:   { }{ } | {X}{ } | {X}{X}
        // ULTIMA:  {    } | {X   } | {X  X}

        if (id == null) {
            return;
        }
        char columnChar = id.charAt(1);
        int column = Character.getNumericValue(columnChar);
        boolean left = column % 2 == 0 ? true : false;

        switch (seatType) {
            case REGULAR:
                if (isAvailable()) {
                    System.out.print("[ ]");
                } else {
                    System.out.print("[X]");
                }
                break;

            case COUPLE:
                if (left && isAvailable()) {
                    System.out.print("[  ");
                } else if (left && !isAvailable()) {
                    System.out.print("[X ");
                } else if (!left && isAvailable()) {
                    System.out.print("  ]");
                } else {
                    System.out.print(" X]");
                }
                break;

            case ELITE:
                if (isAvailable()) {
                    System.out.print("{ }");
                } else {
                    System.out.print("{X}");
                }
                break;

            case ULTIMA:
                if (left && isAvailable()) {
                    System.out.print("{  ");
                } else if (left && !isAvailable()) {
                    System.out.print("{X ");
                } else if (!left && isAvailable()) {
                    System.out.print("  }");
                } else {
                    System.out.print(" X}");
                }
                break;

            default:
                break;
        }
    }
    
    public boolean isAvailable() {
        if (movieGoer == null) {
            return true;
        } else {
            return false;
        }
    }
}
