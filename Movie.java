public class Movie {
    String Name;
    String Summary;
    Float Rating;
    Integer Year;
    public String showData(){
        return Name + "\n\n" + Rating + "\n" + Year + "\n" + Summary;
    }
    public static Movie getMovie(String Name){
        for(int i=0; i<Main.movies.size(); i++){
            if(Main.movies.get(i).Name.equals(Name)){
                return Main.movies.get(i);
            }
        }
        return null;
    }
}