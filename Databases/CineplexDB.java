package Databases;

import java.lang.System;
import Objects.Cineplex;
import Objects.Cinema;

public class CineplexDB extends SerializeDB {
  public CineplexDB() {
    this.filename = "Databases/cineplex.dat";
  }

  public static String generateCinemaCode(Cineplex cnp, Cinema cnm) {
    String code = cnp.getCineplexName().charAt(0) + cnp.getCineplexName().charAt(1) + Integer.toString(cnm.getCinemaNum());
    return code;
  }

  public static int generateShowingId() {
    return (int) (System.currentTimeMillis() / 1000);
  }
}
