import java.io.*;
import java.util.Vector;

import javax.swing.*;
public class Database {
    public static void addTicketDetails(Booking ticket){
        try (FileWriter writer = new FileWriter("usertickets.csv", true)){
            writer.append(ticket.getData());
            writer.append("\n");
        } catch (IOException e) {
            System.out.println("Error opening file usertickets.csv");
        }
    }

    public static void loadTicketDetails(){
        try (BufferedReader reader = new BufferedReader(new FileReader("usertickets.csv"))){
            String line = reader.readLine();
            while((line=reader.readLine())!=null) {
                String[] fields = line.split(",");
                Booking ticket = new Booking();
                ticket.Theatre = Theatre.getTheatre(fields[1]);
                ticket.City = City.getCity(fields[2]);
                ticket.Movie = Movie.getMovie(fields[3]);
                ticket.seat = fields[4];
                User user = User.getUser(fields[0]);
                ticket.userName=user.getUsername();
                user.BookingsCurrUser.add(ticket);
                Main.bookings.add(ticket);
                Vector<String> s = new Vector<>();
                int k=0;
                for(int p=0; p<fields[4].split("-").length; p++){
                    s.add(fields[4].split("-")[p]);
                }
                if(ticket.Theatre.Movies_corres.get(0).Name.equals(ticket.Movie.Name)){
                    k=0;}
                else if(ticket.Theatre.Movies_corres.get(1).Name.equals(ticket.Movie.Name)){
                    k=1;}
                else{
                    k=2;}
                switch (k) {
                    case 0:
                        for(int i=0; i<s.size(); i++){
                            ticket.Theatre.show1_occ.add(s.get(i));
                        }
                        break;
                    case 1:
                        for(int i=0; i<s.size(); i++){
                            ticket.Theatre.show2_occ.add(s.get(i));
                        }
                        break;
                    case 2:
                        for(int i=0; i<s.size(); i++){
                            ticket.Theatre.show3_occ.add(s.get(i));
                        }
                        break;
                    default:
                        break;
                }
            }
            System.out.println("Previous Tickets loaded from CSV");
        } catch(IOException e){
            System.out.println("Error loading usertickets");
        }
    }
    
