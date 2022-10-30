package Databases;

import java.util.ArrayList;
import Objects.Cineplex;

public class CineplexDB extends SerializeDB {
  public static ArrayList<Cineplex> read() {
    return (ArrayList<Cineplex>) readSerializedObject("cineplex.dat");
  }

  public static void write(ArrayList<Cineplex> data) {
    writeSerializedObject("cineplex.dat", data);
  }
}