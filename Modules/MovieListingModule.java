package Modules;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import Databases.MovieDB;
import Enums.MovieStatus;
import Enums.MovieType;

import Interfaces.ModuleInterface;

import Objects.Movie;

public class MovieListingModule implements ModuleInterface {
    private Scanner sc;
    private ArrayList<Movie> movieList;

    public MovieListingModule(Scanner sc) {
        this.sc = sc;
    }

    public void run() {
        boolean running = true;
        while (running) {
            System.out.println("***********************************************");
            System.out.println("MOBLIMA -- Admin -- Movie Listing Module:");
            MovieDB movieDB = new MovieDB();
            movieList = (ArrayList<Movie>) movieDB.read();
            if (movieList == null) {
                movieList = new ArrayList<Movie>();
            }
            System.out.println("[1] Display All Movie Listings");
            System.out.println("[2] Create New Movie Listing");
            System.out.println("[3] Update Movie Listing");
            System.out.println("[4] Remove Movie Listing");
            System.out.println("[5] Back");
            System.out.print("Please enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("***********************************************");
                    System.out.println("MOBLIMA -- Movie Listing Module (Display All Movie Listings):");
                    System.out.println("List of movies: ");
                    for (Movie m: movieList) {
                        String name = m.getTitle();
                        System.out.println(name);
                    }
                    break;

                case 2:
                    createNewMovieListing(movieDB);
                    break;

                case 3:
                    updateMovieListing(movieDB);
                    break;

                case 4:
                    removeMovieListing(movieDB);
                    break;

                case 5:
                    running = false;
                    break;
            }
        }
    }

    private void updateMovieListing(MovieDB movieDB) {
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
            int updateChoice = 0;

            switch (choice) {
                case 1:
                    while (true) {
                        System.out.println("Input new status (COMING_SOON, PREVIEW, NOW_SHOWING)");
                        System.out.println("\n[1] COMING SOON");
                        System.out.println("[2] PREVIEW");
                        System.out.println("[3] NOW SHOWING");
                        System.out.println("[4] END OF SHOWING");
                        System.out.print("\nInput New Movie Status: ");
                        updateChoice = sc.nextInt();
                        sc.nextLine();
                        if (updateChoice <= 4 && updateChoice >= 1) {
                            break;
                        } else {
                            System.out.println("Error: Invalid Movie Status. Please try again");
                        }
                    }
                    MovieStatus status = MovieStatus.values()[updateChoice-1];
                    movie.setStatus(status);
                    break;
                case 2:
                    System.out.print("Input New Sale Count: ");
                    int saleCount = sc.nextInt();
                    sc.nextLine();
                    movie.setSaleCount(saleCount);
                    break;
                case 3:
                    while (true) {
                        System.out.println("\n[1] Regular");
                        System.out.println("[2] 3D");
                        System.out.println("[3] Blockbuster");
                        System.out.print("\nInput New Movie Type: ");
                        updateChoice = sc.nextInt();
                        sc.nextLine();
                        if (updateChoice <= 3 && updateChoice >= 1) {
                            break;
                        } else {
                            System.out.println("Error: Invalid Movie Type. Please try again");
                        }
                    }
                    MovieType type = MovieType.values()[updateChoice-1];
                    movie.setType(type);
                    break;
                case 4:
                    LocalDateTime endOfShowingDate = LocalDateTime.now();
                    while (true) {
                        try {
                            System.out.print("\nInput New End Of Showing Date (dd-MM-yyyy): ");
                            String dateString = sc.nextLine();
                            DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                            endOfShowingDate = LocalDateTime.parse(dateString + " 00:00", dtFormatter);
                            break;
                        } catch (Exception e) {
                            System.out.println("Error: Invalid Date Format. Please try again");
                        }
                    }
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
    private void removeMovieListing(MovieDB movieDB) {
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

    private void createNewMovieListing(MovieDB movieDB) {
        System.out.println("***********************************************");
        System.out.println("MOBLIMA -- Movie Listing Module (Create New Movie Listing):");
        System.out.print("Input Movie Title: ");
        String title = sc.nextLine();
        int choice = 0;
        while (true) {
            System.out.println("\n[1] COMING SOON");
            System.out.println("[2] PREVIEW");
            System.out.println("[3] NOW SHOWING");
            System.out.println("[4] END OF SHOWING");
            System.out.print("\nInput Movie Status: ");
            choice = sc.nextInt();
            sc.nextLine();

            if (choice <= 4 && choice >= 1) {
                break;
            } else {
                System.out.println("Error: Invalid Movie Status. Please try again\n");
            }
        }
        MovieStatus status = MovieStatus.values()[choice-1];

        System.out.print("\nInput Movie Synopsis: ");
        String synopsis = sc.nextLine();

        System.out.print("\nInput Movie Director: ");
        String director = sc.nextLine();

        ArrayList<String> cast = getCast();

        while (true) {
            System.out.println("\n[1] Regular");
            System.out.println("[2] 3D");
            System.out.println("[3] Blockbuster");
            System.out.print("\nInput Movie Type: ");
            choice = sc.nextInt();
            sc.nextLine();
            if (choice <= 3 && choice >= 1) {
                break;
            } else {
                System.out.println("Error: Invalid Movie Type. Please try again");
            }
        }
        MovieType type = MovieType.values()[choice-1];

        while (true) {
            try {
                System.out.print("\nInput Movie End Of Showing Date (dd-MM-yyyy): ");
                String dateString = sc.nextLine();
                DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                LocalDateTime endOfShowingDate = LocalDateTime.parse(dateString + " 00:00", dtFormatter);
        
                Movie newMovie = new Movie(title, status, synopsis, director, cast, type, endOfShowingDate);
                movieList.add(newMovie);
                movieDB.write(movieList);
                break;
            } catch (Exception e) {
                System.out.println("Error: Invalid Date Format. Please try again");
            }
        }

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
