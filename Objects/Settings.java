package Objects;

import java.util.HashMap;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Settings implements Serializable {
  
  private HashMap<String, Double> movieTypePriceMap;
  private HashMap<String, Double> cinemaClassPriceMap;
  private HashMap<String, Double> ageTypePriceMap;
  private HashMap<String, Double> dayTypePriceMap;
  private HashMap<String, Double> seatTypePriceMap;
  private ArrayList<LocalDate> holidayDates;

  public Settings() {
    movieTypePriceMap = new HashMap<String, Double>();
    cinemaClassPriceMap = new HashMap<String, Double>();
    ageTypePriceMap = new HashMap<String, Double>();
    dayTypePriceMap = new HashMap<String, Double>();
    seatTypePriceMap = new HashMap<String, Double>();
    holidayDates = new ArrayList<LocalDate>();
  }

  public double getMovieTypePrice(String typeChoice) {
    if (movieTypePriceMap.containsKey(typeChoice)) {
      return movieTypePriceMap.get(typeChoice);
    } else return 0;
  }

  public double getCinemaClassPrice(String typeChoice) {
    if (cinemaClassPriceMap.containsKey(typeChoice)) {
      return cinemaClassPriceMap.get(typeChoice);
    } return 0;
  }

  public double getAgeTypePrice(String typeChoice) {
    if (ageTypePriceMap.containsKey(typeChoice)) {
      return ageTypePriceMap.get(typeChoice);
    } return 0;
  }

  public double getDayTypePrice(String typeChoice) {
    if (dayTypePriceMap.containsKey(typeChoice)) {
      return dayTypePriceMap.get(typeChoice);
    } return 0;
  }

  public double getSeatTypePrice(String typeChoice) {
    if (seatTypePriceMap.containsKey(typeChoice)) {
      return seatTypePriceMap.get(typeChoice);
    } return 0;
  }

  public ArrayList<LocalDate> getHolidayDates() {
    return holidayDates;
  }

  public void setMovieTypePrice(String typeChoice, double price) {
    movieTypePriceMap.put(typeChoice, price);
  }

  public void setCinemaClassPrice(String typeChoice, double price) {
    cinemaClassPriceMap.put(typeChoice, price);
  }

  public void setAgeTypePrice(String typeChoice, double price) {
    ageTypePriceMap.put(typeChoice, price);
  }

  public void setDayTypePrice(String typeChoice, double price) {
    dayTypePriceMap.put(typeChoice, price);
  }

  public void setSeatTypePrice(String typeChoice, double price) {
    seatTypePriceMap.put(typeChoice, price);
  }

  public void setHolidayDates(ArrayList<LocalDate> holidayDates) {
    this.holidayDates = holidayDates;
  }
}
