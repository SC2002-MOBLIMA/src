package Databases;

import java.util.ArrayList;
import Objects.MovieGoer;

public class MovieGoerDB extends SerializeDB {
    public static ArrayList<MovieGoer> read() {
        return (ArrayList<MovieGoer>) readSerializedObject("movieGoer.dat");
    }

    public static void write(ArrayList<MovieGoer> data) {
        writeSerializedObject("movieGoer.dat", data);
    }
}