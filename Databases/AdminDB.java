package Databases;

import java.util.ArrayList;

import Objects.Admin;

public class AdminDB extends SerializeDB<ArrayList<Admin>> {
    public AdminDB() {
        this.filename = "Databases/admin.dat";
    }
}
