package databases;

import java.util.ArrayList;

import objects.Admin;

/**
 * Represents the concrete DB class for Admin.
 * 
 * @author Aaron Chua
 * @version 1.0
 * @since 2022-11-11
 */
public class AdminDB extends SerializeDB<ArrayList<Admin>> {
    
    /**
     * Creates a new AdminDB with the given filename.
     */
    public AdminDB() {
        this.filename = "databases/admin.dat";
    }
}
