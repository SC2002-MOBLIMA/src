package Objects;

import java.io.Serializable;
import java.util.*;

public class Movie implements Serializable {
  private String title;
  private MovieStatus status;
  private String synopsis;
  private String director;
  private String[] cast;
  private ArrayList<Review> reviewList = new ArrayList<Review>(); // Always initialized
  private int saleCount;
  private MovieType type;
  private String endOfShowingDate;

  public Movie(String title, MovieStatus status, String synopsis, String director, String[] cast, int saleCount,
      MovieType type, String endOfShowingDate) {
    this.title = title;
    this.status = status;
    this.synopsis = synopsis;
    this.director = director;
    this.cast = cast;
    this.saleCount = saleCount;
    this.type = type;
    this.endOfShowingDate = endOfShowingDate;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public MovieStatus getStatus() {
    return this.status;
  }

  public void setStatus(MovieStatus status) {
    this.status = status;
  }

  public String getSynopsis() {
    return this.synopsis;
  }

  public void setSynopsis(String synopsis) {
    this.synopsis = synopsis;
  }

  public String getDirector() {
    return this.director;
  }

  public void setDirector(String director) {
    this.director = director;
  }

  public String[] getCast() {
    return this.cast;
  }

  public void setCast(String[] cast) {
    this.cast = cast;
  }

  public MovieType getType() {
    return this.type;
  }

  public void setType(MovieType type) {
    this.type = type;
  }

  public int getSalesCount() {
    return this.saleCount;
  }

  public void incrementSaleCount() {
    int count = getSalesCount();
    this.saleCount = count++;
  }

  public void printMovieDetails() {
    System.out.println("Title: " + title);
    System.out.println("Status: " + status);
    System.out.println("Synopsis: " + synopsis);
    System.out.println("Director: " + director);
    System.out.println("Cast: " + cast);
    System.out.println("ReviewList: " + reviewList);
    System.out.println("Type: " + type);
    System.out.println("SaleCount: " + saleCount);
    System.out.println("EndOfShowingDate: " + endOfShowingDate);
  }

  public void addReview(String name, int rating, String review) {
    Review newReview = new Review(name, rating, review);
    this.reviewList.add(newReview);
  }

  public double getOverallRating() {
    int totalRating = 0;
    int count = 0;
    for (Review r : reviewList) {
      totalRating += r.getRating();
      count++;
    }
    int overallRating = totalRating / count;
    return overallRating;
  }

  // TODO:
  // public int compareTo() {
  // }

  // public boolean equals(Object o) {
  // }

  // public static void main(String args[]) {
  //   String[] c = { "A", "Iron B" };
  //   Movie m = new Movie("Spider Man", MovieStatus.COMINGSOON, "Spider Man", "Spider Man", c, 0,
  //       MovieType.BLOCKBUSTER, "ABC");
  //   m.addReview("kaijun", 5, "sucks");
  //   m.addReview("kaijun", 1, "sucks");
  //   System.out.println(m.getOverallRating());
  //   System.out.println("works");
  // }
}
