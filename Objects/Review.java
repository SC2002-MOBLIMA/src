package Objects;

import java.io.Serializable;

public class Review implements Serializable {
  private String name;
  private int rating; // From 1(worse) to 5 (best)
  private String review;

  public Review(String name, int rating, String review) {
    this.name = name;
    this.rating = rating;
    this.review = review;
  }

  public String getName() {
    return this.name;
  }

  public int getRating() {
    return this.rating;
  }

  public String getReview() {
    return this.review;
  }
}
