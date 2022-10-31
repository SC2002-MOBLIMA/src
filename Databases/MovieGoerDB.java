package Databases;

import java.util.ArrayList;
import Objects.MovieGoer;

public class MovieGoerDB extends SerializeDB {
    public MovieGoerDB() {
        this.filename = "Databases/movieGoer.dat";
    }

    public boolean checkMovieGoerExists(String name) {
        ArrayList<MovieGoer> data = (ArrayList<MovieGoer>) this.read();
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getName() = name) {
                return true;
            }
        }
        return false;
    }
}
