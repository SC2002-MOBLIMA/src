package Modules;

import java.util.Scanner;
import java.io.*;
import java.util.*;
import Databases.MovieDB;
import Databases.MovieGoerDB;

import java.util.ArrayList;
import Objects.Cinema;
import Objects.Showing;
import Objects.MovieGoer;
import Objects.Movie;
import Enums.MovieStatus;
import Comparators.SortByRating;
import Comparators.SortBySales;

public class MovieGoerModule {
    private Scanner sc;
    private boolean running;
    private boolean signedIn;
    private MovieGoer movieGoerObject;
    private ArrayList<Movie> allmovies;

    public MovieGoerModule(Scanner sc) {
        this.sc = sc;
    }

    public void run() {
        MovieDB movieDB = new MovieDB();
        MovieGoerDB movieGoerDB = new MovieGoerDB();
        System.out.println("***********************************************");
        System.out.println("MOBLIMA -- Movie Goer Module:");
        ArrayList<Movie> readMovies = (ArrayList<Movie>) movieDB.read();
        ArrayList<MovieGoer> readMovieGoers = (ArrayList<MovieGoer>) movieGoerDB.read();
        allmovies = readMovies;
        String keywords = "";

        boolean validUsername = false;
        while (true) {
            System.out.print("Please enter your username: ");
            String username = sc.nextLine();

            for (MovieGoer mg: readMovieGoers) {
                String mgUsername = mg.getName();
                if (mgUsername.equals(username)) {
                    validUsername = true;
                    movieGoerObject = mg;
                }
            }
            if (validUsername) {
                break;
            } else {
                System.out.println("Error: Username not found. Please try again");
            }
        }
        int input = 0;
        while (input != 8) {
            System.out.println("***********************************************");
            System.out.println("MOBLIMA -- Movie Goer Module: (Movie Goer: " + movieGoerObject.getName() + "):");
            System.out.println("[1] Search Movies\n"
                    + "[2] List movies\n"
                    + "[3] View movie details\n"
                    + "[4] Book Seats\n"
                    + "[5] View Booking history\n"
                    + "[6] List top 5 movies based on sales\n"
                    + "[7] List top 5 movies based on ratings\n"
                    + "[8] Exit");
            System.out.print("Please select an option: ");
            input = sc.nextInt();
            sc.nextLine();
            switch (input) {
                case 1:
                    System.out.print("Please enter the keywords: ");
                    keywords = sc.nextLine();
                    printMovieSearch(keywords, false, allmovies);
                    keywords = "";
                    break;
                case 2:
                    printMovieSearch(keywords, false, allmovies);
                    break;
                case 3:
                    printMovieSearch(keywords, true, allmovies);
                    break;
                case 4:
                    BookingModule bookingModule = new BookingModule(sc, movieGoerObject);
                    bookingModule.run();
                    break;
                case 5:
                    System.out.println(movieGoerObject.getMovieTicketList());
                    break;
                case 6:
                    printMovieBySales();
                    break;
                case 7:
                    printMovieByRating();
                    break;
                case 8:
                    break;
            }

        }
    }

    public void printMovieSearch(String phrase, Boolean detailed, ArrayList<Movie> movies) {
        for (Movie m : movies) {
            if (m.getStatus() == (MovieStatus.NOW_SHOWING) && m.getTitle().contains(phrase)) {
                if (detailed == false) {
                    System.out.println(m.getTitle());
                } else {
                    System.out.println(m.getTitle());
                    System.out.println(m.getStatus());
                    System.out.println(m.getSynopsis());
                    System.out.println(m.getDirector());
                    System.out.println(m.getCast());
                    System.out.println(m.getReviewList());
                    System.out.println(m.getSalesCount());
                    System.out.println(m.getType());
                }
            }
        }
    }

    public void printMovieByRating() {
        int counter = 0;
        MovieDB movieDB = new MovieDB();
        @SuppressWarnings("unchecked")
        ArrayList<Movie> movieList = (ArrayList<Movie>) movieDB.read();
        Collections.sort(movieList, new SortByRating());
        for (Movie m : movieList) {
            while (counter < 5) {
                System.out.println(m.getTitle());
                counter++;
            }
        }
    }

    public void printMovieBySales() {
        int counter = 0;
        MovieDB movieDB = new MovieDB();
        @SuppressWarnings("unchecked")
        ArrayList<Movie> movieList = (ArrayList<Movie>) movieDB.read();
        Collections.sort(movieList, new SortBySales());
        for (Movie m : movieList) {
            while (counter < 5) {
                System.out.println(m.getTitle());
                counter++;
            }
        }
    }
}
