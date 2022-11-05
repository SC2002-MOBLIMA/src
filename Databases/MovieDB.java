package Databases;

import java.util.ArrayList;

import Objects.Movie;

public class MovieDB extends SerializeDB<ArrayList<Movie>> {
    public MovieDB() {
        this.filename = "Databases/movie.dat";
    }
}
