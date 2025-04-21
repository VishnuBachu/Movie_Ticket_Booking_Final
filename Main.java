import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class Main{
    public static Vector<User> users = new Vector<User>();
    public static Vector<Admin> admins = new Vector<Admin>();
    public static Vector<City> cities = new Vector<City>();
    public static Vector<Theatre> theatres = new Vector<Theatre>();
    public static Vector<Movie> movies = new Vector<Movie>();
    public static Vector<Booking> bookings = new Vector<Booking>();
    public static void main(String[] args) {
        Database.loadAccountsFromCSV();
        Database.loadCitiesFromCSV();
        Database.loadMoviesfromCSV();
        Database.loadTheatresfromCSV();
        Database.loadTicketDetails();
        ShowIntro();
        LoginPage();
    }

    public static void ShowIntro(){
        JFrame Intro = new JFrame("INTRODUCTION");
        Intro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Intro.setSize(1000,800);
        Intro.setVisible(true);
        Intro.setLayout(new BorderLayout());
        JLabel heading = new JLabel("BOOK-YOUR-TICKETS");
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setFont(new Font("Times New Roman", Font.BOLD, 60));
        Intro.add(heading, BorderLayout.NORTH);
        JLabel subheading = new JLabel("CS24B030");
        subheading.setFont(new Font("Times New Roman", Font.ITALIC, 40));
        subheading.setHorizontalAlignment(SwingConstants.CENTER);
        Intro.add(subheading,BorderLayout.SOUTH);
        ImageIcon icon = new ImageIcon("OIG4.jpg");
        JLabel image = new JLabel(icon);
        Intro.add(image, BorderLayout.CENTER);
        Intro.pack();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Intro.dispose();
    }
    
    public static void LoginPage(){
        JFrame loginpage = new JFrame();
        System.out.println("login page created");
        loginpage.setSize(1000,500);
        loginpage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginpage.setVisible(true);
        loginpage.setLayout(null);
        ImageIcon icon = new ImageIcon("OIG4.jpg");
        loginpage.setTitle("MovieBookingJAVA");
        loginpage.setIconImage(icon.getImage());
        JButton sin = new JButton("Sign-in");
        JButton sup = new JButton("Sign-up");
        sin.setBounds(350, 200, 200, 50);
        sup.setBounds(350, 275, 200, 50);
        loginpage.add(sin);
        loginpage.add(sup);
        sin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                loginpage.dispose();
                Signinpage();
            }
        });
        sup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                loginpage.dispose();
                Signuppage();
            }
        });
    }

    public static void Signinpage(){
        JFrame signinpage = new JFrame();
        signinpage.setTitle("SIGN-IN");
        JMenuBar mb = new JMenuBar();
        JMenuItem back = new JMenuItem("\t\t\tBack");
        mb.add(back);
        signinpage.setJMenuBar(mb);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                signinpage.dispose();
                LoginPage();
            }
        });
        signinpage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        signinpage.setLayout(null);
        signinpage.setVisible(true);
        JLabel l1 = new JLabel("Enter Username: ");
        JLabel l2 = new JLabel("Enter Password: ");
        JTextField ta1 = new JTextField();
        JPasswordField pwd = new JPasswordField();
        l1.setBounds(100, 100, 120, 50);
        ta1.setBounds(250, 120, 100, 20);
        l2.setBounds(100, 200, 120, 50);
        pwd.setBounds(250, 220, 100, 20);
        signinpage.add(l1);
        signinpage.add(ta1);
        signinpage.add(l2);
        signinpage.add(pwd);
        JButton login = new JButton("LOGIN");
        login.setBounds(180, 400, 100, 50);
        signinpage.add(login);
        signinpage.setSize(500, 500);
        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.println("LOGIN BUTTON PRESSED");
                String username = ta1.getText();
                String password = new String(pwd.getPassword());
                if (Database.signin(username, password, true)) {
                    System.out.print("User signed in");
                    signinpage.dispose();
                    JFrame userpage = new JFrame();
                    JMenuBar mb = new JMenuBar();
                    JMenu Profile = new JMenu("Profile");
                    JMenuItem Details = new JMenuItem("Details");
                    JMenuItem logout = new JMenuItem("Logout");
                    Profile.add(logout);
                    Profile.add(Details);
                    mb.add(Profile);
                    userpage.setJMenuBar(mb);
                    Details.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e){
                            JFrame Details= new JFrame();
                            JOptionPane.showMessageDialog(Details,User.getUser(username).showData(),"User",JOptionPane.PLAIN_MESSAGE);
                        }
                    });
                    logout.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e){
                            userpage.dispose();
                            LoginPage();
                        }
                    });
                    userpage.setVisible(true);userpage.setLayout(new GridLayout(4, 0));
                    userpage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    userpage.setSize(500,500);
                    JButton bookTicket = new JButton("Book Ticket->");
                    JButton viewMovies = new JButton("View Movies->");
                    JButton viewCities = new JButton("View Cities->");
                    JButton viewPrevTicket = new JButton("View Prev Tkt->");
                    userpage.add(bookTicket);
                    userpage.add(viewCities);
                    userpage.add(viewMovies);
                    userpage.add(viewPrevTicket);
                    bookTicket.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e){
                            User current_user = new User();
                            for (int i = 0; i < users.size(); i++)
                                if (users.get(i).getUsername().equals(username))
                                    current_user = users.get(i);
                            current_user.bookTicket();
                        }
                    });
                    viewCities.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e){
                            User current_user = new User();
                            for (int i = 0; i < users.size(); i++)
                                if (users.get(i).getUsername().equals(username))
                                    current_user = users.get(i);
                            current_user.viewCities();
                            }
                    });
                    viewMovies.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e){
                            User current_user = new User();
                            for (int i = 0; i < users.size(); i++)
                                if (users.get(i).getUsername().equals(username))
                                    current_user = users.get(i);
                            current_user.viewMovies();
                            }
                    });
                    viewPrevTicket.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e){
                            User current_user = new User();
                            for (int i = 0; i < users.size(); i++)
                                if (users.get(i).getUsername().equals(username))
                                    current_user = users.get(i);
                            current_user.viewPrevTicket();
                            }
                    });
                }else if(Database.signin(username, password, false)){
                    System.out.println("Admin has signed in");
                    JFrame AdminFeatures = new JFrame();
                    signinpage.dispose();
                    AdminFeatures.setVisible(true);AdminFeatures.setLayout(new GridLayout(4,0));
                    AdminFeatures.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);AdminFeatures.setSize(500,500);;
                    JButton addTheatre = new JButton("Add Theatre");
                    AdminFeatures.add(addTheatre);
                    JButton viewSeatsLeft = new JButton("View Seats Left");
                    AdminFeatures.add(viewSeatsLeft);
                    JButton addCity = new JButton("Add City");
                    AdminFeatures.add(addCity);
                    JButton viewBookings = new JButton("User Bookings");
                    viewBookings.setBounds(250,400,100,50);
                    AdminFeatures.add(viewBookings);
                    JMenuBar mb = new JMenuBar();
                    JMenu Profile = new JMenu("Profile");
                    JMenuItem Details = new JMenuItem("Details");
                    JMenuItem logout = new JMenuItem("Logout");
                    Profile.add(logout);
                    Profile.add(Details);
                    mb.add(Profile);
                    AdminFeatures.setJMenuBar(mb);
                    Details.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e){
                            JFrame Details= new JFrame();
                            JOptionPane.showMessageDialog(Details,Admin.getAdmin(username).showData(),"Admin",JOptionPane.PLAIN_MESSAGE);
                        }
                    });
                    logout.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e){
                            AdminFeatures.dispose();
                            LoginPage();
                        }
                    });
                    viewBookings.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e){
                            Admin.viewBookings();
                        }
                    });
                    addCity.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e){
                            Admin.addCity();
                        }
                    });
                    addTheatre.addActionListener(new ActionListener() {
                        public void actionPerformed (ActionEvent e){
                            Admin.addTheatre();
                        }
                    });
                    viewSeatsLeft.addActionListener(new ActionListener() {
                        public void actionPerformed (ActionEvent e){
                            Admin.viewSeatsLeft();;
                        }
                    });
                }else{
                    System.out.println("Error signining in");
                    ta1.setText("");
                    pwd.setText("");
                    JFrame error = new JFrame();
                    JOptionPane.showMessageDialog(error,"Wrong username or password\n\tTry Again","re-try",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    
    public static void Signuppage(){
        JFrame signuppage = new JFrame();
        signuppage.setTitle("SIGN_UP");
        JMenuBar mb = new JMenuBar();
        JMenuItem back = new JMenuItem("\t\t\tBack");
        mb.add(back);
        signuppage.setJMenuBar(mb);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                signuppage.dispose();
                LoginPage();
            }
        });
        signuppage.setSize(500,500);
        signuppage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        signuppage.setVisible(true);
        signuppage.setLayout(null);
        JLabel usernameprompt = new JLabel("Enter Username: ");
        JLabel passwordprompt = new JLabel("Enter Password: ");
        JLabel numberprompt = new JLabel("Enter Ph.no");
        signuppage.add(numberprompt);
        numberprompt.setBounds(100, 10, 120, 50);
        JTextField usernameinput = new JTextField();
        JTextField numberinput = new JTextField();
        signuppage.add(numberinput);
        numberinput.setBounds(250,25, 100, 20);
        JPasswordField pwd = new JPasswordField();
        usernameprompt.setBounds(100, 100, 120, 50);
        usernameinput.setBounds(250, 120, 100, 20);
        passwordprompt.setBounds(100, 200, 120, 50);
        pwd.setBounds(250, 220, 100, 20);
        signuppage.add(usernameprompt);
        signuppage.add(usernameinput);
        signuppage.add(passwordprompt);
        signuppage.add(pwd);
        JButton signup = new JButton("Sign-up");
        signuppage.add(signup);
        JRadioButton usercheck = new JRadioButton("USER");
        JRadioButton admincheck = new JRadioButton("ADMIN");
        signup.setBounds(180, 400, 100, 50);
        usercheck.setBounds(180, 340, 100, 50);
        admincheck.setBounds(280, 340, 100, 50);
        signuppage.add(usercheck);
        signuppage.add(admincheck);
        signup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(numberinput.getText().length()!=10){
                    JFrame phnum = new JFrame();
                    JOptionPane.showMessageDialog(phnum,"Phone no. should be of 10 digits","Error",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(admincheck.isSelected() && usercheck.isSelected()){
                    JFrame selectone = new JFrame();
                    JOptionPane.showMessageDialog(selectone,"Select either User/Admin","Error",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (admincheck.isSelected()) {
                    String password = new String(pwd.getPassword());
                    Database.signup(usernameinput.getText(), password, false, Long.parseLong(numberinput.getText()));
                    signuppage.dispose();
                    LoginPage();
                } else {
                    String password = new String(pwd.getPassword());
                    Database.signup(usernameinput.getText(), password, true, Long.parseLong(numberinput.getText()));
                    signuppage.dispose();
                    LoginPage();
                }
            }
        });
    }
}

