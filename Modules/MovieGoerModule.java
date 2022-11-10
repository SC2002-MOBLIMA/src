package Modules;

import java.util.Scanner;
import java.util.Collections;
import Databases.MovieDB;
import Databases.MovieGoerDB;

import java.util.ArrayList;
import Objects.MovieGoer;
import Objects.Movie;
import Objects.Review;
import Objects.MovieTicket;
import Enums.MovieStatusType;
import Interfaces.LoginInterface;
import Interfaces.ModuleInterface;
import Comparators.SortByRating;
import Comparators.SortBySales;

public class MovieGoerModule implements ModuleInterface, LoginInterface {
    private Scanner sc;
    private boolean isLoggedIn;
    private MovieGoer movieGoerObj;
    private ArrayList<MovieGoer> movieGoerList;
    private ArrayList<Movie> allMovies;

    public MovieGoerModule(Scanner sc) {
        this.sc = sc;
        this.isLoggedIn = true; // TODO: toggle this back
    }

    public void run() {
        MovieDB movieDB = new MovieDB();
        MovieGoerDB movieGoerDB = new MovieGoerDB();
        System.out.println("***********************************************");
        System.out.println("MOBLIMA -- Movie Goer Module:\n");
        allMovies = movieDB.read();
        movieGoerList = movieGoerDB.read();
        String keywords = "";

        login();

        int input = 0;
        while (input != 8) {
            System.out.println("***********************************************");
            System.out.println("MOBLIMA -- Movie Goer Module (Movie Goer: " + movieGoerObj.getName() + "):");
            System.out.println("[1] Search Movies\n"
                    + "[2] List Movies\n"
                    + "[3] View Movie Details\n"
                    + "[4] Book Seats\n"
                    + "[5] View Booking History\n"
                    + "[6] List Top 5 Movies Based on Sales\n"
                    + "[7] List Top 5 Movies Based on Ratings\n"
                    + "[8] Back");
            System.out.print("Please select an option: ");
            input = sc.nextInt();
            sc.nextLine();
            System.out.println("***********************************************");
            switch (input) {
                case 1:
                    System.out.println("MOBLIMA -- Movie Goer Module (Search Movies): ");
                    System.out.print("Please enter the keyword(s): ");
                    keywords = sc.nextLine();
                    System.out.println();
                    printMoviesSearch(keywords, false);
                    keywords = "";
                    break;
                case 2:
                    System.out.println("MOBLIMA -- Movie Goer Module (List Movies): ");
                    System.out.println();
                    printMoviesSearch(keywords, false);
                    break;
                case 3:
                    System.out.println("MOBLIMA -- Movie Goer Module (View Movie Details): ");
                    System.out.print("Please enter the keywords: ");
                    keywords = sc.nextLine();
                    System.out.println();
                    printMoviesSearch(keywords, true);
                    break;

                case 4:
                    BookingModule bookingModule = new BookingModule(sc, movieGoerObj);
                    bookingModule.run();
                    movieGoerDB.write(movieGoerList);
                    movieDB.write(allMovies);
                    break;

                case 5:
                    System.out.println("MOBLIMA -- Movie Goer Module (View Booking History): ");
                    System.out.println();
                    if (!movieGoerObj.getMovieTicketList().isEmpty()) {
                        ArrayList<MovieTicket> mtList = movieGoerObj.getMovieTicketList();
                        for (MovieTicket m : mtList) {
                            m.printTicket();
                        }
                    } else {
                        System.out.println("No Past Bookings\n");
                    }
                    break;

                case 6:
                    printMovieListBySales();
                    break;

                case 7:
                    printMovieListByRating();
                    break;

                case 8:
                    break;

            }

        }
    }

    public void login() {
        if (isLoggedIn) {
            movieGoerObj = movieGoerList.get(0);
        }

        while (!isLoggedIn) {
            System.out.print("Please enter your username: ");
            String username = sc.nextLine();
            
            boolean validUsername = false;
            for (MovieGoer mg : movieGoerList) {
                String mgUsername = mg.getName();
                if (mgUsername.equals(username)) {
                    validUsername = true;
                    movieGoerObj = mg;
                }
            }
            if (validUsername) {
                isLoggedIn = true;
            } else {
                System.out.println("Error: Username not found. Please try again");
            }
        }
    }

    private void printMoviesSearch(String phrase, boolean detailed) {
        int index = 0;
        System.out.println("Results: ");
        for (Movie m : allMovies) {
            if (m.getStatus() == (MovieStatusType.NOW_SHOWING) && m.getTitle().contains(phrase)) {
                if (detailed == false) {
                    System.out.println("[" + (index+1) + "] " + m.getTitle());
                    index++;
                } else {
                    System.out.println("Title: " + m.getTitle());
                    System.out.println("Movie Status: " + m.getStatus());
                    System.out.println("Movie Synopsis: " + m.getSynopsis());
                    System.out.println("Movie Director: " + m.getDirector());
                    System.out.print("Cast Members: ");
                    ArrayList<String> castMembers = m.getCast();
                    System.out.println(String.join(", ", castMembers));
                    System.out.print("Reviews: ");
                    if (!m.getReviewList().isEmpty()) {
                        for (Review movieReview : m.getReviewList()) {
                            System.out.println();
                            System.out.println("Name: "  + movieReview.getName());
                            System.out.println("Rating: "  + movieReview.getRating());
                            System.out.println("Review: "  + movieReview.getReview());
                        }
                    } else {
                        System.out.println("-");
                    }

                    try {
                        System.out.println("\n Overall rating: " + m.getOverallRating());
                    } catch (ArithmeticException e) {
                        ;
                    }

                    System.out.println("Sales Count: " + m.getSaleCount());
                    System.out.println("Movie Type: " + m.getType() + "\n");
                }
            }
        }
    }

    private void printMovieListByRating() {
        System.out.println("MOBLIMA -- Movie Goer Module (Top 5 Movies Based on Rating): ");
        System.out.println();
        int counter = 1;
        MovieDB movieDB = new MovieDB();
        ArrayList<Movie> movieList = (ArrayList<Movie>) movieDB.read();
        Collections.sort(movieList, new SortByRating());
        for (Movie m : movieList) {
            System.out.print("[" + counter + "] ");
            System.out.print(m.getTitle());
            System.out.print(" - Overall Rating: " + m.getOverallRating() + "\n");
            counter++;

            if (counter >= 6) {
                break;
            }
        }
    }

    private void printMovieListBySales() {
        System.out.println("MOBLIMA -- Movie Goer Module (Top 5 Movies Based on Sales): ");
        System.out.println();
        int counter = 1;
        MovieDB movieDB = new MovieDB();
        ArrayList<Movie> movieList = (ArrayList<Movie>) movieDB.read();
        Collections.sort(movieList, new SortBySales());
        for (Movie m : movieList) {
            System.out.print("[" + counter + "] ");
            System.out.print(m.getTitle());
            System.out.print(" - Total Sales: " + m.getSaleCount() + "\n");
            counter++;

            if (counter >= 6) {
                break;
            }
        }
    }
}
