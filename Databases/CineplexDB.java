package Databases;

import Objects.Cineplex;
import Objects.Cinema;

public class CineplexDB extends SerializeDB {
  public CineplexDB() {
    this.filename = "Databases/cineplex.dat";
  }

  public static String generateCinemaCode(Cineplex cnp, Cinema cnm) {
    String code = cnp.getCineplexName()[0] + cnp.getCineplexName()[1] + cnm.getCinemaNum().toString();
    return code;
  }
}