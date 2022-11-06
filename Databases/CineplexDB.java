package Databases;

import java.lang.System;
import Objects.Cineplex;
import Objects.Cinema;

import java.util.ArrayList;

public class CineplexDB extends SerializeDB<ArrayList<Cineplex>> {
    public CineplexDB() {
        this.filename = "Databases/cineplex.dat";
    }

    public static int generateShowingId() {
        return (int) (System.currentTimeMillis() / 1000);
    }
}
