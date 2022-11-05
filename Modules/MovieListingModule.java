package Modules;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import Databases.MovieDB;
import Enums.MovieStatus;
import Enums.MovieType;
import Objects.Movie;

public class MovieListingModule {
    private Scanner sc;

    public MovieListingModule(Scanner sc) {
        this.sc = sc;
    }

    public void run() {
        boolean running = true;
        while (running) {
        System.out.println("***********************************************");
        System.out.println("MOBLIMA -- Admin -- Movie Listing Module:");
        MovieDB movieDB = new MovieDB();
        ArrayList<Movie> movieList = (ArrayList<Movie>) movieDB.read();

        System.out.println("[1] Create New Movie Listing");
        System.out.println("[2] Update Movie Listing");
        System.out.println("[3] Remove Movie Listing");
        System.out.println("[4] Back");
        System.out.print("Please enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1:
                getNewMovieListing(movieList, movieDB);
            break;

            case 2:
                updateMovieListing(movieList, movieDB);
            break;

            case 3:
                removeMovieListing(movieList, movieDB);
            break;

            case 4:
                running = false;
                System.out.println("Movie Listing Module stoppping");
                System.out.println("***********************************************");
                break;
        }
        }
    }

    private void updateMovieListing(ArrayList<Movie> movieList, MovieDB movieDB) {
        System.out.println("***********************************************");
        System.out.println("MOBLIMA -- Movie Listing Module (Update Existing Movie Listing):");
        boolean foundMovie = false;
        System.out.print("\nPlease enter the title of the movie: ");
        String title = sc.nextLine();
        for (Movie m : movieList) {
            if (m.getTitle().equalsIgnoreCase(title)) {
                movieList.remove(m);
                Movie updatedMovie = getUpdatedMovie(m);
                movieList.add(updatedMovie);
                foundMovie = true;
            }
        }
        if (foundMovie) {
            movieDB.write(movieList);
            System.out.println("Movie Listing successfully updated");
        } else {
            System.out.println("Movie not found");
        }
        System.out.println("***********************************************");
    }

    private Movie getUpdatedMovie(Movie movie) {
        boolean run = true;

        do {
        System.out.println("******************************");
        System.out.println("Possible List of Updates:");
        System.out.println("[1] Status");
        System.out.println("[2] Sale Count");
        System.out.println("[3] Type");
        System.out.println("[4] End Of Showing Date");
        System.out.println("[5] Back / Done");
        System.out.println("******************************");

        System.out.println("What do you want to update?");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Input new status (COMING_SOON, PREVIEW, NOW_SHOWING)");
                System.out.println("\n[1] COMING SOON");
                System.out.println("[2] PREVIEW");
                System.out.println("[3] NOW SHOWING");
                System.out.println("[4] END OF SHOWING");
                System.out.print("\nInput New Movie Status: ");
                int movieStatusChoice = sc.nextInt();
                sc.nextLine();
                MovieStatus status = MovieStatus.values()[movieStatusChoice];
                movie.setStatus(status);
                break;
            case 2:
                System.out.print("Input New Sale Count: ");
                int saleCount = sc.nextInt();
                sc.nextLine();
                movie.setSaleCount(saleCount);
                break;
            case 3:
                System.out.println("\n[1] Regular");
                System.out.println("[2] 3D");
                System.out.println("[3] Blockbuster");
                System.out.print("\nInput New Movie Type: ");
                int movieTypeChoice = sc.nextInt();
                sc.nextLine();
                MovieType type = MovieType.values()[movieTypeChoice];
                movie.setType(type);
                break;
            case 4:
                System.out.print("\nInput New End Of Showing Date (dd-MM-yyyy): ");
                String dateString = sc.nextLine();
                DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                LocalDateTime endOfShowingDate = LocalDateTime.parse(dateString + " 00:00", dtFormatter);
                movie.setEndOfShowingDate(endOfShowingDate);
                break;
            case 5:
                run = false;
                break;
            default:
                System.out.println("Error: Invalid Choice, Please try again");
                break;
        }
        } while (run);

        return movie;
    }

    // Update the status of the Movie Listing as "END_OF_SHOWING"
    private void removeMovieListing(ArrayList<Movie> movieList, MovieDB movieDB) {
        System.out.println("***********************************************");
        System.out.println("MOBLIMA -- Movie Listing Module (Remove Movie Listing):");

        boolean foundMovie = false;
        System.out.print("\nPlease enter the title of the movie: ");
        String title = sc.nextLine();
        for (Movie m : movieList) {
            if (m.getTitle().equalsIgnoreCase(title)) {
                movieList.remove(m);
                m.setStatus(MovieStatus.COMING_SOON);
                movieList.add(m);
                foundMovie = true;
            }
        }
        if (foundMovie) {
            movieDB.write(movieList);
            System.out.println("Movie Listing successfully removed");
        } else {
            System.out.println("Movie not found");
        }
        System.out.println("***********************************************");

    }

    private void getNewMovieListing(ArrayList<Movie> movieList, MovieDB movieDB) {
        System.out.println("***********************************************");
        System.out.println("MOBLIMA -- Movie Listing Module (Create New Movie Listing):");
        System.out.print("Input Movie Title: ");
        String title = sc.nextLine();
        System.out.println("\n[1] COMING SOON");
        System.out.println("[2] PREVIEW");
        System.out.println("[3] NOW SHOWING");
        System.out.println("[4] END OF SHOWING");
        System.out.print("\nInput Movie Status: ");
        int movieStatusChoice = sc.nextInt();
        sc.nextLine();
        MovieStatus status = MovieStatus.values()[movieStatusChoice];
        System.out.print("\nInput Movie Synopsis: ");
        String synopsis = sc.nextLine();

        System.out.print("\nInput Movie Director: ");
        String director = sc.nextLine();

        ArrayList<String> cast = getCast();

        System.out.println("\n[1] Regular");
        System.out.println("[2] 3D");
        System.out.println("[3] Blockbuster");
        System.out.print("\nInput Movie Type: ");
        int movieTypeChoice = sc.nextInt();
        sc.nextLine();
        MovieType type = MovieType.values()[movieTypeChoice];

        System.out.print("\nInput Movie End Of Showing Date (dd-MM-yyyy): ");
        String dateString = sc.nextLine();
        DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime endOfShowingDate = LocalDateTime.parse(dateString + " 00:00", dtFormatter);

        Movie newMovie = new Movie(title, status, synopsis, director, cast, type, endOfShowingDate);

        movieList.add(newMovie);
        movieDB.write(movieList);

        System.out.println("New Movie Listing successfully added");
        System.out.println("***********************************************");
    }

    private ArrayList<String> getCast() {
        ArrayList<String> cast = new ArrayList<String>();

        int numberOfCast = 0;

        boolean run = true;
        do {
            System.out.print("\nInput number of cast members: ");
            numberOfCast = sc.nextInt();
            sc.nextLine();
            if (numberOfCast < 2) {
                System.out.println("There must be at least 2 cast members!");
            } else {
                run = false;
            }
        } while (run);

        for (int i = 0; i < numberOfCast; i++) {
            System.out.print("Input Movie Cast: ");
            String castMember = sc.nextLine();
            cast.add(castMember);
        }

        return cast;
    }
}
