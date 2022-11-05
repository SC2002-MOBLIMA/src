package Objects;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import Enums.MovieStatus;
import Enums.MovieType;

public class Movie implements Serializable, Comparable<Movie> {
  private String title;
  private MovieStatus status;
  private String synopsis;
  private String director;
  private ArrayList<String> cast;
  private ArrayList<Review> reviewList;
  private int saleCount;
  private MovieType type;
  private LocalDateTime endOfShowingDate; // YYYY-MM-DDT00:00:00

  public Movie(String title, MovieStatus status, String synopsis, String director, ArrayList<String> cast,
      MovieType type, LocalDateTime endOfShowingDate) {
    this.title = title;
    this.status = status;
    this.synopsis = synopsis;
    this.director = director;
    this.cast = cast;
    this.reviewList = new ArrayList<Review>(); // default empty reviewList
    this.saleCount = 0; // default empty saleCount
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

  public ArrayList<Review> getReviewList() {
    return this.reviewList;
  }

  public String getDirector() {
    return this.director;
  }

  public void setDirector(String director) {
    this.director = director;
  }

  public ArrayList<String> getCast() {
    return this.cast;
  }

  public void setCast(ArrayList<String> cast) {
    this.cast = cast;
  }

  public MovieType getType() {
    return this.type;
  }

  public void setType(MovieType type) {
    this.type = type;
  }

  public int getSaleCount() {
    return this.saleCount;
  }

  public void setSaleCount(int saleCount) {
    this.saleCount = saleCount;
  }

  public LocalDateTime getEndOfShowingDate() {
    return this.endOfShowingDate;
  }

  public void setEndOfShowingDate(LocalDateTime endOfShowingDate) {
    this.endOfShowingDate = endOfShowingDate;
  }

  public void incrementSaleCount() {
    this.saleCount++;
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

  @Override
  public int compareTo(Movie m) {
    // compare by movie title
    return this.title.compareToIgnoreCase(m.getTitle());
  }

  @Override
  public boolean equals(Object o) {
    // compare by movie title
    if (o == null) {
      return false;
    }
    if (o.getClass() != this.getClass()) {
      return false;
    }

    Movie m = (Movie) o;
    if (this.title == m.getTitle()) {
      return true;
    }
    return false;
  }
}
