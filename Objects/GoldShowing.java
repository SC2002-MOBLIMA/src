package Objects;

import java.time.LocalDateTime;

import Enums.DayType;
import Enums.SeatType;

public class GoldShowing extends Showing {
  public GoldShowing(Movie movie, LocalDateTime showTime, DayType dayType) {
    super(movie, showTime, dayType);
    Seat[][] layout = new Seat[5][4]; // rows 0-6

    for (int i = 0; i < layout.length; i++) {
      for (int j = 0; j < layout[i].length; j++) {
        // SeatType seatType = i < coupleSeatRowCount ? SeatType.REGULAR : SeatType.COUPLE;
        String seatId = (char) (i + 65) + String.valueOf(j);
        layout[i][j] = new Seat(seatId, SeatType.ULTIMA);
      }
    }
    this.seatLayout = layout;
  }
}
