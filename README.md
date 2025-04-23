Project Title:
#Movie Ticket Booking System - Java Swing

-->Description:
This is a Java-based GUI application that allows users to book movie tickets and admins to manage theatres, movies, and cities. The system includes both frontend and backend components, designed using Java Swing and standard Java collections with CSV-based data persistence.

-->Technologies Used:
- Language: Java
- GUI Framework: Java Swing
- Data Storage: CSV files (loaded via Database class)
- Utilities: Java Collections (Vector, etc.)

-->Frontend Features (GUI using Swing):
Welcome screen with animated intro
Sign-In and Sign-Up pages for Users and Admins

1)User Dashboard:
-Book tickets based on selected city, theatre, and movie
-View list of available movies and cities
-View previously booked tickets

2)Admin Dashboard:
- Add new cities and theatres
- View user bookings and remaining seats
- Profile view for both User and Admin roles
- Seat availability feedback (booked vs available)
- Simple navigation using buttons and menus

-->Backend Features:
Role-based authentication system (User/Admin)

- Persistent data management using CSV files:
users.csv, movies.csv, cities.csv, theatres.csv, etc.
Booking system that updates seat availability

- Object-oriented model design:
Classes for User, Admin, City, Theatre, Movie, Show, Seat, and Booking
Central Database class for loading/storing data
Seat reservation logic to avoid double-booking
Vector-based in-memory data structures for performance and simplicity

-->How to Run:
Step 1: Ensure JDK (Java 8 or above) is installed.
Step 2 : Compile the code:
         using command-
         javac Main.java
Step 3 : Run the program:
         java Main


-->Planned Improvements:
1)Add seat layout GUI with real-time selection
2)Migrate from CSV to a database (e.g., MySQL or SQLite)
3)Search/filter movies by language or genre
4)Improved validation and error messages
5)Add show timings and seat types

-->Authors:
Vishnu Vardhan(CS24B004)&
Sreeja Raj(CS24B030).

