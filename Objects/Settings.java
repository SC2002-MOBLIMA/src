package Objects;

import java.util.HashMap;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Settings implements Serializable {
  
  private HashMap<String, Integer> movieTypePriceMap;
  private HashMap<String, Integer> cinemaClassPriceMap;
  private HashMap<String, Integer> ageTypePriceMap;
  private HashMap<String, Integer> dayTypePriceMap;
  private ArrayList<LocalDate> holidayDates;

  public Settings() {
    movieTypePriceMap = new HashMap<String, Integer>();
    cinemaClassPriceMap = new HashMap<String, Integer>();
    ageTypePriceMap = new HashMap<String, Integer>();
    dayTypePriceMap = new HashMap<String, Integer>();
    holidayDates = new ArrayList<LocalDate>();
  }

  public int getMovieTypePrice(String typeChoice) {
    if (movieTypePriceMap.containsKey(typeChoice)) {
      return movieTypePriceMap.get(typeChoice);
    } else return 0;
  }

  public int getCinemaClassPrice(String typeChoice) {
    if (cinemaClassPriceMap.containsKey(typeChoice)) {
      return cinemaClassPriceMap.get(typeChoice);
    } return 0;
  }

  public int getAgeTypePrice(String typeChoice) {
    if (ageTypePriceMap.containsKey(typeChoice)) {
      return ageTypePriceMap.get(typeChoice);
    } return 0;
  }

  public int getDayTypePrice(String typeChoice) {
    if (dayTypePriceMap.containsKey(typeChoice)) {
      return dayTypePriceMap.get(typeChoice);
    } return 0;
  }

  public ArrayList<LocalDate> getHolidayDates() {
    return holidayDates;
  }

  public void setMovieTypePrice(String typeChoice, int price) {
    movieTypePriceMap.put(typeChoice, price);
  }

  public void setCinemaClassPrice(String typeChoice, int price) {
    cinemaClassPriceMap.put(typeChoice, price);
  }

  public void setAgeTypePrice(String typeChoice, int price) {
    ageTypePriceMap.put(typeChoice, price);
  }

  public void setDayTypePriceMap(String typeChoice, int price) {
    dayTypePriceMap.put(typeChoice, price);
  }

  public void setHolidayDates(ArrayList<LocalDate> holidayDates) {
    this.holidayDates = holidayDates;
  }
}
