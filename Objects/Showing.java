package Objects;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Enums.DayType;
import Enums.SeatType;

import Databases.CineplexDB;

abstract public class Showing implements Serializable {
  private int id;
  private Movie movie;
  private LocalDateTime showTime; // YYYY-MM-DDT00:00:00
  private DayType dayType; // Weekend / Weekday / PublicHoliday
  protected Seat[][] seatLayout; // 9 rows, 8 columns

  public Showing(Movie movie, LocalDateTime showTime, DayType dayType) {
    this.movie = movie;
    this.showTime = showTime;
    this.dayType = dayType;
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

  public DayType getDayType() {
    return this.dayType;
  }

  public DayType setDayType(DayType dayType){
    return this.dayType = dayType;
  }

  public String getFormattedTime() {
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    String formattedDate = showTime.format(myFormatObj);
    return formattedDate;
  }

  public void printSeating() {
    System.out.println("***********************************************");
    System.out.print("=".repeat(seatLayout[0].length));
    System.out.print(" Movie Screen ");
    System.out.print("=".repeat(seatLayout[0].length) + "\n");
    System.out.println("{STAIRS}");
    System.out.println();

    // print labels on top 
    System.out.print("   ");
    for (int i = 1; i <= seatLayout[0].length; i++) {
      System.out.print(i + "  ");
      if (i == this.seatLayout[0].length/2) {
        System.out.print("\t ");
      }
    }
    System.out.println();

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
    System.out.println("REGULAR: [ ][ ] | [X][X]");
    System.out.println("COUPLE:  [    ] | [X  X]");
    System.out.println("ELITE:   { }{ } | {X}{X}");
    System.out.println("ULTIMA:  {    } | {X  X}");
    System.out.println("\n***********************************************");
  }

  public boolean isAvailable(String seatId) {
    int row = (int) seatId.charAt(0) - 65;
    int column = Character.valueOf(seatId.charAt(1)) - 49;
    Seat seat = this.seatLayout[row][column];
    return seat.isAvailable();
  }

  public SeatType getSeatType(String seatId) {
    int row = (int) seatId.charAt(0) - 65;
    int column = Character.valueOf(seatId.charAt(1)) - 49;
    Seat seat = this.seatLayout[row][column];
    return seat.getSeatType();
  }

  public void assignSeat(MovieGoer movieGoer, String seatId) {
    int row = (int) seatId.charAt(0) - 65;
    int column = Character.valueOf(seatId.charAt(1)) - 49;
    Seat seat = this.seatLayout[row][column];
    seat.assignSeat(movieGoer);
  }

  public void unassignSeat(String seatId) {
    int row = (int) seatId.charAt(0) - 65;
    int column = Character.valueOf(seatId.charAt(1)) - 49;
    Seat seat = this.seatLayout[row][column];
    seat.unassignSeat();
  }

  // Testing
  public Seat[][] getSeatLayout() {
    return this.seatLayout;
  }
}
