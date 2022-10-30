package Databases;

import java.util.ArrayList;
import Objects.Movie;

public class MovieDB extends SerializeDB {
    public static ArrayList<Movie> read() {
        return (ArrayList<Movie>) readSerializedObject("movie.dat");
    }

    public static void write(ArrayList<Movie> data) {
        writeSerializedObject("movie.dat", data);
    }
}