    public static void loadMoviesfromCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Movies.csv"))) {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                Movie movie_curr = new Movie();
                movie_curr.Name = fields[0];
                movie_curr.Summary=fields[1];
                movie_curr.Rating=Float.parseFloat(fields[2]);
                movie_curr.Year=Integer.parseInt(fields[3]);
                Main.movies.add(movie_curr);
            }
        } catch (FileNotFoundException e) {
            System.out.println("CSV file not found");
        } catch (IOException ex) {
            System.out.println("Error opening file Movies.csv");
        }
    }

    public static void writeMovietoCSV(String Movie, String Summary, Float Rating, Integer Year) {
        for(int i=0; i<Main.movies.size(); i++){
            if(Main.movies.get(i).Name.equals(Movie)){
                JFrame error = new JFrame();
                error.setVisible(true);
                error.setSize(300,100);
                error.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                error.setLayout(null);
                JLabel ErrorMessage = new JLabel("This movie exists.");
                ErrorMessage.setBounds(0,0,100,100);
                error.add(ErrorMessage);
                return;
            }
        }

        try (FileWriter writer = new FileWriter("Movies.csv", true)) {
            writer.append(Movie);
            writer.append(",");
            writer.append(Summary);
            writer.append(",");
            writer.append(Rating.toString());
            writer.append(",");
            writer.append(Year.toString());
            writer.append("\n");
            System.out.println("added");
        } catch (IOException e) {
            System.out.println("Error writing movie to CSV: " + e.getMessage());
        }
    }
    
    public static void loadTheatresfromCSV(){
        try(BufferedReader reader = new BufferedReader(new FileReader("theatre_movie.csv"))){
            String line = reader.readLine();
            while((line=reader.readLine())!=null){
                Theatre theatre = new Theatre();
                String[] fields = line.split(",");
                theatre.setName(fields[1]);
                theatre.Movies_corres.add(Movie.getMovie(fields[2]));
                theatre.Movies_corres.add(Movie.getMovie(fields[3]));
                theatre.Movies_corres.add(Movie.getMovie(fields[4]));
                Show show1 = new Show();
                Show show2 = new Show();
                Show show3 = new Show();
                theatre.Shows_corres.add(show1);
                theatre.Shows_corres.add(show2);
                theatre.Shows_corres.add(show3);
                for(int i=0; i<Integer.parseInt(fields[5]); i++){
                    Seat seat1 = new Seat();
                    Seat seat2 = new Seat();
                    Seat seat3 = new Seat();
                    seat1.setType("VIP");
                    seat2.setType("VIP");
                    seat3.setType("VIP");
                    show1.Seats.add(seat1);
                    show2.Seats.add(seat2);
                    show3.Seats.add(seat3);
                }
                for(int i=0; i<Integer.parseInt(fields[6]); i++){
                    Seat seat1 = new Seat();
                    Seat seat2 = new Seat();
                    Seat seat3 = new Seat();
                    seat1.setType("GOLD");
                    seat2.setType("GOLD");
                    seat3.setType("GOLD");
                    show1.Seats.add(seat1);
                    show2.Seats.add(seat2);
                    show3.Seats.add(seat3);
                }
                for(int i=0; i<Integer.parseInt(fields[7]); i++){
                    Seat seat1 = new Seat();
                    Seat seat2 = new Seat();
                    Seat seat3 = new Seat();
                    seat1.setType("LONGUE");
                    seat2.setType("LONGUE");
                    seat3.setType("LONGUE");
                    show1.Seats.add(seat1);
                    show2.Seats.add(seat2);
                    show3.Seats.add(seat3);
                }
                for(int i=0; i<Main.cities.size(); i++){
                    if(Main.cities.get(i).getName().equals(fields[0])){
                        Main.cities.get(i).Theatres_corres.add(theatre);
                    }
                }
                Main.theatres.add(theatre);
            }
        }catch (FileNotFoundException e) {
            System.out.println("CSV file not found");
        } catch (IOException e) {
            System.out.println("Error loading theatres from CSV: " + e.getMessage());
        }
    }

    public static void writeTheatretoCSV(String Theatre, String City, String Movie1, String Movie2, String Movie3, String seats) {
        Theatre newtheatre = null;
        for(int i=0; i<Main.cities.size(); i++){
            if(City.equals(Main.cities.get(i).getName())){
                for(int j=0; j<Main.cities.get(i).Theatres_corres.size(); j++){
                    if(Theatre.equals(Main.cities.get(i).Theatres_corres.get(j).getName())){
                        JFrame error = new JFrame();
                        JOptionPane.showMessageDialog(error,"Theatre already exits!", "ERROR", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }
                newtheatre = new Theatre();
                newtheatre.setName(Theatre);
                Main.cities.get(i).Theatres_corres.add(newtheatre);
                Main.theatres.add(newtheatre);
            }
        }
        String[] Movie1_data = Movie1.split(",");
        String[] Movie2_data = Movie2.split(",");
        String[] Movie3_data = Movie3.split(",");
        Movie movie1 = Movie.getMovie(Movie1_data[0]);
        Movie movie2 = Movie.getMovie(Movie2_data[0]);
        Movie movie3 = Movie.getMovie(Movie3_data[0]);
        if(movie1==null){
            movie1 = new Movie();
            movie1.Name = Movie1_data[0];
            movie1.Rating = Float.parseFloat(Movie1_data[1]);
            movie1.Year = Integer.parseInt(Movie1_data[2]);
            movie1.Summary = Movie1_data[3];
            Main.movies.add(movie1);
            try (FileWriter writer = new FileWriter("Movies.csv", true)) {
                writer.append(movie1.Name);
                writer.append(",");
                writer.append(movie1.Summary);
                writer.append(",");
                writer.append(movie1.Rating.toString());
                writer.append(",");
                writer.append(movie1.Year.toString());
                writer.append("\n");
                System.out.println("added");
            } catch (IOException e) {
                System.out.println("Error writing movie to CSV: " + e.getMessage());
            }
        }if(movie2==null){
            movie2 = new Movie();
            movie2.Name = Movie2_data[0];
            movie2.Rating = Float.parseFloat(Movie2_data[1]);
            movie2.Year = Integer.parseInt(Movie2_data[2]);
            movie2.Summary = Movie2_data[3];
            Main.movies.add(movie2);
            try (FileWriter writer = new FileWriter("Movies.csv", true)) {
                writer.append(movie2.Name);
                writer.append(",");
                writer.append(movie2.Summary);
                writer.append(",");
                writer.append(movie2.Rating.toString());
                writer.append(",");
                writer.append(movie2.Year.toString());
                writer.append("\n");
                System.out.println("added");
            } catch (IOException e) {
                System.out.println("Error writing movie to CSV: " + e.getMessage());
            }
        }if(movie3==null){
            movie3 = new Movie();
            movie3.Name = Movie3_data[0];
            movie3.Rating = Float.parseFloat(Movie3_data[1]);
            movie3.Year = Integer.parseInt(Movie3_data[2]);
            movie3.Summary = Movie3_data[3];
            Main.movies.add(movie1);
            try (FileWriter writer = new FileWriter("Movies.csv", true)) {
                writer.append(movie3.Name);
                writer.append(",");
                writer.append(movie3.Summary);
                writer.append(",");
                writer.append(movie3.Rating.toString());
                writer.append(",");
                writer.append(movie3.Year.toString());
                writer.append("\n");
                System.out.println("added");
            } catch (IOException e) {
                System.out.println("Error writing movie to CSV: " + e.getMessage());
            }
        }
        newtheatre.Movies_corres.add(movie1);
        newtheatre.Movies_corres.add(movie2);
        newtheatre.Movies_corres.add(movie3);

        Show show1 = new Show();
        Show show2 = new Show();
        Show show3 = new Show();
        newtheatre.Shows_corres.add(show1);
        newtheatre.Shows_corres.add(show2);
        newtheatre.Shows_corres.add(show3);
        String[] seats_data = seats.split("-");

        for(int i=0; i<Integer.parseInt(seats_data[0]); i++){
            Seat seat1 = new Seat();
            Seat seat2 = new Seat();
            Seat seat3 = new Seat();
            seat1.setType("VIP");
            seat2.setType("VIP");
            seat3.setType("VIP");
            show1.Seats.add(seat1);
            show2.Seats.add(seat2);
            show3.Seats.add(seat3);
        }
        for(int i=0; i<Integer.parseInt(seats_data[1]); i++){
            Seat seat1 = new Seat();
            Seat seat2 = new Seat();
            Seat seat3 = new Seat();
            seat1.setType("GOLD");
            seat2.setType("GOLD");
            seat3.setType("GOLD");
            show1.Seats.add(seat1);
            show2.Seats.add(seat2);
            show3.Seats.add(seat3);
        }
        for(int i=0; i<Integer.parseInt(seats_data[2]); i++){
            Seat seat1 = new Seat();
            Seat seat2 = new Seat();
            Seat seat3 = new Seat();
            seat1.setType("LONGUE");
            seat2.setType("LONGUE");
            seat3.setType("LONGUE");
            show1.Seats.add(seat1);
            show2.Seats.add(seat2);
            show3.Seats.add(seat3);
        }


        try (FileWriter writer = new FileWriter("theatres.csv", true)) {
            writer.append(City);
            writer.append(",");
            writer.append(Theatre);
            writer.append("\n");
            System.out.println("added");
        } catch (IOException e) {
            System.out.println("Error writing to CSV: " + e.getMessage());
        }

        try(FileWriter writer = new FileWriter("theatre_movie.csv", true)){
            writer.append(City);
            writer.append(",");
            writer.append(newtheatre.getName());
            writer.append(",");
            writer.append(Movie1_data[0]);
            writer.append(",");
            writer.append(Movie2_data[0]);
            writer.append(",");
            writer.append(Movie3_data[0]);
            writer.append(",");
            writer.append(seats_data[0]);
            writer.append(",");
            writer.append(seats_data[1]);
            writer.append(",");
            writer.append(seats_data[2]);
            writer.append("\n");
            JFrame success = new JFrame();
            JOptionPane.showMessageDialog(success, "Theatre added successfully");
        } catch (IOException e) {
            System.out.println("Error writing to CSV: " + e.getMessage());
        }
    }

    public static void writeCitytoCSV(String City) {
        for(int i=0; i<Main.cities.size(); i++){
            if(City.equals(Main.cities.get(i).getName())){
                JFrame error = new JFrame();
                error.setVisible(true);
                error.setSize(300,100);
                error.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                error.setLayout(null);
                JLabel ErrorMessage = new JLabel("This city already exists");
                ErrorMessage.setBounds(0,0,100,100);
                return;
            }
        }
        City newcity = new City();
        newcity.setName(City);
        Main.cities.add(newcity);
        try (FileWriter writer = new FileWriter("city.csv", true)) {
            writer.append(City);
            writer.append("\n");
            System.out.println("City added to CSV successfully.");
            JFrame success = new JFrame();
            JOptionPane.showMessageDialog(success, "Theatre added successfully");
        } catch (IOException e) {
            System.out.println("Error writing to CSV: " + e.getMessage());
        }
    }

    public static void loadCitiesFromCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader("city.csv"))) {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                City city_curr = new City();
                city_curr.setName(line);
                Main.cities.add(city_curr);
            }
            System.out.println("Cities loaded from CSV successfully");
        } catch (FileNotFoundException e) {
            System.out.println("CSV file not found.");
        } catch (IOException e) {
            System.out.println("Error loading cities from CSV: " + e.getMessage());
        }
    }

    public static void signup(String username, String Password, boolean user, long number) {
        if(User.getUser(username)!=null || Admin.getAdmin(username)!=null) {
            JFrame UsernameTaken = new JFrame();
            JOptionPane.showMessageDialog(UsernameTaken,"This username is already taken :(","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        JFrame success = new JFrame();
        JOptionPane.showMessageDialog(success,"You successfully signed-up","",JOptionPane.INFORMATION_MESSAGE);
        if (user) {
            User users = new User();
            users.setUsername(username);
            users.setPassword(RailFence.encrypt(Password));;
            users.setNumber(number);
            Main.users.add(users);
            writeAccountToCSV(username, Password, number, "User");
        } else {
            Admin admins = new Admin();
            admins.setUsername(username);
            admins.setPassword(RailFence.encrypt(Password));
            admins.setNumber(number);
            Main.admins.add(admins);
            writeAccountToCSV(username, Password, number, "Admin");
        }
    }

    public static boolean signin(String username, String password, boolean user) {
        if (user) {
            for (int i = 0; i < Main.users.size(); i++) {
                if (Main.users.get(i).getUsername().equals(username)) {
                    if (Main.users.get(i).getPassword().equals(RailFence.encrypt(password))) {
                        return true;
                    }
                }
            }
        } else {
            for (int i = 0; i < Main.admins.size(); i++) {
                if (Main.admins.get(i).getUsername().equals(username)) {
                    if (Main.admins.get(i).getPassword().equals(RailFence.encrypt(password))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void loadAccountsFromCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader("accounts.csv"))) {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                String username = fields[0];
                String password = fields[1];
                long number = Long.parseLong(fields[2]);
                String type = fields[3];

                if (type.equals("User")) {
                    User userObj = new User();
                    userObj.setUsername(username);;
                    userObj.setPassword(password);;
                    userObj.setNumber(number);
                    Main.users.add(userObj);
                } else if (type.equals("Admin")) {
                    Admin adminObj = new Admin();
                    adminObj.setUsername(username);
                    adminObj.setPassword(password);
                    adminObj.setNumber(number);
                    Main.admins.add(adminObj);
                }
            }
            System.out.println("Accounts loaded from CSV file successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("CSV file not found.");
        } catch (IOException e) {
            System.out.println("Error loading accounts from CSV: " + e.getMessage());
        }
    }

    public static void writeAccountToCSV(String username, String password, long number, String type) {
        try (FileWriter writer = new FileWriter("accounts.csv", true)) {
            writer.append(username);
            writer.append(",");
            writer.append(RailFence.encrypt(password));
            writer.append(",");
            writer.append(Long.toString(number));
            writer.append(",");
            writer.append(type);
            writer.append("\n");
            System.out.println("Account added to CSV successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to CSV: " + e.getMessage());
        }
    }
}