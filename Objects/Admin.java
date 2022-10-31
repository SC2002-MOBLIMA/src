package Objects;

import java.io.Serializable;

public class Admin implements Serializable {
  private String username;
  private String password;

  public Admin(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }
}
