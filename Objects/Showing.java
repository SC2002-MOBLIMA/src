package Objects;

import java.time.LocalDateTime;
import java.lang.Character;
import java.lang.String;
import Enums.DateType;
// import Enums.*;

public class Showing {

  private int id;
  private Movie movie;
  private LocalDateTime showTime; // Exact Date and Time
  private DateType dateType; // Weekend/ Weekday/ PublicHoliday
  private Seat[][] seatLayout; // 10 rows, 9 columns

  public Showing(Movie movie, LocalDateTime showTime) {
    this.movie = movie;
    this.showTime = showTime;

    Seat[][] layout = new Seat[10][9];
    for (int i = 0; i < layout.length; i++) {
      for (int j = 0; j < layout[i].length; j++) {
        String seatId = (char) (i + 65) + String.valueOf(j);
        layout[i][j] = new Seat(seatId);
        // System.out.println(seatId);
      }
    }
    this.seatLayout = layout;
  }

  public int getId() {
    return this.id;
  }

  public LocalDateTime getShowTime() {
    return this.showTime;
  }

  public void setShowTime(LocalDateTime showTime) {
    this.showTime = showTime;
  }

  public String getMovieTitle() {
    return movie.getTitle();
  }

  public void printSeating() {
    for (int i = 0; i < this.seatLayout.length; i++) {
      for (int j = 0; j < this.seatLayout[j].length; j++) {
        if (this.seatLayout[i][j].isAvailable()) {
          System.out.print("[ ]");
        } else {
          System.out.print("[X]");
        }
        if (j == this.seatLayout[j].length - 1) {
          System.out.println("\n");
        }
      }
    }
  }

  public boolean isAvailable(String seatId) {
    int row = (int) seatId.charAt(0) - 65;
    // System.out.println(row);
    int column = Character.valueOf(seatId.charAt(1)) - 48;
    // System.out.println(column);
    Seat seat = this.seatLayout[row][column];
    // System.out.println("Seat is available: " +
    // seat.isAvailable());
    return seat.isAvailable();
  }

  public void assignSeat(MovieGoer movieGoer, String seatId) {
    int row = (int) seatId.charAt(0) - 65;
    int column = Character.valueOf(seatId.charAt(1)) - 48;
    Seat seat = this.seatLayout[row][column];
    seat.assignSeat(movieGoer);
  }

  // TODO:
  // + getDateType(): String
  // + getFormattedTime(): String

  // // Testing
  // public Seat[][] getSeatLayout() {
  //   return this.seatLayout;
  // }

  // public static void main(String[] args) {
  //   String[] c = { "A", "Iron B" };
  //   Movie m = new Movie("Spider Man", MovieStatus.COMING_SOON, "Spider Man",
  //       "Spider Man", c, 0, MovieType.BLOCKBUSTER,
  //       "ABC");
  //   LocalDateTime lTime = LocalDateTime.now();
  //   Showing showing = new Showing(m, lTime);
  //   Seat[][] layout = showing.getSeatLayout();
  //   layout[0][0].assignSeat(new MovieGoer());
  //   showing.printSeating();
  //   System.out.println("new");
  //   showing.isAvailable("A0");
  //   showing.isAvailable("A1");
  //   showing.assignSeat(new MovieGoer(), "A1");
  //   showing.printSeating();
  // }
}