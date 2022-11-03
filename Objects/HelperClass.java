package Objects;

public class HelperClass {

    public HelperClass(){}

    public LocalDate parseDate(String date){
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyyMMdd");
        
        try{
            LocalDate editted_date = LocalDateTime.parse(date, myFormatObj);
            return editted_date;
        } catch(Exception e){
            return null;
        }
    }

    public LocalTime parseTime(String time){
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("hhMM");

        try{
            LocalTime editted_time = LocalDateTime.parse(time, myFormatObj);
            return editted_time;
        } catch(Exception e){
            return null;
        }
    }

    public searchMovies(String phrase, boolean detailed){
        MovieDB movieDB = new MovieDB();
        @SuppressWarnings("unchecked")
        ArrayList<Movie> movieList = (ArrayList<Movie>)movieDB.read(); 

        for(int i=0; i<movieList.size(); i++){
            if((movieList.get(i).getTitle().toLowerCase()).contains(phrase.toLowerCase())){
                if(detailed){
                    System.out.println("Movie Title: " + movieList.get(i).getTitle());
                    int select = movieList.get(i).getStatus();
                    switch(select){
                        case 0:
                            System.out.println("Movie Status: Coming Soon");
                            break;
                        
                        case 1:
                            System.out.println("Movie Status: Preview");
                            break;
                        
                        case 2:
                            System.out.println("Movie Status: Now Showing");
                            break;
                        
                        case 3:
                            System.out.println("Movie Status: End of Showing");
                            break;
                    }
                    System.out.println("Synopsis: " + movieList.get(i).getSynopsis());
                    System.out.println("Director: " + movieList.get(i).getDirector());
                    ArrayList<String> castList = movieList.get(i).getcast();
                    System.out.println("Cast: ");

                    for(int j=0; j<castList.size(); j++){
                        System.out.println(castList(j));
                    }
                    int select_next = movieList.getType();
                    switch(select_next){
                        case 0:
                            System.out.println("Movie Type: Regular");
                            break;

                        case 1:
                            System.out.println("Movie Type: 3D");
                            break;

                        case 2:
                            System.out.println("Movie Type: Blockbuster");
                            break;
                    }
                    System.out.println("----------------");
                }
                else{
                    System.out.println("Movie Title: " + movieList.get(i).getTitle());
                }
            }
        }
    }

}
