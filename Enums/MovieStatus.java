package Enums;

public enum MovieStatus {
  COMING_SOON("coming soon"),
  PREVIEW("preview"),
  NOW_SHOWING("now showing"),
  END_OF_SHOWING("end of showing");

  private String status;

  private MovieStatus(String text) {
    this.status = text;
  }

  public String getStatus() {
    return this.status;
  }

  public static MovieStatus fromString(String text) {
    for (MovieStatus m : MovieStatus.values()) {
      if (m.getStatus().equalsIgnoreCase(text)) {
        return m;
      }
    }
    return null;
  }

}
