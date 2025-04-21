import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
public class Admin extends Account{

    public static Admin getAdmin(String username){
        for(int i=0; i<Main.admins.size(); i++){
            if(Main.admins.get(i).getUsername().equals(username))
                return Main.admins.get(i);
        }
        return null;
    }
    public String showData(){
        return "Username: "+ getUsername() + "\n\n" + "Number: " + getNumber();
    }
    public static void addTheatre() {
        JFrame addTheater = new JFrame();
        JMenuBar mb = new JMenuBar();
        JMenuItem back = new JMenuItem("\t\t\tBack");
        mb.add(back);
        addTheater.setJMenuBar(mb);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                addTheater.dispose();
            }
        });
        addTheater.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addTheater.setVisible(true);
        addTheater.setSize(500,500);
        addTheater.setLayout(null);
        JLabel theatreprompt = new JLabel("Enter Theatre name: ");
        JLabel cityprompt = new JLabel("Enter city name: ");
        JTextField theatreinput = new JTextField();
        String[] cityName = new String[Main.cities.size()];
        for(int i=0; i<cityName.length; i++){
            cityName[i] = Main.cities.get(i).getName();
        }
        JComboBox<String> cityinput = new JComboBox<>(cityName);
        JButton next = new JButton("add");
        theatreprompt.setBounds(100,100,100,50);
        theatreinput.setBounds(250,100,100,50);
        cityprompt.setBounds(100,200,100,50);
        cityinput.setBounds(250,200,100,50);
        next.setBounds(175,300,100,50);
        addTheater.add(theatreprompt);
        addTheater.add(cityprompt);
        addTheater.add(theatreinput);
        addTheater.add(cityinput);
        addTheater.add(next);
        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                addTheater.dispose();
                JFrame theatredata = new JFrame();theatredata.setSize(500,500);
                theatredata.setLayout(new GridLayout(5,2)); theatredata.setVisible(true);theatredata.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                JLabel movie1 = new JLabel("Movie1: ");
                JLabel movie2 = new JLabel("Movie2: ");
                JLabel movie3 = new JLabel("Movie3: ");
                JLabel seatdata = new JLabel("Seats: ");
                JTextField movie1inp = new JTextField();
                JTextField movie2inp = new JTextField();
                JTextField movie3inp = new JTextField();
                JTextField seatinp = new JTextField();
                theatredata.add(movie1);
                theatredata.add(movie1inp);
                theatredata.add(movie2);
                theatredata.add(movie2inp);
                theatredata.add(movie3);
                theatredata.add(movie3inp);
                theatredata.add(seatdata);
                theatredata.add(seatinp);
                JButton next = new JButton("next");
                theatredata.add(next);
                next.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        theatredata.dispose();
                        Database.writeTheatretoCSV(theatreinput.getText(), cityinput.getSelectedItem().toString(),movie1inp.getText(), movie2inp.getText(), movie3inp.getText(), seatinp.getText());
                    }
                });
                }
        });
    }
    public static void addCity(){
        JFrame addCity = new JFrame();
        JMenuBar mb = new JMenuBar();
        JMenuItem back = new JMenuItem("\t\t\tBack");
        mb.add(back);
        addCity.setJMenuBar(mb);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                addCity.dispose();
            }
        });
        addCity.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addCity.setVisible(true);
        addCity.setLayout(null);
        addCity.setSize(500,500);
        JLabel cityprompt = new JLabel("Enter city");
        JButton next = new JButton("next->");
        JTextField cityinput = new JTextField();
        cityprompt.setBounds(100,100,100,50);
        cityinput.setBounds(250,100,100,50);
        next.setBounds(175,200,100,50);
        addCity.add(cityprompt);
        addCity.add(cityinput);
        addCity.add(next);
        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                Database.writeCitytoCSV(cityinput.getText());
            }
        });
    }

    public static void addMovie() {
        JFrame addMovie = new JFrame();
        JMenuBar mb = new JMenuBar();
        JMenuItem back = new JMenuItem("\t\t\tBack");
        mb.add(back);
        addMovie.setJMenuBar(mb);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                addMovie.dispose();
            }
        });
        addMovie.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addMovie.setVisible(true);
        addMovie.setLayout(null);
        addMovie.setSize(500, 500);
        JLabel theatreprompt = new JLabel("Enter theatre: ");
        JLabel movieprompt = new JLabel("Enter Movie name: ");
        JTextField theatreinput = new JTextField();
        JTextField  movieinput = new JTextField();
        JButton next = new JButton("add");
        theatreprompt.setBounds(100,100,100,50);
        theatreinput.setBounds(250,100,100,50);
        movieprompt.setBounds(100,200,100,50);
        movieinput.setBounds(250,200,100,50);
        next.setBounds(175,300,100,50);
        addMovie.add(theatreprompt);
        addMovie.add(theatreinput);
        addMovie.add(movieinput);
        addMovie.add(movieprompt);
        addMovie.add(next);
        next.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                Database.writeMovietoCSV(theatreinput.getText(), movieinput.getText(),null,null);
            }
        });
    }   

    public static void viewSeatsLeft() {
        JFrame bookings = new JFrame();
        JMenuBar mb = new JMenuBar();
        JMenuItem back = new JMenuItem("\t\t\tBack");
        mb.add(back);
        bookings.setJMenuBar(mb);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                bookings.dispose();
            }
        });
        bookings.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        bookings.setSize(500,500);bookings.setVisible(true);bookings.setLayout(new FlowLayout(FlowLayout.CENTER));
        String[] cityName = new String[Main.cities.size()];
        for(int i=0; i<cityName.length; i++){
            cityName[i] = Main.cities.get(i).getName();
        }
        JComboBox<String> cityinput = new JComboBox<>(cityName);
        bookings.add(cityinput);
        cityinput.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String[] theatreName = new String[City.getCity(cityinput.getSelectedItem().toString()).Theatres_corres.size()];
                for(int i=0; i<theatreName.length; i++){
                    theatreName[i] = City.getCity(cityinput.getSelectedItem().toString()).Theatres_corres.get(i).getName();
                }
                JComboBox<String> theatreinput = new JComboBox<>(theatreName);
                cityinput.setVisible(false);
                bookings.add(theatreinput);
                theatreinput.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        String[] movieName = new String[3];
                        Theatre theatre = Theatre.getTheatre(theatreinput.getSelectedItem().toString());
                        for(int i=0; i<3; i++){
                            movieName[i] = theatre.Movies_corres.get(i).Name;
                        }
                        JComboBox<String> movieinput = new JComboBox<>(movieName);
                        bookings.add(movieinput);
                        theatreinput.setVisible(false);
                        movieinput.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e){
                                JButton next = new JButton("next");
                                bookings.add(next);
                                next.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e){  
                                        JFrame returnseatleft = new JFrame();
                                        int seats_left =0;
                                        if(Movie.getMovie(movieinput.getSelectedItem().toString()).Name.equals(theatre.Movies_corres.get(0).Name)){
                                            seats_left = theatre.Shows_corres.get(0).Seats.size() - theatre.show1_occ.size();
                                        }else if(Movie.getMovie(movieinput.getSelectedItem().toString()).Name.equals(theatre.Movies_corres.get(1).Name)){
                                            seats_left = theatre.Shows_corres.get(1).Seats.size() - theatre.show2_occ.size();
                                        }else{
                                            seats_left = theatre.Shows_corres.get(2).Seats.size() - theatre.show3_occ.size();
                                        }
                                        JOptionPane.showMessageDialog(returnseatleft, "Seats left: " + seats_left);
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });
        
        
    }

    public static void viewBookings() {
        JFrame viewBookings = new JFrame();
        JMenuBar mb = new JMenuBar();
        JMenuItem back = new JMenuItem("\t\t\tBack");
        mb.add(back);
        viewBookings.setJMenuBar(mb);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewBookings.dispose();
            }
        });
        viewBookings.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        viewBookings.setVisible(true);
        viewBookings.setSize(500, 500);
        viewBookings.setLayout(new GridLayout(Main.bookings.size(),0));
        for (int i = 0; i < Main.bookings.size(); i++) {
            JTextField ticketField = new JTextField(Main.bookings.get(i).getData());
            ticketField.setHorizontalAlignment(JTextField.CENTER);
            ticketField.setEditable(false);
            viewBookings.add(ticketField);
        }
    }
}
