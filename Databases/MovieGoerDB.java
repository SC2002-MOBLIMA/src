package Databases;

import java.util.ArrayList;
import Objects.MovieGoer;

public class MovieGoerDB extends SerializeDB<ArrayList<MovieGoer>> {
    public MovieGoerDB() {
        this.filename = "Databases/movieGoer.dat";
    }
}
