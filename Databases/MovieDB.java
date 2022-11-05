package Databases;

import Objects.Movie;

public class MovieDB extends SerializeDB<Movie> {
    public MovieDB() {
        this.filename = "Databases/movie.dat";
    }
}
