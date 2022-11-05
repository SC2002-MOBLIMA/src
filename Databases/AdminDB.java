package Databases;

import Objects.Admin;

public class AdminDB extends SerializeDB<Admin> {
    public AdminDB() {
        this.filename = "Databases/admin.dat";
    }
}
