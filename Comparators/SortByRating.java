package Comparators;

import java.util.Comparator;

import Objects.Movie;

public class SortByRating implements Comparator<Movie> {

    public int compare(Movie m0, Movie m1) {
        if (m0.getRating() > m1.getRating()) {
            return 1;
        } else if (m0.getRating() == m1.getRating()) {
            return 0;
        } else {
            return -1;
        }
    }

}