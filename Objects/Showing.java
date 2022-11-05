package Objects;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import Enums.DateType;

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

    Seat[][] layout = new Seat[9][10];
    for (int i = 0; i < layout.length; i++) {
      for (int j = 0; j < layout[i].length; j++) {
        String seatId = (char) (i + 65) + String.valueOf(j);
        layout[i][j] = new Seat(seatId);
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

  public String getFormattedTime() {
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    String formattedDate = showTime.format(myFormatObj);
    return formattedDate;
  }

  public void printSeating() {
    System.out.println("************** Movie Screen *******************");
    System.out.println();
    System.out.println("   0  1  2  3  4 \t 5  6  7  8  9");
    for (int i = 0; i < this.seatLayout.length; i++) {
      for (int j = 0; j < this.seatLayout[i].length; j++) {
        if (j == 0) {
          System.out.print((char)(i+65) + " ");
        }
        if (this.seatLayout[i][j].isAvailable()) {
          System.out.print("[ ]");
        } else {
          System.out.print("[X]");
        }
        if (j == 4) {
          System.out.print("\t");
        }
        if (j == this.seatLayout[i].length - 1) {
          System.out.println();
        }
      }
    }
    System.out.println("\n***********************************************");
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
  //   System.out.println(showing.isAvailable("A0"));
  //   System.out.println(showing.isAvailable("A1"));

  //   showing.assignSeat(movieGoer, "A1");
  //   showing.printSeating();
  // }
}
