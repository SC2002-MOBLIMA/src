package Modules;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

import Databases.CineplexDB;
import Databases.MovieDB;
import Databases.SettingsDB;

import Enums.AgeType;
import Enums.CinemaType;
import Enums.MovieType;
import Enums.DayType;
import Enums.SeatType;

import Interfaces.ModuleInterface;

import Objects.Cinema;
import Objects.Cineplex;
import Objects.Movie;
import Objects.MovieGoer;
import Objects.MovieTicket;
import Objects.Settings;
import Objects.Showing;

public class BookingModule implements ModuleInterface {
    private Scanner sc;
    private Settings settingsObj;
    private ArrayList<Cineplex> cineplexList;
    private ArrayList<Cinema> cinemaList;
    private Cineplex cineplexObj;
    private MovieGoer movieGoerObj;

    public BookingModule(Scanner sc, MovieGoer movieGoer) {
        this.sc = sc;
        this.movieGoerObj = movieGoer;
    }

    public void run() {
        System.out.println("***********************************************");
        System.out.println("MOBLIMA -- Movie Goer -- Booking Module:");

        CineplexDB cineplexDB = new CineplexDB();
        cineplexList = cineplexDB.read();

        SettingsDB settingsDB = new SettingsDB();
        settingsObj = settingsDB.read();

        selectCineplex();

        boolean running = true;
        while (running) {
        System.out.println("***********************************************");
        System.out.println("MOBLIMA -- Movie Goer -- Booking Module (Selected Cineplex: " + cineplexObj.getCineplexName() + "):");
        System.out.println("[1] Display All Cinema Showings");
        System.out.println("[2] Display Cinema Showings");
        System.out.println("[3] Check Showing Seat Availability");
        System.out.println("[4] Select, Book & Purchase Tickets");
        System.out.println("[5] Reselect Cineplex");
        System.out.println("[6] Back");
        System.out.print("Please enter your choice: ");

        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                displayAllCinemaShowings();
                break;

            case 2:
                displayCinemaShowings();
                break;

            case 3:
                checkSeatAvailability();
                break;

            case 4:
                bookSeat();
                break;

            case 5:
                selectCineplex();
                break;

            case 6:
                running = false;
                break;

            default:
                System.out.println("Invalid Choice, Please try again.\n");
                break;
        }
        }
    }

    private void displayAllCinemaShowings() {
        for (Cinema cinema : cinemaList) {
            System.out.println("Cinema " + cinema.getCinemaNum() + ":");
            cinema.displayAvailableShows();
            System.out.println();
        }
    }

    private void displayCinemaShowings() {
        Cinema cinemaObj = selectCinema();
        cinemaObj.displayAvailableShows();
        System.out.println();
    }

    private void checkSeatAvailability() {
        Cinema cinemaObj = selectCinema();
        cinemaObj.displayAvailableShows();

        if (cinemaObj.getShowList().size() == 0) {
            return;
        }

        Showing showingObj = selectShowing(cinemaObj);
        showingObj.printSeating();
    }

    private void bookSeat() {
        Cinema cinemaObj = selectCinema();
        cinemaObj.displayAvailableShows();

        if (cinemaObj.getShowList().size() == 0) {
            return;
        }

        MovieDB movieDB = new MovieDB();
        ArrayList<Movie> movieList = movieDB.read();

        Showing showingObj = selectShowing(cinemaObj);
        Movie movieObj = showingObj.getMovie();
        System.out.println("***********************************************");
        System.out.println("Chosen Movie: " + movieObj.getTitle());

        HashMap<String, Double> idPriceMap = new HashMap<String, Double>();
        double totalPrice = 0;
        int ticketCount = 1;
        while (true) {
            int ticketsBooked = 1;
            do {
                showingObj.printSeating();
                System.out.print("Ticket " + ticketCount + " | ");
                System.out.print("Please enter seat to book (eg. A1): ");
                String seatId = sc.next();
                
                if (showingObj.isAvailable(seatId)) {
                    Movie movie = showingObj.getMovie();
                    MovieType movieType = movie.getType();
                    CinemaType cinemaClass = cinemaObj.getCinemaType();
                    AgeType movieGoerAge = movieGoerObj.getAgeType();
                    DayType showingDayType = showingObj.getDayType();
                    SeatType seatType = showingObj.getSeatType(seatId);
                    double price = calculatePrice(movieType, cinemaClass, movieGoerAge, showingDayType, seatType);

                    totalPrice += price;
                    idPriceMap.put(seatId, price);
                    showingObj.assignSeat(movieGoerObj, seatId);

                    // book adjacent seat if couple / ultima
                    if (seatType == SeatType.COUPLE || seatType == SeatType.ULTIMA) {
                        int col = Character.getNumericValue(seatId.charAt(1));
                        int adjCol = col % 2 == 0 ? col - 1 : col + 1;
                        String adjSeatId = "" + seatId.charAt(0) + Character.forDigit(adjCol, 10);

                        totalPrice += price;
                        idPriceMap.put(adjSeatId, price);
                        showingObj.assignSeat(movieGoerObj, adjSeatId);
                        ticketsBooked++;
                    }
                    break;
                } else {
                    System.out.print("Ticket " + ticketCount + " | ");
                    System.out.println("Seat already occupied, Please try again.\n");
                }
            } while (true);
            
            showingObj.printSeating();

            char confirmInput;
            do {
                System.out.print("Ticket " + ticketCount + " | Would you like to book more tickets? (Y/N): ");
                confirmInput = Character.toUpperCase(sc.next().charAt(0));
                if (confirmInput == 'Y' || confirmInput == 'N') {
                    break;
                } else {
                    System.out.println("Invalid input, Please try again\n");
                }
            } while (true);

            if (confirmInput == 'N') {
                break;
            }

            ticketCount += ticketsBooked;
        }

        System.out.println("Please confirm the details of your booking: ");
        System.out.println("Movie:\t\t" + movieObj.getTitle());
        System.out.println("Cineplex:\t" + cineplexObj.getCineplexName());
        System.out.println("Cinema:\t\t" + cinemaObj.getCinemaNum());
        System.out.println("Price:\t\t" + totalPrice);
        System.out.println("Time:\t\t" + showingObj.getFormattedTime());
        System.out.print("Seats:\t\t");
        for (String seatId: idPriceMap.keySet()) {
            System.out.print(seatId + " ");
        }
        System.out.println();
        char confirmInput;
        do {
            System.out.print("Confirm (Y/N): ");
            confirmInput = Character.toUpperCase(sc.next().charAt(0));
            if (confirmInput == 'Y' || confirmInput == 'N') {
                break;
            } else {
                System.out.println("Invalid input, Please try again\n");
            }
        } while (true);
        System.out.println("***********************************************");
        if (confirmInput == 'Y') {
            System.out.println("Booking Successful! Here are the details of your Movie Ticket(s)\n");
            for (Map.Entry<String, Double> entry: idPriceMap.entrySet()) {
                String seatId = entry.getKey();
                double price = entry.getValue();
                MovieTicket movieTicket = new MovieTicket(movieGoerObj, price, showingObj, cineplexObj, cinemaObj, seatId);
                movieGoerObj.addMovieTicket(movieTicket);
                movieObj.incrementSaleCount();

                System.out.println("Movie Ticket " + (seatId + 1) + ": ");
                movieTicket.printTicket();
            }
        } else {
            for (String seatId: idPriceMap.keySet()) {
                showingObj.unassignSeat(seatId);
            }
            System.out.println("Booking Cancelled!\n");
        }
        CineplexDB cineplexDB = new CineplexDB();
        cineplexDB.write(cineplexList);
        movieDB.write(movieList);
    }

    // SELECTION HELPERS

    private void selectCineplex() {
        int choice;
        do {
            int index = 0;
            for (Cineplex cineplex : cineplexList) {
                index++;
                System.out.println("[" + index + "]: " + cineplex.getCineplexName());
            }
            int cineplexSize = cineplexList.size();
            System.out.print("Please select your cineplex of choice: ");
            choice = sc.nextInt();
            choice = choice < 1 || choice > cineplexSize ? 0 : choice;
            if (choice == 0) {
                System.out.println("Invalid choice, Please try again.\n");
            } else {
                break;
            }
        } while (true);
        cineplexObj = cineplexList.get(choice - 1);
        cinemaList = cineplexObj.getListOfCinemas();
    }

    private Cinema selectCinema() {
        int cinemaSize = cinemaList.size();
        int cinemaChoice;

        do {
            System.out.print("Please select your cinema of choice (1-" + cinemaSize + "): ");
            cinemaChoice = sc.nextInt();
            cinemaChoice = cinemaChoice < 1 || cinemaChoice > cinemaSize ? 0 : cinemaChoice;
            if (cinemaChoice == 0) {
                System.out.println("Invalid choice, Please try again.\n");
            } else {
                break;
            }
        } while (true);
        Cinema cinema = cinemaList.get(cinemaChoice-1);
        return cinema;
    }

    private Showing selectShowing(Cinema cinema) {
        Showing showing;
        do {
            System.out.print("Please enter the ID of the show of your choice: ");
            int showingId = sc.nextInt();
            showing = cinema.searchShow(showingId);
            if (showing == null) {
                System.out.println("Invalid Showing ID, Please try again. \n");
            } else {
                break;
            }
        } while (true);

        return showing;
    }

    // PRICE HELPER

    private double calculatePrice(MovieType movieType, CinemaType cinemaClass, AgeType movieGoerAge, DayType showingDayType, SeatType seatType) {
        String movieTypeChoice = movieType.name();
        String cinemaClassChoice = cinemaClass.name();
        String movieGoerAgeChoice = movieGoerAge.name();
        String showingDayTypeChoice = showingDayType.name();
        String seatTypeChoice = seatType.name();

        // MovieType
        double price = settingsObj.getMovieTypePrice(movieTypeChoice);

        // CinemaClass
        double cinemaClassPrice = settingsObj.getCinemaClassPrice(cinemaClassChoice);
        price *= cinemaClassPrice;

        // MovieGoer Age
        double movieGoerAgePrice = settingsObj.getAgeTypePrice(movieGoerAgeChoice);
        price *= movieGoerAgePrice;

        // DayType
        double showingDayTypePrice = settingsObj.getDayTypePrice(showingDayTypeChoice);
        price *= showingDayTypePrice;


        // SeatType
        double seatTypePrice = settingsObj.getSeatTypePrice(seatTypeChoice);
        price *= seatTypePrice;

        return Math.round(price * 100) / 100;
    }
}
