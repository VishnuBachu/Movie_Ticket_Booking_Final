import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
public class User extends Account{
    
    public Vector<Booking> BookingsCurrUser = new Vector<>();

    public String showData(){
        return "Username: "+ getUsername() + "\n\n" + "Number: " + getNumber();
    }
    public static User getUser(String username){
        for(int i=0; i<Main.users.size(); i++){
            if(username.equals(Main.users.get(i).getUsername())){
                return Main.users.get(i);
            }
        }
        return null;
    }
    public void bookTicket() {
        Booking currUserTicket = new Booking();
        currUserTicket.userName = getUsername();
        String[] cityNames = new String[Main.cities.size()];
        for (int i = 0; i < Main.cities.size(); i++)
            cityNames[i] = Main.cities.get(i).getName();
        JComboBox<String> select_city = new JComboBox<>(cityNames);
        select_city.setBounds(100, 100, 100, 50);
        JFrame f_selectcity = new JFrame("Select City");
        JMenuBar mb = new JMenuBar();
        JMenuItem back = new JMenuItem("\t\t\tBack");
        mb.add(back);
        f_selectcity.setJMenuBar(mb);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                f_selectcity.dispose();
            }
        });
        f_selectcity.setSize(500, 500);
        f_selectcity.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f_selectcity.setVisible(true);
        f_selectcity.setLayout(null);
        f_selectcity.add(select_city);
        JButton next_city = new JButton("Next->");
        next_city.setBounds(100, 300, 100, 50);
        f_selectcity.add(next_city);
        next_city.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < Main.cities.size(); i++) {
                    if (Main.cities.get(i).getName().equals(select_city.getSelectedItem().toString())) {
                        currUserTicket.City = Main.cities.get(i);
                    }
                }
                f_selectcity.dispose();
                JFrame f_selecttheatre = new JFrame();
                JMenuBar mb = new JMenuBar();
                JMenuItem back = new JMenuItem("\t\t\tBack");
                mb.add(back);
                f_selecttheatre.setJMenuBar(mb);
                back.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        f_selecttheatre.dispose();
                    }
                });
                f_selecttheatre.setSize(500, 500);
                f_selecttheatre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f_selecttheatre.setVisible(true);
                f_selecttheatre.setLayout(null);
                String[] theatreNames = new String[currUserTicket.City.Theatres_corres.size()];
                for (int i = 0; i < theatreNames.length; i++) {
                    theatreNames[i] = currUserTicket.City.Theatres_corres.get(i).getName();
                }
                JComboBox<String> select_Theatre = new JComboBox<>(theatreNames);
                f_selecttheatre.add(select_Theatre);
                select_Theatre.setBounds(100, 100, 100, 50);
                JButton next_theatre = new JButton("next->");
                next_theatre.setBounds(100, 300, 100, 50);
                f_selecttheatre.add(next_theatre);
                next_theatre.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        for (int i = 0; i < currUserTicket.City.Theatres_corres.size(); i++) {
                            if (currUserTicket.City.Theatres_corres.get(i).getName()
                                    .equals(select_Theatre.getSelectedItem().toString())) {
                                currUserTicket.Theatre = currUserTicket.City.Theatres_corres.get(i);
                            }
                        }
                        f_selecttheatre.dispose();
                        JFrame f_selectMovie = new JFrame();
                        JMenuBar mb = new JMenuBar();
                        JMenuItem back = new JMenuItem("\t\t\tBack");
                        mb.add(back);
                        f_selectMovie.setJMenuBar(mb);
                        back.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e){
                                f_selectMovie.dispose();
                            }
                        });
                        f_selectMovie.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        f_selectMovie.setLayout(null);
                        f_selectMovie.setVisible(true);
                        f_selectMovie.setSize(500, 500);
                        String[] movieName = new String[currUserTicket.Theatre.Movies_corres.size()];
                        for (int i = 0; i < movieName.length; i++) {
                            movieName[i] = currUserTicket.Theatre.Movies_corres.get(i).Name;
                        }
                        JComboBox<String> select_Movie = new JComboBox<>(movieName);
                        f_selectMovie.setTitle("SELECT MOVIE");
                        select_Movie.setBounds(100, 100, 100, 50);
                        JButton next_Movie = new JButton("next->");
                        next_Movie.setBounds(100, 300, 100, 50);
                        f_selectMovie.add(select_Movie);
                        f_selectMovie.add(next_Movie);
                        next_Movie.addActionListener(new ActionListener() {
                           public void actionPerformed(ActionEvent e) {
                                f_selectMovie.dispose();
                                int k=0;
                                for (int i = 0; i < currUserTicket.Theatre.Movies_corres.size(); i++) {
                                    if (currUserTicket.Theatre.Movies_corres.get(i).Name
                                            .equals(select_Movie.getSelectedItem().toString())) {
                                        currUserTicket.Movie = currUserTicket.Theatre.Movies_corres.get(i);
                                        k=i;
                                    }
                                }
                                System.out.println(k);
                                JFrame f_selectseat = new JFrame("SELECT SEATS");
                                JMenuBar mb = new JMenuBar();
                                JMenuItem back = new JMenuItem("\t\t\tBack");
                                mb.add(back);
                                f_selectseat.setJMenuBar(mb);
                                back.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e){
                                       f_selectseat.dispose();
                                   }
                                });
                                f_selectseat.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                f_selectseat.setLayout(new GridLayout(4, 0));
                                f_selectseat.setVisible(true);
                                f_selectseat.setSize(500, 500);
                                JPanel nextbut = new JPanel();
                                nextbut.setLayout(new FlowLayout());
                                JButton next = new JButton("Next: ");
                                next.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        BookingsCurrUser.add(currUserTicket);
                                        Main.bookings.add(currUserTicket);
                                        f_selectseat.dispose();
                                        if(!currUserTicket.seats.isEmpty())
                                            Database.addTicketDetails(currUserTicket);
                                        f_selectMovie.dispose();
                                        f_selecttheatre.dispose();
                                        f_selectcity.dispose();
                                        JFrame booked = new JFrame();
                                        JOptionPane.showMessageDialog(booked,"Your ticket has been successfully booked :\n"+currUserTicket.getData(),"Success",JOptionPane.INFORMATION_MESSAGE);

                                        SendWhatsappMsg.sendMessageDa(currUserTicket.getData(), Long.toString(getNumber()));
                                    }
                                });
                                nextbut.add(next);
                                JPanel goldseats = new JPanel();
                                goldseats.setLayout(new FlowLayout());
                                goldseats.setBackground(Color.CYAN);
                                JPanel vipseats = new JPanel();
                                vipseats.setLayout(new FlowLayout());
                                vipseats.setBackground(Color.GRAY);
                                JPanel lonseats = new JPanel();
                                lonseats.setLayout(new FlowLayout());
                                lonseats.setBackground(Color.GREEN);
                                f_selectseat.add(vipseats);
                                f_selectseat.add(goldseats);
                                f_selectseat.add(lonseats);
                                f_selectseat.add(nextbut);
                                
                                Show currShow = currUserTicket.Theatre.Shows_corres.get(k);
                                for(int i=0; i<currShow.Seats.size(); i++){
                                    Seat currSeat = currShow.Seats.get(i);
                                    currSeat.setName(Integer.toString(i));
                                    JButton seat = new JButton(currSeat.getName());
                                    switch (k) {
                                        case 0:
                                            for(int j=0; j<currUserTicket.Theatre.show1_occ.size(); j++){
                                                if(currUserTicket.Theatre.show1_occ.get(j).equals(currSeat.getName())){
                                                    currSeat.isReserved=true;
                                                    seat.setBackground(Color.RED);
                                                }
                                            }
                                            break;
                                        case 1:
                                            for(int j=0; j<currUserTicket.Theatre.show2_occ.size(); j++){
                                                if(currUserTicket.Theatre.show2_occ.get(j).equals(currSeat.getName())){
                                                    currSeat.isReserved=true;
                                                    seat.setBackground(Color.RED);
                                                }
                                            }
                                            break;
                                        case 2:
                                            for(int j=0; j<currUserTicket.Theatre.show3_occ.size(); j++){
                                                if(currUserTicket.Theatre.show2_occ.get(j).equals(currSeat.getName())){
                                                    currSeat.isReserved=true;
                                                    seat.setBackground(Color.RED);
                                                }
                                            }
                                            break;
                                        default:
                                            break;
                                    }
                                    JButton useless_me = new JButton("i'm useless");
                                    seat.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e){
                                            if(currSeat.isReserved){
                                                if(seat.getBackground().equals(Color.YELLOW)){
                                                    currUserTicket.seats.remove(currSeat);
                                                    seat.setBackground(useless_me.getBackground());
                                                    currSeat.isReserved=false;
                                                }
                                            }else{
                                                currSeat.isReserved=true;
                                                seat.setBackground(Color.YELLOW);
                                                currUserTicket.seats.add(currSeat);
                                            }
                                        }
                                    });;
                                    if(currShow.Seats.get(i).isReserved){
                                        seat.setBackground(Color.RED);
                                    }
                                    if(currShow.Seats.get(i).getType().equals("GOLD")){
                                        goldseats.add(seat);
                                    }
                                    else  if (currShow.Seats.get(i).getType().equals("VIP")) {
                                        vipseats.add(seat);
                                    }
                                    else{
                                        lonseats.add(seat);
                                    }
                                }

                            }
                       });
                    }
                });
            }
        });
    }
    
    public void viewMovies(){
        JFrame viewMovies = new JFrame();
        JMenuBar mb = new JMenuBar();
        JMenuItem back = new JMenuItem("\t\t\tBack");
        mb.add(back);
        viewMovies.setJMenuBar(mb);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                viewMovies.dispose();
            }
        });
        viewMovies.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);viewMovies.setSize(2000,500);viewMovies.setVisible(true);viewMovies.setLayout(new GridLayout());
        for(int i=0; i<Main.movies.size(); i++){
            JButton btn = new JButton(Main.movies.get(i).Name);
            viewMovies.add(btn);
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame movie = new JFrame();
                    movie.setTitle("DETAILS");
                    JMenuBar mb = new JMenuBar();
                    JMenuItem back = new JMenuItem("Back");
                    mb.add(back);
                    movie.setJMenuBar(mb);
                    back.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e){
                            movie.dispose();
                        }
                    });
                    Movie currmovie = new Movie();
                    for(int i=0; i<Main.movies.size(); i++){
                        if(Main.movies.get(i).Name.equals(btn.getText()))
                            currmovie = Main.movies.get(i);
                    }
                    JTextField details = new JTextField(currmovie.showData());
                    details.setHorizontalAlignment(JTextField.CENTER);
                    details.setEditable(false);
                    movie.add(details);
                    movie.setVisible(true);movie.setLayout(new GridLayout());movie.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    movie.setSize(500,500);
                }
            });
        }

    }
    public void viewCities(){
        JFrame viewCities = new JFrame();
        JMenuBar mb = new JMenuBar();
        JMenuItem back = new JMenuItem("\t\t\tBack");
        mb.add(back);
        viewCities.setJMenuBar(mb);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                viewCities.dispose();
            }
        });
        viewCities.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        viewCities.setLayout(new GridLayout());
        viewCities.setSize(2000,500);
        viewCities.setVisible(true);
        for(int i=0; i<Main.cities.size(); i++){
            JButton cityField = new JButton(Main.cities.get(i).getName());
            viewCities.add(cityField);
            cityField.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    JFrame theatres = new JFrame();
                    JMenuBar mb = new JMenuBar();
                    JMenuItem back = new JMenuItem("\t\t\tBack");
                    mb.add(back);
                    theatres.setJMenuBar(mb);
                    back.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e){
                            theatres.dispose();
                        }
                    });

                    theatres.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    theatres.setLayout(new FlowLayout());
                    theatres.setSize(500,500);
                    theatres.setVisible(true);
                    cityField.getText();
                    City city = City.getCity(cityField.getText());
                    for(int j=0; j<city.Theatres_corres.size();j++){
                        JTextField  theatreField = new JTextField(city.Theatres_corres.get(j).getName());
                        theatreField.setHorizontalAlignment(JTextField.CENTER);
                        theatreField.setEditable(false);
                        theatres.add(theatreField);
                    }
                }
            });
        }
    }
    public void viewPrevTicket(){
        JFrame prevTickets = new JFrame("History");
        JMenuBar mb = new JMenuBar();
        JMenuItem back = new JMenuItem("\t\t\tBack");
        mb.add(back);
        prevTickets.setJMenuBar(mb);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                prevTickets.dispose();
            }
        });
        prevTickets.setVisible(true);prevTickets.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        prevTickets.setSize(1000,500);prevTickets.setLayout(new GridLayout(BookingsCurrUser.size(), 0));
        for(int i=0; i<BookingsCurrUser.size(); i++){
            JTextField ticketField = new JTextField(BookingsCurrUser.get(i).getData());
            ticketField.setHorizontalAlignment(JTextField.CENTER);
            ticketField.setEditable(false);
            prevTickets.add(ticketField);
        }
    }
}