class Show {
    private long ShowTime;

    public long getShowTime() {
        return ShowTime;
    }

    public void setShowTime(long showTime) {
        ShowTime = showTime;
    }

    public Vector<Seat> Seats=new Vector<>();
}

class Seat {
    private String Name;
    public boolean isReserved;
    private String type;

    public String getName(){
        return Name;
    }
    public void setName(String Name){
        this.Name = Name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

class City {
    private String Name;

    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }

    public Vector<Theatre> Theatres_corres = new Vector<>();
    
    public static City getCity(String Name){
        for(int i=0; i<Main.cities.size();i++){
            if(Main.cities.get(i).Name.equals(Name))
                return Main.cities.get(i);
        }
        return null;
    }
}

class Theatre {
    private String Name;
    public Vector<String> show1_occ = new Vector<>();
    public Vector<String> show2_occ = new Vector<>();
    public Vector<String> show3_occ = new Vector<>();
    public Vector<Movie> Movies_corres = new Vector<>();
    public Vector<Show> Shows_corres = new Vector<>();
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
    public static Theatre getTheatre(String name){
        for(int i=0; i<Main.theatres.size();i++){
            if(name.equals(Main.theatres.get(i).Name)){
                return Main.theatres.get(i);
            }
        }
        return null;
    }
}
