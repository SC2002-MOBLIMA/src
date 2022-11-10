package Objects;

import java.time.LocalDateTime;

import Enums.DateType;
import Enums.SeatType;

// // For Testing
// import Enums.*;
// import java.util.ArrayList;

public class RegularShowing extends Showing {
  public RegularShowing(Movie movie, LocalDateTime showTime, DateType dateType) {
    super(movie, showTime, dateType);
    Seat[][] layout = new Seat[9][8]; // rows 1 - 8

    int coupleSeatRowCount = layout.length - 2; // 2 rows of couple seats | rows 7-8

    for (int i = 0; i < layout.length; i++) {
      for (int j = 0; j < layout[i].length; j++) {
        SeatType seatType = i < coupleSeatRowCount ? SeatType.REGULAR : SeatType.COUPLE;
        String seatId = (char) (i + 65) + String.valueOf(j);
        layout[i][j] = new Seat(seatId, seatType);
      }
    }
    this.seatLayout = layout;
  }

  // public static void main(String[] args) {
  //   ArrayList<String> cast = new ArrayList<>();
  //   cast.add("ABC");
  //   cast.add("ABC");
  //   String[] c = { "A", "Iron B" };
  //   Movie m = new Movie("Spider Man", MovieStatus.NOW_SHOWING, "Spider Man", "Spider Man", cast, MovieType.BLOCKBUSTER, LocalDateTime.now());
  //   LocalDateTime lTime = LocalDateTime.now();
  //   DateType dateType = DateType.WEEKEND;
  //   Showing showing = new RegularShowing(m, lTime, dateType);

  //   showing.getFormattedTime();
  //   showing.printSeating();
  //   System.out.println();

  //   MovieGoer movieGoer = new MovieGoer(null, null, null, null);
  //   Seat[][] layout = showing.getSeatLayout();
  //   layout[0][0].assignSeat(movieGoer);
  //   showing.printSeating();
  //   System.out.println(showing.isAvailable("A1"));

  //   showing.assignSeat(movieGoer, "A2");
  //   showing.assignSeat(movieGoer, "I1");
  //   showing.assignSeat(movieGoer, "H6");
  //   showing.assignSeat(movieGoer, "H5");
  //   showing.printSeating();
  // }
}
