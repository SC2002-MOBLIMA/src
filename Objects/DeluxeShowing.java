package Objects;

import java.time.LocalDateTime;

import Enums.DateType;
import Enums.SeatType;

// // For Testing
import Enums.*;
import java.util.ArrayList;

public class DeluxeShowing extends Showing {
  public DeluxeShowing(Movie movie, LocalDateTime showTime, DateType dateType) {
    super(movie, showTime, dateType);
    Seat[][] layout = new Seat[7][6]; // rows 0-6

    for (int i = 0; i < layout.length; i++) {
      for (int j = 0; j < layout[i].length; j++) {
        // SeatType seatType = i < coupleSeatRowCount ? SeatType.REGULAR : SeatType.COUPLE;
        String seatId = (char) (i + 65) + String.valueOf(j);
        layout[i][j] = new Seat(seatId, SeatType.ELITE);
      }
    }
    this.seatLayout = layout;
  }

  public static void main(String[] args) {
    ArrayList<String> cast = new ArrayList<>();
    cast.add("ABC");
    cast.add("ABC");
    String[] c = { "A", "Iron B" };
    Movie m = new Movie("Spider Man", MovieStatus.NOW_SHOWING, "Spider Man", "Spider Man", cast, MovieType.BLOCKBUSTER, LocalDateTime.now());
    LocalDateTime lTime = LocalDateTime.now();
    DateType dateType = DateType.WEEKEND;
    Showing showing = new DeluxeShowing(m, lTime, dateType);

    showing.getFormattedTime();
    showing.printSeating();
    System.out.println();

    MovieGoer movieGoer = new MovieGoer(null, null, null, null);
    Seat[][] layout = showing.getSeatLayout();
    layout[0][0].assignSeat(movieGoer);
    showing.printSeating();
    System.out.println(showing.isAvailable("A1"));

    showing.assignSeat(movieGoer, "A2");
    showing.assignSeat(movieGoer, "E1");
    showing.assignSeat(movieGoer, "E4");
    showing.assignSeat(movieGoer, "E3");
    showing.printSeating();
  }
}
