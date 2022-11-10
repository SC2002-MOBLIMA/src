package Objects;

import java.time.LocalDateTime;

import Enums.DayType;
import Enums.SeatType;

// // For Testing
// import Enums.*;
// import java.util.ArrayList;

public class RegularShowing extends Showing {
  public RegularShowing(Movie movie, LocalDateTime showTime, DayType dayType) {
    super(movie, showTime, dayType);
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
}
