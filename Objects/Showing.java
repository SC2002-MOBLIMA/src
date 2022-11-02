package Objects;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import Enums.DateType;
// import java.util.ArrayList;
// import Enums.*;

public class Showing {

  private int id;
  private Movie movie;
  private LocalDateTime showTime; // Exact Date and Time
  private DateType dateType; // Weekend/ Weekday/ PublicHoliday
  private Seat[][] seatLayout; // 9 rows, 10 columns

  public Showing(Movie movie, LocalDateTime showTime, DateType dateType) {
    this.movie = movie;
    this.showTime = showTime;
    this.dateType = dateType;

    Seat[][] layout = new Seat[9][10];
    for (int i = 0; i < layout.length; i++) {
      for (int j = 0; j < layout[i].length; j++) {
        String seatId = (char) (i + 65) + String.valueOf(j);
        layout[i][j] = new Seat(seatId);
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

  public Movie getMovie() {
    return this.movie;
  }

  public String getMovieTitle() {
    return this.movie.getTitle();
  }

  public DateType getDateType() {
    return this.dateType;
  }

  public String getFormattedTime() {
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
    String formattedDate = showTime.format(myFormatObj);
    System.out.println(formattedDate);
    return formattedDate;
  }

  public void printSeating() {
    for (int i = 0; i < this.seatLayout.length; i++) {
      for (int j = 0; j < this.seatLayout[i].length; j++) {
        if (this.seatLayout[i][j].isAvailable()) {
          System.out.print("[ ]");
        } else {
          System.out.print("[X]");
        }
        if (j == 4) {
          System.out.print("\t\t");
        }
        if (j == this.seatLayout[i].length - 1) {
          System.out.println();
        }
      }
    }
  }

  public boolean isAvailable(String seatId) {
    int row = (int) seatId.charAt(0) - 65;
    int column = Character.valueOf(seatId.charAt(1)) - 48;
    Seat seat = this.seatLayout[row][column];
    return seat.isAvailable();
  }

  public void assignSeat(MovieGoer movieGoer, String seatId) {
    int row = (int) seatId.charAt(0) - 65;
    int column = Character.valueOf(seatId.charAt(1)) - 48;
    Seat seat = this.seatLayout[row][column];
    seat.assignSeat(movieGoer);
  }

  // Testing
  public Seat[][] getSeatLayout() {
    return this.seatLayout;
  }

  // public static void main(String[] args) {
  // ArrayList<String> cast = new ArrayList<>();
  // cast.add("ABC");
  // cast.add("ABC");
  // String[] c = { "A", "Iron B" };
  // Movie m = new Movie("Spider Man", MovieStatus.COMING_SOON, "Spider Man",
  // "Spider Man", cast, 0, MovieType.BLOCKBUSTER, "ABC");
  // LocalDateTime lTime = LocalDateTime.now();
  // DateType dateType = DateType.WEEKEND;
  // Showing showing = new Showing(m, lTime, dateType);

  // showing.getFormattedTime();
  // showing.printSeating();
  // System.out.println();

  // ArrayList<MovieTicket> TransactionList = new ArrayList<MovieTicket>();
  // MovieGoer movieGoer = new MovieGoer("null", "null", Agetype.ADULT, "null", 0,
  // TransactionList);
  // Seat[][] layout = showing.getSeatLayout();
  // layout[0][0].assignSeat(movieGoer);
  // showing.printSeating();
  // System.out.println(showing.isAvailable("A0"));
  // System.out.println(showing.isAvailable("A1"));

  // showing.assignSeat(movieGoer, "A1");
  // showing.printSeating();
  // }
}
