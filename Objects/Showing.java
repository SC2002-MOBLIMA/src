package Objects;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Enums.DateType;
import Enums.SeatType;

import Databases.CineplexDB;

// // For Testing
// import Enums.*;
// import java.util.ArrayList;

public class Showing implements Serializable {

  private int id;
  private Movie movie;
  private LocalDateTime showTime; // YYYY-MM-DDT00:00:00
  private DateType dateType; // Weekend/ Weekday/ PublicHoliday
  private Seat[][] seatLayout; // 9 rows, 10 columns

  public Showing(Movie movie, LocalDateTime showTime, DateType dateType) {
    this.movie = movie;
    this.showTime = showTime;
    this.dateType = dateType;

    Seat[][] layout = new Seat[9][8]; // rows 0 - 8

    int ultimaSeatRowCount = layout.length - 1; // 1 row of ultima / elite seats | row 8
    int coupleSeatRowCount = ultimaSeatRowCount - 2; // 2 rows of couple seats | rows 6-7

    for (int i = 0; i < layout.length; i++) {
      for (int j = 0; j < layout[i].length; j++) {
        SeatType seatType = i < coupleSeatRowCount ? SeatType.REGULAR : i < ultimaSeatRowCount ? SeatType.COUPLE : j <= 3 ? SeatType.ELITE : SeatType.ULTIMA;
        String seatId = (char) (i + 65) + String.valueOf(j);
        layout[i][j] = new Seat(seatId, seatType);
      }
    }
    this.seatLayout = layout;
    this.id = CineplexDB.generateShowingId();
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

  public Movie setMovie(Movie movie){
    return this.movie = movie;
  }

  public String getMovieTitle() {
    return this.movie.getTitle();
  }

  public DateType getDateType() {
    return this.dateType;
  }

  public DateType setDateType(DateType dateType){
    return this.dateType = dateType;
  }

  public String getFormattedTime() {
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    String formattedDate = showTime.format(myFormatObj);
    return formattedDate;
  }

  public void printSeating() {
    System.out.println("************** Movie Screen *******************");
    System.out.println();
    System.out.println("   1  2  3  4 \t 5  6  7  8");
    for (int i = 0; i < this.seatLayout.length; i++) {
      for (int j = 0; j < this.seatLayout[i].length; j++) {
        if (j == 0) {
          System.out.print((char)(i+65) + " ");
        }
        this.seatLayout[i][j].printSeat();
        if (j == this.seatLayout[i].length/2 - 1) {
          System.out.print("\t");
        }
        if (j == this.seatLayout[i].length - 1) {
          System.out.println();
        }
      }
    }
    System.out.println("\nLegend: ");
    System.out.println("REGULAR: [ ][ ] | [X][ ] | [X][X]");
    System.out.println("COUPLE:  [    ] | [X   ] | [X  X]");
    System.out.println("ELITE:   { }{ } | {X}{ } | {X}{X}");
    System.out.println("ULTIMA:  {    } | {X   } | {X  X}");
    System.out.println("\n***********************************************");
  }

  public boolean isAvailable(String seatId) {
    int row = (int) seatId.charAt(0) - 65;
    int column = Character.valueOf(seatId.charAt(1)) - 49;
    Seat seat = this.seatLayout[row][column];
    return seat.isAvailable();
  }

  public void assignSeat(MovieGoer movieGoer, String seatId) {
    int row = (int) seatId.charAt(0) - 65;
    int column = Character.valueOf(seatId.charAt(1)) - 49;
    Seat seat = this.seatLayout[row][column];
    seat.assignSeat(movieGoer);
  }

  // Testing
  public Seat[][] getSeatLayout() {
    return this.seatLayout;
  }

  // public static void main(String[] args) {
  //   ArrayList<String> cast = new ArrayList<>();
  //   cast.add("ABC");
  //   cast.add("ABC");
  //   String[] c = { "A", "Iron B" };
  //   Movie m = new Movie("Spider Man", MovieStatus.NOW_SHOWING, "Spider Man", "Spider Man", cast, MovieType.BLOCKBUSTER, LocalDateTime.now());
  //   LocalDateTime lTime = LocalDateTime.now();
  //   DateType dateType = DateType.WEEKEND;
  //   Showing showing = new Showing(m, lTime, dateType);

  //   showing.getFormattedTime();
  //   showing.printSeating();
  //   System.out.println();

  //   MovieGoer movieGoer = new MovieGoer(null, null, null, null);
  //   Seat[][] layout = showing.getSeatLayout();
  //   layout[0][0].assignSeat(movieGoer);
  //   showing.printSeating();
  //   System.out.println(showing.isAvailable("A1"));
  //   System.out.println(showing.isAvailable("A2"));

  //   showing.assignSeat(movieGoer, "A2");
  //   showing.assignSeat(movieGoer, "I1");
  //   showing.assignSeat(movieGoer, "H6");
  //   showing.assignSeat(movieGoer, "H5");
  //   showing.printSeating();
  // }
}
