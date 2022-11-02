package Enums;

public enum DateType {
  WEEKEND("weekend"),
  WEEKDAY("weekday"),
  PUBLIC_HOLIDAY("public holiday");

  private final String date;

  private DateType(String date) {
    this.date = date;
  }

  public String getDate() {
    return this.date;
  }

  public static DateType fromString(String text) {
    for (DateType d : DateType.values()) {
      if (d.getDate().equalsIgnoreCase(text)) {
        return d;
      }
    }
    return null;
  }

}
