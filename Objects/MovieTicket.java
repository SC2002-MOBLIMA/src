package Objects;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MovieTicket {
  private MovieGoer movieGoer;
  private double price;
  private Showing showing;
  private Cineplex cineplex;
  private Cinema cinema;
  private String seatId;
  private LocalDateTime showingTime;
  private String TID;

  public MovieTicket(MovieGoer movieGoer, double price, Showing showing, Cineplex cineplex, Cinema cinema, String seatId, LocalDateTime showingTime) {
    this.movieGoer = movieGoer;
    this.price = price;
    this.showing = showing;
    this.cineplex = cineplex;
    this.cinema = cinema;
    this.seatId = seatId;
    this.showingTime = showingTime;

    DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
    this.TID = "AAA" + showingTime.format(dtFormatter);
  }

  public void printTicket() {
    System.out.println("***********************************************");
    System.out.println("Booking Information for TID " + TID);
    // TODO: Remove hardcoded values
    System.out.println("Cineplex: " + "PlaceholderCineplex");
    System.out.println("Cinema: " + "PlaceholderCinema");
    System.out.println("MovieGoer: " + "IvanLoke");
    System.out.println("Price: " + price);
  }

}
