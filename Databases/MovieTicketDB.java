package Databases;

import java.util.ArrayList;
import Objects.MovieTicket;

public class MovieTicketDB extends SerializeDB {
    public static ArrayList<MovieTicket> read() {
        return (ArrayList<MovieTicket>) readSerializedObject("movieTicket.dat");
    }

    public static void write(ArrayList<MovieTicket> data) {
        writeSerializedObject("movieTicket.dat", data);
    }
}