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
  private String TID;

  public MovieTicket(MovieGoer movieGoer, double price, Showing showing, Cineplex cineplex, Cinema cinema, String seatId) {
    this.movieGoer = movieGoer;
    this.price = price;
    this.showing = showing;
    this.cineplex = cineplex;
    this.cinema = cinema;
    this.seatId = seatId;

    LocalDateTime showTime = showing.getShowTime();
    DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");

    this.TID = cinema.getCinemaCode() + showTime.format(dtFormatter);
  }

  public void printTicket() {
    System.out.println("***********************************************");
    System.out.println("Booking Information for TID " + TID);
    System.out.println("***********************************************");
    System.out.println("Personal Particulars: ");
    System.out.println("Name: " + movieGoer.getName());
    System.out.println("Mobile: " + movieGoer.getMobile());
    System.out.println("Email: " + movieGoer.getEmail());
    System.out.println("***********************************************");
    System.out.println("Movie Information: ");
    System.out.println("Movie: " + showing.getMovieTitle());
    System.out.println("Cineplex: " + cineplex.getCineplexName());
    System.out.println("Cinema: " + cinema.getCinemaNum());
    System.out.println("Price: " + price + " | Time: " + showing.getFormattedTime() + " | Seat: " + seatId);
    System.out.println("***********************************************");
  }

}
