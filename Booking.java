import java.util.Vector;

public class Booking {
    public Vector<Seat> seats = new Vector<>();
    public String seat;
    public String userName;
    public Theatre Theatre;
    public Movie Movie; 
    public City City;
    
    public String getData(){
        if(seat==null){
        seat="";
        for(int i=0; i<seats.size(); i++){
            if(i==seats.size()-1)
                seat+=seats.get(i).getName();
            else
                seat+=seats.get(i).getName()+"-";
        }
    }  
        return userName + "," + Theatre.getName() + "," + City.getName() + "," + Movie.Name + "," + seat;
    }
}
