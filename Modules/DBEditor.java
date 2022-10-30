package Modules;

import java.util.Scanner;

// three imports for illustration
import Databases.AdminDB;
import Objects.Admin;
import java.util.ArrayList;

public class DBEditor {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("***********************************************");
        System.out.println("************* WELCOME TO DATABASE EDITOR **************");
        System.out.println("***********************************************");
        int choice;
        boolean running = true;
        while (running) {

            System.out.println("DATABASE EDITOR -- Main Menu:");
            System.out.println("[1] Edit AdminDB ");
            System.out.println("[2] Edit CineplexDB ");
            System.out.println("[3] Edit MovieDB ");
            System.out.println("[4] Edit MovieGoerDB ");
            System.out.println("[5] Exit");
            System.out.print("Please Select: ");
            choice = sc.nextInt();
            System.out.println("***********************************************");
            switch (choice) {
                case 1:
                    // create instance object of your DB and your local data variable
                    AdminDB AdminDBInstance = new AdminDB();
                    ArrayList<Admin> data;

                    // read functionality
                    // (if no data exists yet, catch and create new ArrayList)
                    try {
                        data = (ArrayList<Admin>) AdminDBInstance.read();
                        System.out.println("Current Data:");
                        for (int i = 0; i < data.size(); i++) {
                            Admin cinemaStaffPerson = (Admin) data.get(i);
                            System.out.println("name is " + cinemaStaffPerson.getUsername());
                            System.out.println("contact is " + cinemaStaffPerson.getPassword());
                        }
                    } catch (Exception e) {
                        System.out.println("~No current data~");
                        data = new ArrayList<Admin>();
                    }
                    System.out.println("-----------------");

                    // write functionality
                    Admin newDataToAdd = new Admin("Aaron", "password");
                    data.add(newDataToAdd);
                    // data = null; // uncomment this, recompile and run to reset your dat file
                    AdminDBInstance.write(data);
                    System.out.println("-----------------");
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    System.out.println("Bye Bye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid Choice, Please try again!\n");
                    break;
            }
            System.out.println("***********************************************");
        }
    }
}