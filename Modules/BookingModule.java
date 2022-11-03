package Modules;

import java.util.ArrayList;
import java.util.Scanner;

import Databases.CineplexDB;
import Objects.Cinema;
import Objects.Cineplex;
import Objects.Movie;
import Objects.MovieGoer;
import Objects.MovieTicket;
import Objects.Showing;

public class BookingModule {
    private Scanner sc;
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
        System.out.println("MOBLIMA -- Booking Module:");

        CineplexDB cineplexDB = new CineplexDB();
        @SuppressWarnings("unchecked")
        ArrayList<Cineplex> readList = (ArrayList<Cineplex>) cineplexDB.read();
        cineplexList = readList;
        selectCineplex();

        boolean running = true;
        while (running) {
        System.out.println("***********************************************");
        System.out.println("MOBLIMA -- Booking Module (Selected Cineplex: " + cineplexObj.getCineplexName() + " ):");
        System.out.println("[1] Display All Cinema Showings");
        System.out.println("[2] Display Cinema Showings");
        System.out.println("[3] Check Showing Seat Availability");
        System.out.println("[4] Select, Book & Purchase Tickets");
        System.out.println("[5] Reselect Cineplex");
        System.out.println("[6] Back");

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
        System.out.println("");
        }
    }

  private void displayCinemaShowings() {
    Cinema cinemaObj = selectCinema();
    cinemaObj.displayAvailableShows();
    System.out.println("");
  }

    private void checkSeatAvailability() {
        Cinema cinemaObj = selectCinema();
        cinemaObj.displayAvailableShows();

        Showing showingObj = selectShowing(cinemaObj);
        showingObj.printSeating();
    }

    private void bookSeat() {
        Cinema cinemaObj = selectCinema();
        cinemaObj.displayAvailableShows();
        Showing showingObj = selectShowing(cinemaObj);
        Movie movieObj = showingObj.getMovie();
        double price = calculatePrice(cinemaObj, showingObj, movieGoerObj);
        System.out.println("***********************************************");
        System.out.println("Chosen Movie: " + showingObj.getMovieTitle() + " | Price: " + price);
        System.out.println("Please enter the number of tickets: ");
        int ticketCount = sc.nextInt();

        ArrayList<String> seatIds = new ArrayList<String>();
        for (int i = 0; i < ticketCount; i++) {
        boolean seatChosen = false;
        do {
            showingObj.printSeating();
            System.out.print("Ticket " + i + 1 + " | ");
            System.out.print("Please enter seat to book (eg. A1): ");
            String seatId = sc.next();
            if (showingObj.isAvailable(seatId)) {
            System.out.print("Ticket " + i + 1 + " | ");
            System.out.println("Seat already occupied, Please try again.\n");
            } else {
            seatIds.set(i, seatId);
            break;
            }
        } while (true);
        }

        System.out.println("Please confirm the details of your booking: ");
        System.out.println("Movie: " + movieObj.getTitle());
        System.out.println("Cineplex: " + cineplexObj.getCineplexName());
        System.out.println("Cinema: " + cinemaObj.getCinemaNum());
        System.out.println("Price: " + price);
        System.out.println("Time: " + showingObj.getFormattedTime());
        System.out.print("Seats: ");
        for (String seatId : seatIds) {
        System.out.print(seatId + " ");
        }
        System.out.println("");
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
        if (confirmInput == 'Y') {
        System.out.println("Booking Successful! Here are the details of your Movie Tickets.");
        for (int i = 0; i < seatIds.size(); i++) {
            String seatId = seatIds.get(i);
            showingObj.assignSeat(movieGoerObj, seatId);
            MovieTicket movieTicket = new MovieTicket(movieGoerObj, price, showingObj, cineplexObj, cinemaObj, seatId);
            movieGoerObj.addMovieTicket(movieTicket);
            movieObj.incrementSaleCount();

            System.out.println("Movie Ticket " + (seatId + 1) + ": ");
            movieTicket.printTicket();
        }
        }
    }

  // SELECTION HELPERS

    private void selectCineplex() {
        int choice;
        do {
        System.out.println("Please select your cineplex of choice: ");
        int index = 0;
        for (Cineplex cineplex : cineplexList) {
            index++;
            System.out.println("[" + index + "]: " + cineplex.getCineplexName());
        }
        int cineplexSize = cineplexList.size();
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
        Cinema cinema = cinemaList.get(cinemaChoice);
        return cinema;
    }

    private Showing selectShowing(Cinema cinema) {
        System.out.print("Please enter the ID of the show of your choice: ");
        int showingId = sc.nextInt();
        Showing showing;
        do {
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

    private double calculatePrice(Cinema cinema, Showing showing, MovieGoer movieGoer) {
        // Factors for price calculation
        // a. type of movie (3D, Blockbuster, etc.) -> showing.getMovieType() // TODO
        // b. class of cinema (e.g. Platinum Movie Suites) -> cinema.getCinemaType()
        // c. age of movie-goer (e.g. adult, senior citizen, child) ->
        // movieGoer.getAgeType() // TODO
        // d. day of the week or public holiday -> showing.getShowTime()
        double price = 1;
        return Math.round(price * 100) / 100;
    }
}
