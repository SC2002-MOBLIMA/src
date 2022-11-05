package Enums;

public enum MovieType { // modified by aaron based on appendix A
  REGULAR("regular"),
  THREE_D("3d"),
  BLOCKBUSTER("blockbuster");

  private String type;

  private MovieType(String text) {
    this.type = text;
  }

  public String getType() {
    return this.type;
  }

  public MovieType fromString(String text) {
    for (MovieType type : MovieType.values()) {
      if (type.getType().equalsIgnoreCase(text)) {
        return type;
      }
    }
    return null;
  }
}
