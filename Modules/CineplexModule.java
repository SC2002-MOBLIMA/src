package Modules;

import java.util.Scanner;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.DayOfWeek;

import Databases.MovieDB;
import Databases.CineplexDB;
import Databases.SettingsDB;

import Enums.DateType;
import Objects.Cineplex;
import Objects.Cinema;
import Objects.Showing;
import Objects.Movie;
import Objects.Settings;


public class CineplexModule {
    private Scanner sc;
    private ArrayList<Cineplex> cineplexList;
    private Cineplex cineplexObj;
    private Cinema cinemaObj;
    private ArrayList<LocalDate> holidayDates;
    private Movie movieObj;

    public CineplexModule(Scanner sc) {
        this.sc = sc;
    }

    public void run() {

        boolean main = true;
        while (main) {
            System.out.println("***************************************************");
            System.out.println("MOBLIMA -- Admin -- Cineplex Module: ");
            
            CineplexDB cineplexDB = new CineplexDB();
            cineplexList = (ArrayList<Cineplex>)cineplexDB.read();
            for (int i = 0; i < cineplexList.size(); i++) {
                System.out.println("[" + (i+1) + "] " + cineplexList.get(i).getCineplexName());
            }
            System.out.print("Please key in the number of the cineplex name that you would like to edit: ");
            int name = sc.nextInt();

            if(!(name<1 || name>cineplexList.size())){
                cineplexObj = cineplexList.get(name-1);
                main = false;
                break;
            }     
            System.out.println("Error: Cineplex not found. Please try again");
        }

        boolean main_cinema = true;
        while (main_cinema) {
            ArrayList<Cinema> cinemaList = cineplexObj.getListOfCinemas();
            ArrayList<String> cinemaNums = new ArrayList<String>();
        for (int i = 0; i < cinemaList.size(); i++) {
            cinemaNums.add(Integer.toString(cinemaList.get(i).getCinemaNum()));
        }
        System.out.print("Please key in the cinema number that you would like to edit (" + String.join(",", cinemaNums) + "): ");
        int num = sc.nextInt();

        for (int i = 0; i < cinemaList.size(); i++) {
            if (cinemaList.get(i).getCinemaNum() == num) {
            cinemaObj = cinemaList.get(i);
            main_cinema = false;
            }
        }
        if (main_cinema) {
            System.out.println("Error: Cinema not found. Please try again\n");
        }
        }

        boolean main_final = true;
        while (main_final) {
            System.out.println("***************************************************");
            System.out.println("MOBLIMA -- Admin -- Cineplex Module: ");
            System.out.println("[1] Show Showings");
            System.out.println("[2] Add Showing");
            System.out.println("[3] Remove Showing");
            System.out.println("[4] Update Showing");
            System.out.println("[5] Back");
            System.out.print("Please select an option: ");
            int select = sc.nextInt();
            System.out.println("***************************************************");

        switch (select) {
            
            case 1:
                showShow();
                break;
            
            case 2:
                addShow();
                break;

            case 3:
                removeShow();
                break;

            case 4:
                updateShow();
                break;

            case 5:
                main_final = false;
                break;
        }
        }
    }

