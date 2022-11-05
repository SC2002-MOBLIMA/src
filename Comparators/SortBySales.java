package Comparators;

import java.util.Comparator;

import Objects.Movie;

public class SortBySales implements Comparator<Movie> {
    public int compare(Movie m0, Movie m1) {
        if (m0.getSaleCount() > m1.getSaleCount()) {
            return 1;
        } else if (m0.getSaleCount() == m1.getSaleCount()) {
            return 0;
        } else {
            return -1;
        }
    }
}
