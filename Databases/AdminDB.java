package Databases;

import java.util.ArrayList;
import Objects.Admin;

public class AdminDB extends SerializeDB {
  public static ArrayList<Admin> read() {
    return (ArrayList<Admin>) readSerializedObject("admin.dat");
  }

  public static void write(ArrayList<Admin> data) {
    writeSerializedObject("admin.dat", data);
  }
}