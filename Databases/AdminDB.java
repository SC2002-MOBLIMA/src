package Databases;

import java.util.ArrayList;
import Objects.Admin;

public class AdminDB {
  public AdminDB() {}
  public static ArrayList<Admin> getAdminList() {
    // TODO: change this
    ArrayList<Admin> temp = new ArrayList<Admin>();
    temp.add(new Admin("Chualala", "ChualalaPassword"));
    return temp;
  }
  public static void setAdminList() {}
}