    private void selectMovie() {
        boolean main = true;
        while (main) {
            MovieDB movieDB = new MovieDB();
            ArrayList<Movie> movieList = (ArrayList<Movie>) movieDB.read();
            for (int i = 0; i < movieList.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + movieList.get(i).getTitle());
            }
            System.out.print("Please key in the number of the movie that you would like: ");
            int selection = sc.nextInt();
            if (!(selection < 1 || selection > movieList.size())) {
                main = false;
                movieObj = movieList.get(selection - 1);
            } else {
                System.out.println("Error: Invalid value keyed in. Please try again\n");
            } 
        }
    }

    private void addShow() {
        System.out.println("***************************************************");
        selectMovie();
        LocalDateTime dateTime = LocalDateTime.now();
        while (true) {
            try {
                System.out.print("Please key in the Date and Time of the show in the following format (yyyyMMddHHmm): ");
                String input = sc.next();
        
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
                dateTime = LocalDateTime.parse(input, myFormatObj);

                if (dateTime.compareTo(movieObj.getEndOfShowingDate()) > 0) {
                    System.out.println("Error: Date keyed in exceeds the end of showing of movie. Please try again\n");
                }
                else if (dateTime.compareTo(LocalDateTime.now()) < 0){
                    System.out.println("Error: Date keyed in has already passed. Please try again");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Error: Invalid date format. Please try again");
            }
        }

        DayOfWeek dayofWeek= DayOfWeek.from(dateTime);
        int day = dayofWeek.getValue();
        
        SettingsDB settingsDB = new SettingsDB();
        Settings settingsObj = settingsDB.read();
        holidayDates = settingsObj.getHolidayDates();
        LocalDate date = dateTime.toLocalDate();

        DateType inputDateType = DateType.WEEKDAY;
        
        boolean filter = true;
        for(int i=0; i<holidayDates.size(); i++){
            if(holidayDates.get(i).equals(date)){
                inputDateType = DateType.PUBLIC_HOLIDAY;
                filter = false;
            }
        }
        if (filter) {
            if (day == 6 || day == 7) {
                inputDateType = DateType.WEEKEND;
            }
        }
        cinemaObj.addShow(movieObj, dateTime, inputDateType);
        CineplexDB cineplexDB = new CineplexDB();
        cineplexDB.write(cineplexList);
        System.out.println("Show has been sucessfully added.");
        System.out.println("***************************************************");
    }

    private void showShow() {
        System.out.println("******************** Results **********************");
        ArrayList<Showing> showList = cinemaObj.getShowList();
        if(showList.size()==0){
            System.out.println("There are no showings available.");
        } else {
            cinemaObj.displayShowList();
        }
        System.out.println("***************************************************");
    }

    private void removeShow() {
        System.out.println("***************************************************");
        boolean main = true;
        while (main) {
            ArrayList<Showing> showList = cinemaObj.getShowList();
            if (showList.size()==0) {
                System.out.println("There are no showings to remove");
                break;
            }
            cinemaObj.displayShowList();
            System.out.print("Please key in the number of the show that you would like to remove: ");
            int selection = sc.nextInt();
            if (!(selection < 1 || selection > showList.size())) {
                Showing selectedShow = showList.get(selection-1);
                cinemaObj.removeShow(selectedShow);
                main = false;
                System.out.println("Selection has been successfully removed");
            } else {
                System.out.println("Error: Invalid value keyed in. Please try again");
            }
        }
        CineplexDB cineplexDB = new CineplexDB();
        cineplexDB.write(cineplexList);
        System.out.println("***************************************************");
    }

    private void updateShow() {
        System.out.println("Updating Showing...");
        ArrayList<Showing> showList = cinemaObj.getShowList();
        boolean main = true; 
        while (main) {
            if (showList.size() == 0){
                System.out.println("There are no showings to remove");
                break;
            }
            System.out.println("Key in the number of the show that you would like to update: ");
            cinemaObj.displayShowList();
            int selection = sc.nextInt();
            Showing show = showList.get(selection-1);
            System.out.println("***************************************************");
            if (!(selection < 1 || selection > showList.size())) {
                System.out.println("Update: ");
                System.out.println("[1] Movie");
                System.out.println("[2] Showtime");
                int choice = sc.nextInt();

                switch(choice){
                case 1:
                    System.out.println("Updating movie of Showing..");
                    selectMovie();
                    show.setMovie(movieObj);
                    System.out.println("Movie has been updated");
                    main = false;
                    break;

                case 2:
                    boolean main_loop = true;
                    System.out.println("Updating showtime of Showing...");
                    if (main_loop) { 
                        LocalDateTime dateTime = null;
                        movieObj = show.getMovie();
                        System.out.println("Key in the new show time in the following format (yyyyMMddHHmm): ");
                        String date = sc.next();
                        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
                        try{
                            dateTime = LocalDateTime.parse(date, myFormatObj);
                            if(dateTime.compareTo(movieObj.getEndOfShowingDate())<0){
                            show.setShowTime(dateTime);
                            System.out.println("Showtime has been updated");
                            DayOfWeek dayofWeek= DayOfWeek.from(dateTime);
                            int day = dayofWeek.getValue();
                            LocalDate date_check = dateTime.toLocalDate();
                            DateType inputDateType = DateType.WEEKDAY;
                            boolean filter = true;
                            for (int i=0; i<holidayDates.size(); i++) {
                                if (holidayDates.get(i).equals(date_check)) {
                                    inputDateType = DateType.PUBLIC_HOLIDAY;
                                    System.out.println("Public holiday detected");
                                    filter = false;
                                }
                            }
                            if(filter){
                                if (day == 6 || day == 7) {
                                    inputDateType = DateType.WEEKEND;
                                }       
                            }
                            show.setDateType(inputDateType);
                            main_loop = false;
                            main = false;
                            break;
                            }
                            else{
                            System.out.println("Showtime keyed in exceeds movie's end of showing date. Please key in the date again");
                            }
                        } catch (Exception e) {
                            System.out.println("Error: Invalid date format. Please try again");
                        }
                    }
                    break;

                default:
                    break;
                }
            } else {
                System.out.println("Error: Key in a valid value");
            }
        }
        CineplexDB cineplexDB = new CineplexDB();
        cineplexDB.write(cineplexList);
    }
}
