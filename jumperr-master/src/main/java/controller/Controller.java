package controller;

import model.*;
import storage.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.threeten.bp.LocalTime;

import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

//////////////////////////////////////////////////
//various imports
//////////////////////////////////////////////////
//// import com.google.auth.Credentials;
import com.google.cloud.ReadChannel;
import com.google.cloud.WriteChannel;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Controller {

    public static ArrayList<String> getUsers_GoogleCloudMySQL() {

        ArrayList<String> rows = new ArrayList<>();

        try {
            DatabaseConnection dbConnection = new DatabaseConnection("jumperr_database", "sql-user", "hejmeddig001",
                    "jumperr:europe-west1:jumperr-sql");

            try (Connection con = dbConnection.getConnection()) {
                PreparedStatement stmt = con.prepareStatement("SELECT * FROM User");
                ResultSet rs = stmt.executeQuery();

                // ResultSetMetaData metadata = rs.getMetaData();
                // int columnCount = metadata.getColumnCount();

                while (rs.next()) {

                    String row = "";

                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    String address = rs.getString("address");
                    int telephoneNumber = rs.getInt("telephoneNumber");
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String profilePicture = rs.getString("profilePicture");
                    int admin = rs.getInt("admin");

                    row = id + ";" + name + ";" + email + ";" + address + ";" + telephoneNumber + ";" + username + ";"
                            + password + ";" + profilePicture + ";" + admin + ";";

                    rows.add(row);
                }
                if (!con.isClosed()) {
                    con.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("DatabaseConnection EXCEPTION:" + e.toString());
        }
        return rows;

    }

    public static User authenticateUser_GoogleCloudMySQL(String username, String password) {
        User user = null;

        ArrayList<String> rows = Controller.getUsers_GoogleCloudMySQL();
        for (String rowString : rows) {

            String[] row = rowString.split(";");

            if (row[5].equals(username) && row[6].equals(password)) { // index 5 er username & index 6 er password
                user = new User(Integer.parseInt(row[0]), row[1], row[2], row[3], Integer.parseInt(row[4]), row[5],
                        row[6], Integer.parseInt(row[8]));
                break;
            }
        }
        return user;
    }

    public static User getUserById_GoogleCloudMySQL(String userId) {
        User user = null;

        ArrayList<String> rows = Controller.getUsers_GoogleCloudMySQL();
        for (String rowString : rows) {

            String[] row = rowString.split(";");

            if (row[0].equals(userId)) { // index 0 er id
                user = new User(Integer.parseInt(row[0]), row[1], row[2], row[3], Integer.parseInt(row[4]), row[5],
                        row[6], Integer.parseInt(row[8]));
                break;
            }
        }
        return user;
    }

    public static boolean checkIfUserExists_GoogleCloudMySQL(String username) {
        boolean found = false;

        ArrayList<String> rows = Controller.getUsers_GoogleCloudMySQL();
        for (String rowString : rows) {

            String[] row = rowString.split(";");

            if (row[5].equals(username)) { // index 5 er username
                found = true;
                break;
            }
        }

        return found;
    }

    public static void createUser_GoogleCloudMySQL(String name, String email, String address, int telephoneNumber,
            String username, String password, int admin) {

        try {
            DatabaseConnection dbConnection = new DatabaseConnection("jumperr_database", "sql-user", "hejmeddig001",
                    "jumperr:europe-west1:jumperr-sql");

            try (Connection con = dbConnection.getConnection()) {
                PreparedStatement stmt = con.prepareStatement(
                        "INSERT INTO User(name, email, address, telephoneNumber, username, password, profilePicture, admin) VALUES(?,?,?,?,?,?,?,?)");

                stmt.clearParameters();
                stmt.setString(1, name);
                stmt.setString(2, email);
                stmt.setString(3, address);
                stmt.setInt(4, telephoneNumber);
                stmt.setString(5, username);
                stmt.setString(6, password);
                stmt.setString(7, "avatar.png");
                stmt.setInt(8, admin);

                stmt.execute();

                if (!con.isClosed()) {
                    con.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("DatabaseConnection EXCEPTION:" + e.toString());
        }
    }

    public static void updateUser_GoogleCloudMySQL(String userId, String name, String email, String address,
            int telephoneNumber, String username, String password, String profilePicture) {

        try {
            DatabaseConnection dbConnection = new DatabaseConnection("jumperr_database", "sql-user", "hejmeddig001",
                    "jumperr:europe-west1:jumperr-sql");

            try (Connection con = dbConnection.getConnection()) {
                PreparedStatement stmt = con.prepareStatement(
                        "UPDATE User SET name = ?, email = ?, address = ?, telephoneNumber = ?, username = ?, password = ?, profilePicture = ? where id > ?");

                stmt.clearParameters();
                stmt.setString(1, name);
                stmt.setString(2, email);
                stmt.setString(3, address);
                stmt.setInt(4, telephoneNumber);
                stmt.setString(5, username);
                stmt.setString(6, password);
                stmt.setString(7, profilePicture);
                stmt.setInt(8, Integer.parseInt(userId));

                stmt.execute();

                if (!con.isClosed()) {
                    con.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("DatabaseConnection EXCEPTION:" + e.toString());
        }
    }

    public static void deleteUser_GoogleCloudMySQL(String userId) {

        try {
            DatabaseConnection dbConnection = new DatabaseConnection("jumperr_database", "sql-user", "hejmeddig001",
                    "jumperr:europe-west1:jumperr-sql");

            try (Connection con = dbConnection.getConnection()) {
                PreparedStatement stmt = con.prepareStatement("DELETE FROM User WHERE id > ?"); //FEJL

                stmt.clearParameters();
                stmt.setInt(1, Integer.parseInt(userId));

                stmt.execute();

                if (!con.isClosed()) {
                    con.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("DatabaseConnection EXCEPTION:" + e.toString());
        }
    }

    public static ArrayList<String> getTrips_GoogleCloudMySQL() {

        ArrayList<String> rows = new ArrayList<>();

        try {
            DatabaseConnection dbConnection = new DatabaseConnection("jumperr_database", "sql-user", "hejmeddig001",
                    "jumperr:europe-west1:jumperr-sql");

            try (Connection con = dbConnection.getConnection()) {
                PreparedStatement stmt = con.prepareStatement("SELECT * FROM Trip");
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {

                    String row = "";

                    int id = rs.getInt("id");
                    String date = rs.getString("date");
                    String timeOfDeparture = rs.getString("timeOfDeparture");
                    String timeOfArrival = rs.getString("timeOfArrival");
                    String departureAddress = rs.getString("departureAddress");
                    String arrivalAddress = rs.getString("arrivalAddress");
                    int driver = rs.getInt("driver");
                    int pricePerKm = rs.getInt("pricePerKm");
                    int availableSeats = rs.getInt("availableSeats");

                    row = id + ";" + date + ";" + timeOfDeparture + ";" + timeOfArrival + ";" + departureAddress + ";"
                            + arrivalAddress + ";" + driver + ";" + pricePerKm + ";" + availableSeats + ";";

                    rows.add(row);
                }
                if (!con.isClosed()) {
                    con.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("DatabaseConnection EXCEPTION:" + e.toString());
        }
        return rows;
    }

    public static Trip getTripById_GoogleCloudMySQL(String tripId) {
        Trip trip = null;

        ArrayList<String> rows = Controller.getTrips_GoogleCloudMySQL();
        for (String rowString : rows) {

            String[] row = rowString.split(";");
            User driver = Controller.getUserById_GoogleCloudMySQL(row[6]); // row[6] er userId på driver

            if (row[0].equals(tripId)) { // index 0 er id
                trip = new Trip(Integer.parseInt(row[0]), row[1], row[2], row[3], row[4], row[5], driver,
                        Integer.parseInt(row[7]), Integer.parseInt(row[8]));
                break;
            }
        }
        return trip;
    }

    public static void createTrip_GoogleCloudMySQL(String date, String timeOfDeparture, String timeOfArrival,
            String departureAddress, String arrivalAddress, User driver, int pricePerKm, int availableSeats) {

        try {
            DatabaseConnection dbConnection = new DatabaseConnection("jumperr_database", "sql-user", "hejmeddig001",
                    "jumperr:europe-west1:jumperr-sql");

            try (Connection con = dbConnection.getConnection()) {
                PreparedStatement stmt = con.prepareStatement(
                        "INSERT INTO Trip(date, timeOfDeparture, timeOfArrival, departureAddress, arrivalAddress, driver, pricePerKm, availableSeats) VALUES(?,?,?,?,?,?,?,?)");

                stmt.clearParameters();
                stmt.setString(1, date);
                stmt.setString(2, timeOfDeparture);
                stmt.setString(3, timeOfArrival);
                stmt.setString(4, departureAddress);
                stmt.setString(5, arrivalAddress);
                stmt.setInt(6, driver.getId());
                stmt.setInt(7, pricePerKm);
                stmt.setInt(8, availableSeats);

                stmt.execute();

                if (!con.isClosed()) {
                    con.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("DatabaseConnection EXCEPTION:" + e.toString());
        }
    }

    public static void updateTrip_GoogleCloudMySQL(String tripId, String date, String timeOfDeparture,
            String timeOfArrival, String departureAddress, String arrivalAddress, User driver, int availableSeats) {

        try {
            DatabaseConnection dbConnection = new DatabaseConnection("jumperr_database", "sql-user", "hejmeddig001",
                    "jumperr:europe-west1:jumperr-sql");

            try (Connection con = dbConnection.getConnection()) {
                PreparedStatement stmt = con.prepareStatement(
                        "UPDATE Trip SET date = ?, timeOfDeparture = ?, timeOfArrival = ?, departureAddress = ?, arrivalAddress = ?, driver = ?, availableSeats = ? where id > ?"); //FEJL

                stmt.clearParameters();
                stmt.setString(1, date);
                stmt.setString(2, timeOfDeparture);
                stmt.setString(3, timeOfArrival);
                stmt.setString(4, departureAddress);
                stmt.setString(5, arrivalAddress);
                stmt.setInt(6, driver.getId());
                stmt.setInt(7, availableSeats);
                stmt.setInt(8, Integer.parseInt(tripId));

                stmt.execute();

                if (!con.isClosed()) {
                    con.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("DatabaseConnection EXCEPTION:" + e.toString());
        }
    }

    public static void deleteTrip_GoogleCloudMySQL(String tripId) {

        try {
            DatabaseConnection dbConnection = new DatabaseConnection("jumperr_database", "sql-user", "hejmeddig001",
                    "jumperr:europe-west1:jumperr-sql");

            try (Connection con = dbConnection.getConnection()) {
                PreparedStatement stmt = con.prepareStatement("DELETE FROM Trip WHERE id > ?"); //FEJL

                stmt.clearParameters();
                stmt.setInt(1, Integer.parseInt(tripId));

                stmt.execute();

                if (!con.isClosed()) {
                    con.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("DatabaseConnection EXCEPTION:" + e.toString());
        }
    }

    public static ArrayList<Trip> searchForTrips_GoogleCloudMySQL(String date, String timeOfDeparture) {
        ArrayList<Trip> matches = new ArrayList<>(); // trips-objekter der matcher søgningen
        LocalDate inputLocalDate = LocalDate.parse(date); // dagens dato
        LocalTime inputLocalTime = LocalTime.parse(timeOfDeparture); // afgangstidspunkt

        ArrayList<String> rows = Controller.getTrips_GoogleCloudMySQL(); // alle trips hentet som strenge
        ArrayList<Trip> trips = new ArrayList<>(); // trips-objekter der allerede eksisterer i systemet

        for (String rowString : rows) {

            String[] row = rowString.split(";");
            User driver = Controller.getUserById(row[6]);

            Trip trip = new Trip(Integer.parseInt(row[0]), row[1], row[2], row[3], row[4], row[5], driver,
                    Integer.parseInt(row[7]), Integer.parseInt(row[8]));
            trips.add(trip);
        }

        for (Trip t : trips) {
            LocalDate tripLocalDate = LocalDate.parse(Controller.formatDateToLocalDateFormat(t.getDate()));
            LocalTime tripLocalTime = LocalTime.parse(t.getTimeOfDeparture());
            LocalTime tripArrivalLocalTime = LocalTime.parse(t.getTimeOfArrival());

            if (inputLocalDate.equals(tripLocalDate)) {
                if (inputLocalTime.isAfter(tripLocalTime) && inputLocalTime.isBefore(tripArrivalLocalTime)) {
                    matches.add(t);
                }
            }
        }

        return matches;
    }

    public static ArrayList<String> getPickuppoints_GoogleCloudMySQL() {

        ArrayList<String> rows = new ArrayList<>();

        try {
            DatabaseConnection dbConnection = new DatabaseConnection("jumperr_database", "sql-user", "hejmeddig001",
                    "jumperr:europe-west1:jumperr-sql");

            try (Connection con = dbConnection.getConnection()) {
                PreparedStatement stmt = con.prepareStatement("SELECT * FROM Pickuppoint");
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {

                    String row = "";

                    int id = rs.getInt("id");
                    int jumper = rs.getInt("jumper");
                    int tripId = rs.getInt("tripId");
                    String departureAddress = rs.getString("departureAddress");
                    String arrivalAddress = rs.getString("arrivalAddress");
                    double price = rs.getDouble("price");
                    double km = rs.getDouble("km");

                    row = id + ";" + jumper + ";" + tripId + ";" + departureAddress + ";" + arrivalAddress + ";" + price
                            + ";" + km + ";";

                    rows.add(row);
                }
                if (!con.isClosed()) {
                    con.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("DatabaseConnection EXCEPTION:" + e.toString());
        }
        return rows;

    }

    public static ArrayList<String> getPickUpPointsByTripId_GoogleCloudMySQL(String tripIdForPickUpPoints) {
        ArrayList<String> rows = new ArrayList<>();

        try {
            DatabaseConnection dbConnection = new DatabaseConnection("jumperr_database", "sql-user", "hejmeddig001",
                    "jumperr:europe-west1:jumperr-sql");

            try (Connection con = dbConnection.getConnection()) {
                PreparedStatement stmt = con.prepareStatement(
                        "SELECT * FROM Pickuppoint JOIN Trip ON Pickuppoint.tripId = Trip.id where Pickuppoint.tripId = ?");

                stmt.setInt(1, Integer.parseInt(tripIdForPickUpPoints));

                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {

                    String row = "";

                    int id = rs.getInt("id");
                    int jumper = rs.getInt("jumper");
                    int tripId = rs.getInt("tripId");
                    String departureAddress = rs.getString("departureAddress");
                    String arrivalAddress = rs.getString("arrivalAddress");
                    double price = rs.getDouble("price");
                    double km = rs.getDouble("km");

                    row = id + ";" + jumper + ";" + tripId + ";" + departureAddress + ";" + arrivalAddress + ";" + price
                            + ";" + km + ";";

                    rows.add(row);
                }
                if (!con.isClosed()) {
                    con.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("DatabaseConnection EXCEPTION:" + e.toString());
        }
        return rows;
    }

    public static PickUpPoint getPickUpPointById_GoogleCloudMySQL(String pickuppointId) {
        PickUpPoint pickUpPoint = null;

        ArrayList<String> rows = Controller.getPickuppoints_GoogleCloudMySQL();
        for (String rowString : rows) {

            String[] row = rowString.split(";");
            User jumper = Controller.getUserById_GoogleCloudMySQL(row[0]); // row[0] er userId på jumper

            if (row[0].equals(pickuppointId)) { // index 0 er id
                pickUpPoint = new PickUpPoint(Integer.parseInt(row[0]), jumper, Integer.parseInt(row[2]), row[3],
                        row[4], Double.parseDouble(row[5]), Double.parseDouble(row[6]));
                break;
            }
        }
        return pickUpPoint;
    }

    public static void createPickUpPoint_GoogleCloudMySQL(User jumper, int tripId, String departureAddress,
            String arrivalAddress, double price, double km) {

        try {
            DatabaseConnection dbConnection = new DatabaseConnection("jumperr_database", "sql-user", "hejmeddig001",
                    "jumperr:europe-west1:jumperr-sql");

            try (Connection con = dbConnection.getConnection()) {
                PreparedStatement stmt = con.prepareStatement(
                        "INSERT INTO Pickuppoint(jumper, tripId, departureAddress, arrivalAddress, price, km) VALUES(?,?,?,?,?,?)");

                stmt.clearParameters();
                stmt.setInt(1, jumper.getId());
                stmt.setInt(2, tripId);
                stmt.setString(3, departureAddress);
                stmt.setString(4, arrivalAddress);
                stmt.setDouble(5, price);
                stmt.setDouble(6, km);

                stmt.execute();

                if (!con.isClosed()) {
                    con.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("DatabaseConnection EXCEPTION:" + e.toString());
        }
    }

    public static void deletePickUpPoint_GoogleCloudMySQL(String pickuppointId) {

        try {
            DatabaseConnection dbConnection = new DatabaseConnection("jumperr_database", "sql-user", "hejmeddig001",
                    "jumperr:europe-west1:jumperr-sql");

            try (Connection con = dbConnection.getConnection()) {
                PreparedStatement stmt = con.prepareStatement("DELETE FROM Pickuppoint WHERE id > ?"); //FEJL

                stmt.clearParameters();
                stmt.setInt(1, Integer.parseInt(pickuppointId));

                stmt.execute();

                if (!con.isClosed()) {
                    con.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("DatabaseConnection EXCEPTION:" + e.toString());
        }
    }

    // formaterer en dato format fra: 19-05-2019 til 2019-05-19 (så det kan bruges
    // som LocalDate)
    public static String formatDateToLocalDateFormat(String dateInput) {
        SimpleDateFormat fromUser = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateMyFormat = "";

        try {
            Date dateFromUser = fromUser.parse(dateInput);
            dateMyFormat = myFormat.format(dateFromUser);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateMyFormat;
    }

    public static ArrayList<PickUpPoint> getRecentPickuppointsByJumper(User jumper) {
        ArrayList<PickUpPoint> pickUpPoints = new ArrayList<>();

        for (Trip t : LocalStorage.getTrips()) {
            if (t.getPickUpPoints().size() != 0) {
                for (PickUpPoint p : t.getPickUpPoints()) {
                    if (p.getJumper().equals(jumper) && !pickUpPoints.contains(p)) {
                        pickUpPoints.add(p);
                    }
                }
            }
        }

        return pickUpPoints;
    }

    public static User authenticateUser(String username, String password) {
        User user = null;

        for (User u : LocalStorage.getUsers()) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                user = u;
                break;
            }
        }

        return user;
    }

    public static User getUserById(String userId) {

        User userObject = null;

        for (User user : LocalStorage.getUsers()) {
            if (user.getId() == Integer.parseInt((userId))) {
                userObject = user;
                break;
            }
        }
        return userObject;
    }

    public static void updateUser(String userId, String email, String name, String address, int telephoneNumber,
            String username, String password) {

        for (User user : LocalStorage.getUsers()) {
            if (user.getId() == Integer.parseInt((userId))) {
                user.setName(name);
                user.setEmail(email);
                user.setAddress(address);
                user.setTelephoneNumber(telephoneNumber);
                user.setUsername(username);
                user.setPassword(password);
                break;
            }
        }
    }

    public static void createUser(String name, String email, String address, int telephoneNumber, String username,
            String password) throws IOException {

        User user = new User(name, email, address, telephoneNumber, username, password);
        LocalStorage.addUser(user);

        updateUsersInGoogleStorage(LocalStorage.getUsers(), "User.txt");
    }

    public static void createUser(String name, String email, String address, int telephoneNumber, String username,
            String password, int admin) throws IOException {

        User user = new User(name, email, address, telephoneNumber, username, password);
        user.setAdmin(admin);
        LocalStorage.addUser(user);

        updateUsersInGoogleStorage(LocalStorage.getUsers(), "User.txt");
    }

    public static boolean checkIfUsernameExists(String username) {

        boolean found = false;

        for (User user : LocalStorage.getUsers()) {
            if (user.getUsername().equals(username)) {
                found = true;
                break;
            }
        }

        return found;
    }

    public static Trip getTripById(String tripId) {

        Trip tripObject = null;

        for (Trip trip : LocalStorage.getTrips()) {
            if (trip.getId() == Integer.parseInt((tripId))) {
                tripObject = trip;
                break;
            }
        }
        return tripObject;
    }

    public static void updateTrip(String tripId, String date, String timeOfDeparture, String timeOfArrival,
            String departureAddress, String arrivalAddress, User user, int availableSeats) {

        for (Trip trip : LocalStorage.getTrips()) {
            if (trip.getId() == Integer.parseInt(tripId)) {
                trip.setDate(date);
                trip.setDepartureAddress(departureAddress);
                trip.setArrivalAddress(arrivalAddress);
                trip.setTimeOfDeparture(timeOfDeparture);
                trip.setTimeOfArrival(timeOfArrival);
                trip.setAvailableSeats(availableSeats);
                break;
            }
        }
    }

    public static void createTrip(String date, String timeOfDeparture, String timeOfArrival, String departureAddress,
            String arrivalAddress, User user, int pricePerKm, int availableSeats) throws IOException {

        Trip trip = new Trip(date, timeOfDeparture, timeOfArrival, departureAddress, arrivalAddress, user, pricePerKm,
                availableSeats);
        LocalStorage.addTrip(trip);

        Controller.updateTripsInGoogleStorage(LocalStorage.getTrips(), "Trip.txt");
    }

    public static ArrayList<Trip> searchForTrips(String date, String timeOfDeparture) {

        ArrayList<Trip> matches = new ArrayList<>();
        LocalDate inputLocalDate = LocalDate.parse(date);
        LocalTime inputLocalTime = LocalTime.parse(timeOfDeparture);

        for (Trip t : LocalStorage.getTrips()) {
            LocalDate tripLocalDate = LocalDate.parse(t.getDate());
            LocalTime tripLocalTime = LocalTime.parse(t.getTimeOfDeparture());
            LocalTime tripArrivalLocalTime = LocalTime.parse(t.getTimeOfArrival());

            if (inputLocalDate.equals(tripLocalDate)) {
                if (inputLocalTime.isAfter(tripLocalTime) && inputLocalTime.isBefore(tripArrivalLocalTime)) {
                    if (t.getAvailableSeats() > 0) {
                        matches.add(t);
                    }
                }
            }
        }

        return matches;
    }

    public static Trip searchForRecentTrip(String date, String pickUpPointDepartureTime, User driver,
            String departureAddress, String arrivalAddress, String timeOfDeparture, String timeOfArrival) {

        Trip trip = null;
        LocalTime inputLocalTime = LocalTime.parse(pickUpPointDepartureTime);

        for (Trip t : LocalStorage.getTrips()) {
            LocalTime tripLocalTime = LocalTime.parse(t.getTimeOfDeparture());
            LocalTime tripArrivalLocalTime = LocalTime.parse(t.getTimeOfArrival());

            if (t.getDate().equals(date) && inputLocalTime.isAfter(tripLocalTime)
                    && inputLocalTime.isBefore(tripArrivalLocalTime)) {
                if (t.getDate().equals(date) && t.getDriver().equals(driver)
                        && t.getDepartureAddress().equals(departureAddress)
                        && t.getArrivalAddress().equals(arrivalAddress)
                        && t.getTimeOfDeparture().equals(timeOfDeparture)
                        && t.getTimeOfArrival().equals(timeOfArrival)) {
                    trip = t;
                    break;
                }
            }
        }
        return trip;
    }

    public static PickUpPoint getPickUpPointById(String pickuppointId) {

        PickUpPoint pickUpPointObject = null;

        for (PickUpPoint pickUpPoint : LocalStorage.getPickUpPoints()) {
            if (pickUpPoint.getId() == Integer.parseInt((pickuppointId))) {
                pickUpPointObject = pickUpPoint;
                break;
            }
        }
        return pickUpPointObject;
    }

    public static void createPickUpPoint(User jumper, int tripId, String departureAddress, String arrivalAddress,
            double price, double km) {

        PickUpPoint pickUpPoint = new PickUpPoint(jumper, tripId, departureAddress, arrivalAddress, price, km);
        LocalStorage.addPickUpPoint(pickUpPoint);
        getTripById(Integer.toString(tripId)).addPickUpPoint(pickUpPoint);
    }

    public static PickUpPoint createPickUpPoint(User jumper, int tripId, String departureAddress,
            String arrivalAddress) {

        PickUpPoint pickUpPoint = new PickUpPoint(jumper, tripId, departureAddress, arrivalAddress);
        LocalStorage.addPickUpPoint(pickUpPoint);
        getTripById(Integer.toString(tripId)).addPickUpPoint(pickUpPoint);

        return pickUpPoint;
    }

    public static String getTravelDistanceInKm(String inputUrl) throws IOException {

        URL url = new URL(inputUrl);
        URLConnection request = url.openConnection();
        request.connect();

        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //relationer og simple typer, node tree

        JsonObject rootobj = root.getAsJsonObject();
        String rows = rootobj.get("rows").toString();

        Pattern patt = Pattern.compile("\"([^\"]*)\""); //regexer det, istedet for at gå gennem normal struktur

        Matcher m = patt.matcher(rows);
        int counter = 0;
        String km = "";

        while (m.find()) {

            if (counter == 3) {
                String s = m.group(0);
                String distanceInMiles = s.substring(1, s.length() - 3);
                km = (Double.parseDouble(distanceInMiles) / 5) * 8 + "";
            }
            counter++;
        }

        return km;
    }

    // opretter forbindelse til Google Storage Bucket samt returnerer et
    // bucket-objekt
    public static Bucket getGoogleStorageBucket() throws IOException {
        String bucketName = "jumperr.appspot.com";
        System.out.printf("Bucket name %s ", bucketName);

        FileInputStream stream = new FileInputStream("/Users/made/Yule2019/jumperr-master/src/main/webapp/WEB-INF/credentials/jumperr-f14d60a5e78c.json");
        GoogleCredentials credentials = GoogleCredentials.fromStream(stream);
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        Bucket bucket = storage.get(bucketName);

        return bucket;
    }

    // opretter forbindelse til Google Storage Bucket samt returnerer et
    // storage-objekt
    public static Storage getGoogleStorage() throws IOException {
        String bucketName = "jumperr.appspot.com";
        System.out.printf("Bucket name %s ", bucketName);

        FileInputStream stream = new FileInputStream("/Users/made/Yule2019/jumperr-master/src/main/webapp/WEB-INF/credentials/jumperr-f14d60a5e78c.json");
        GoogleCredentials credentials = GoogleCredentials.fromStream(stream);
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();

        return storage;
    }

    // henter alle users fra Google Storage Bucket, opretter dem som objekter og
    // tilføjer dem til LocalStorage
    public static void downloadUsersFromGoogleStorage(String fileName) throws IOException {
        String dataFileName = fileName;
        Blob blob = getGoogleStorage().get(BlobId.of(getGoogleStorageBucket().getName(), dataFileName));

        ArrayList<String> lines = new ArrayList<>();

        try (ReadChannel reader = blob.reader()) { // import com.google.cloud.ReadChannel;
            BufferedReader br = new BufferedReader(Channels.newReader(reader, "UTF-8")); // import
                                                                                         // java.nio.channels.Channels;

            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }

        for (String line : lines) {

            String[] userInfo = line.split("; ");
            String type = userInfo[0];
            String name = userInfo[1];
            String email = userInfo[2];
            String address = userInfo[3];
            String phoneNumber = userInfo[4];
            String username = userInfo[5];
            String password = userInfo[6];
            String profilePicture = userInfo[7];

            if (type.equals("Admin")) {
                Admin admin = new Admin(name, email, address, Integer.parseInt(phoneNumber), username, password);
                LocalStorage.addUser(admin);
                admin.setProfilePicture(profilePicture);

            } else {
                User user = new User(name, email, address, Integer.parseInt(phoneNumber), username, password);
                LocalStorage.addUser(user);
                user.setProfilePicture(profilePicture);
            }

        }
    }

    // opdaterer User.txt filen i Google Storage Bucket
    // bruges til create, update & delete -operationerne
    public static void updateUsersInGoogleStorage(ArrayList<User> users, String fileName) throws IOException {
        String dataFileName = fileName;
        Blob blob = getGoogleStorage().get(BlobId.of(getGoogleStorageBucket().getName(), dataFileName));

        String newString = "";
        WritableByteChannel channel = blob.writer();

        for (User u : users) {
            newString = newString + u.getClass().getSimpleName() + "; ";
            newString = newString + u.getName() + "; ";
            newString = newString + u.getEmail() + "; ";
            newString = newString + u.getAddress() + "; ";
            newString = newString + u.getTelephoneNumber() + "; ";
            newString = newString + u.getUsername() + "; ";
            newString = newString + u.getPassword() + "; ";
            newString = newString + u.getProfilePicture();
            newString = newString + "\n";
        }

        channel.write(ByteBuffer.wrap(newString.getBytes()));
        channel.close();
    }

    // henter alle trips fra Google Storage Bucket, opretter dem som objekter og
    // tilføjer dem til LocalStorage
    public static void downloadTripsFromGoogleStorage(String fileName) throws IOException {
        String dataFileName = fileName;
        Blob blob = getGoogleStorage().get(BlobId.of(getGoogleStorageBucket().getName(), dataFileName));

        ArrayList<String> lines = new ArrayList<>();

        try (ReadChannel reader = blob.reader()) { // import com.google.cloud.ReadChannel;
            BufferedReader br = new BufferedReader(Channels.newReader(reader, "UTF-8")); // import
                                                                                         // java.nio.channels.Channels;

            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }

        for (String line : lines) {

            String[] TripInfo = line.split("; ");

            String date = TripInfo[1];
            String timeOfDeparture = TripInfo[2];
            String timeOfArrival = TripInfo[3];
            String departureAddress = TripInfo[4];
            String arrivalAddress = TripInfo[5];
            String pricePerKm = TripInfo[6];
            String availableSeats = TripInfo[7];

            String userId = TripInfo[8];
            User user = Controller.getUserById(userId);

            Trip trip = new Trip(date, timeOfDeparture, timeOfArrival, departureAddress, arrivalAddress, user,
                    Integer.parseInt(pricePerKm), Integer.parseInt(availableSeats));
            LocalStorage.addTrip(trip);
        }
    }

    // opdaterer Trip.txt filen i Google Storage Bucket
    // bruges til create, update & delete -operationerne
    public static void updateTripsInGoogleStorage(ArrayList<Trip> trips, String fileName) throws IOException {
        String dataFileName = fileName;
        Blob blob = getGoogleStorage().get(BlobId.of(getGoogleStorageBucket().getName(), dataFileName));

        String newString = "";
        WritableByteChannel channel = blob.writer();

        for (Trip t : trips) {
            newString = newString + t.getClass().getSimpleName() + "; ";
            newString = newString + t.getDate() + "; ";
            newString = newString + t.getTimeOfDeparture() + "; ";
            newString = newString + t.getTimeOfArrival() + "; ";
            newString = newString + t.getDepartureAddress() + "; ";
            newString = newString + t.getArrivalAddress() + "; ";
            newString = newString + t.getPricePerKm() + "; ";
            newString = newString + t.getAvailableSeats() + "; ";
            newString = newString + t.getDriver().getId();
            newString = newString + "\n";
        }

        channel.write(ByteBuffer.wrap(newString.getBytes()));
        channel.close();

    }

    // henter alle pickuppoints fra Google Storage Bucket, opretter dem som objekter
    // og tilføjer dem til LocalStorage
    public static void downloadPickuppointsFromGoogleStorage(String fileName) throws IOException {
        String dataFileName = fileName;
        Blob blob = getGoogleStorage().get(BlobId.of(getGoogleStorageBucket().getName(), dataFileName));

        ArrayList<String> lines = new ArrayList<>();

        try (ReadChannel reader = blob.reader()) { // import com.google.cloud.ReadChannel;
            BufferedReader br = new BufferedReader(Channels.newReader(reader, "UTF-8")); // import
                                                                                         // java.nio.channels.Channels;

            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }

        for (String line : lines) {

            String[] TripInfo = line.split("; ");

            int tripId = Integer.parseInt(TripInfo[1]);
            String departureAddress_pickUpPoint = TripInfo[2];
            String arrivalAddress_pickUpPoint = TripInfo[3];
            double price = Double.parseDouble(TripInfo[4]);
            double km = Double.parseDouble(TripInfo[5]);

            String userId = TripInfo[6];
            User user = Controller.getUserById(userId);

            PickUpPoint pickUpPoint = new PickUpPoint(user, tripId, departureAddress_pickUpPoint,
                    arrivalAddress_pickUpPoint, price, km);
            LocalStorage.addPickUpPoint(pickUpPoint);
            Trip trip = Controller.getTripById(String.valueOf(tripId));
            trip.addPickUpPoint(pickUpPoint);
        }
    }

    // opdaterer Pickuppoints.txt filen i Google Storage Bucket
    // bruges til create, update & delete -operationerne
    public static void updatePickuppointsInGoogleStorage(ArrayList<PickUpPoint> pickUpPoints, String fileName)
            throws IOException {
        String dataFileName = fileName;
        Blob blob = getGoogleStorage().get(BlobId.of(getGoogleStorageBucket().getName(), dataFileName));

        String newString = "";
        WritableByteChannel channel = blob.writer();

        for (PickUpPoint p : pickUpPoints) {
            newString = newString + p.getClass().getSimpleName() + "; ";
            newString = newString + p.getTripId() + "; ";
            newString = newString + p.getDepartureAddress() + "; ";
            newString = newString + p.getArrivalAddress() + "; ";
            newString = newString + p.getPrice() + "; ";
            newString = newString + p.getKm() + "; ";
            newString = newString + p.getJumper().getId();
            newString = newString + "\n";
        }

        channel.write(ByteBuffer.wrap(newString.getBytes()));
        channel.close();
    }

    // henter alle reviews fra Google Storage Bucket, opretter dem som objekter og
    // tilføjer dem til LocalStorage
    public static void downloadReviewsFromGoogleStorage(String fileName) throws IOException {
        String dataFileName = fileName;
        Blob blob = getGoogleStorage().get(BlobId.of(getGoogleStorageBucket().getName(), dataFileName));

        ArrayList<String> lines = new ArrayList<>();

        try (ReadChannel reader = blob.reader()) { // import com.google.cloud.ReadChannel;
            BufferedReader br = new BufferedReader(Channels.newReader(reader, "UTF-8")); // import
                                                                                         // java.nio.channels.Channels;

            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }

        for (String line : lines) {

            String[] reviewInfo = line.split("; ");
            // String type = reviewInfo[0];
            int userId = Integer.parseInt(reviewInfo[1]);
            int reviewedUserId = Integer.parseInt(reviewInfo[2]);
            int stars = Integer.parseInt(reviewInfo[3]);

            Review review = new Review(userId, reviewedUserId, stars);
            LocalStorage.addReview(review);
        }
    }

    // opdaterer Review.txt filen i Google Storage Bucket
    // bruges til create, update & delete -operationerne
    public static void updateReviewsInGoogleStorage(ArrayList<Review> reviews, String fileName) throws IOException {
        String dataFileName = fileName;
        Blob blob = getGoogleStorage().get(BlobId.of(getGoogleStorageBucket().getName(), dataFileName));

        String newString = "";
        WritableByteChannel channel = blob.writer();

        for (Review r : reviews) {
            newString = newString + r.getClass().getSimpleName() + "; ";
            newString = newString + r.getUserId() + "; ";
            newString = newString + r.getReviewedUserId() + "; ";
            newString = newString + r.getStars() + "; ";
            newString = newString + "\n";
        }

        channel.write(ByteBuffer.wrap(newString.getBytes()));
        channel.close();
    }

    // bruges til at hente det uploadede billedes- filnavn & extension fx.
    // 'avatar.png'
    public static String getFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE
                                                                                                                    // fix.
            }
        }
        return null;
    }

    // henter en fil-extension - hvis filen hedder: 'test.tar.gz' så returneres .gz
    public static String getFileExtension(String fileName) {

        int lastIndexOf = fileName.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // hvis det er en tom extension returneres en tom string
        }
        return fileName.substring(lastIndexOf);
    }

    // bliver kaldt i Images-servlet for at vise et specifikt billede
    public static void downloadImageFromGoogleStorage(String imageName, HttpServletResponse response)
            throws IOException {

        Blob blob = Controller.getGoogleStorage()
                .get(BlobId.of(Controller.getGoogleStorageBucket().getName(), imageName));

        response.setContentType("image/jpeg");

        try (ReadChannel reader = blob.reader()) { // import com.google.cloud.ReadChannel;
            ByteBuffer bytes = ByteBuffer.allocate(64 * 1024);
            int r = 0;
            while ((r = reader.read(bytes)) > 0) {
                byte[] buf = new byte[r];
                bytes.position(0);
                bytes.get(buf);

                response.getOutputStream().write(buf);
                response.getOutputStream().flush();
                bytes.clear();
            }
        }
    }

    // uploader et billede til Google Storage Bucket
    public static void uploadImageToGoogleStorage(String imageName, HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {

        Part file = request.getPart("file");
        InputStream filecontent = file.getInputStream();

        BlobId blobId = BlobId.of(getGoogleStorageBucket().getName(), imageName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("application/octet-stream").build();

        byte[] bytes = new byte[1024];
        try (WriteChannel writer = getGoogleStorage().writer(blobInfo)) {
            try {
                int r;
                while ((r = filecontent.read(bytes)) != -1) {
                    writer.write(ByteBuffer.wrap(bytes, 0, r));
                }
            } catch (Exception e) {
                System.out.println("EXCEPTION : " + e.getMessage());
            }
        }
        filecontent.close();

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
    }

    // henter alle aktive trips som en jumper skal køre i, sammen med en driver, ud
    // fra en userID
    public static ArrayList<Trip> getActiveTripsForUser(int userID) {
        ArrayList<Trip> res = new ArrayList<>();

        for (Trip trip : LocalStorage.getTrips()) {
            if (!trip.getPickUpPoints().isEmpty()) {
                ArrayList<PickUpPoint> pickUpPoints = trip.getPickUpPoints();

                for (PickUpPoint pickUpPoint : pickUpPoints) {
                    if (pickUpPoint.getJumper().getId() == userID) {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        String tripDateString = trip.getDate();

                        // convert String to LocalDate
                        LocalDate tripLocalDate = LocalDate.parse(tripDateString, formatter);

                        LocalDate today = LocalDate.now();

                        if (tripLocalDate.isAfter(today) || tripLocalDate.isEqual(today)) {
                            res.add(trip);
                        }

                    }
                }
            }
        }
        return res;
    }

    // sætter den gennemsnitlige score for hver Driver - bruges som en form for
    // rating, til at vise 1-5 stjerner ud for hver Driver
    public static void getAverageScoreForDrivers() throws IOException {
        ArrayList<Review> reviews = LocalStorage.getReviews();
        ArrayList<User> users = LocalStorage.getUsers();

        for (User user : users) {

            int counter = 0;
            int sumScore = 0;

            for (Review review : reviews) {
                if (review.getReviewedUserId() == user.getId()) {
                    counter++;
                    sumScore = sumScore + review.getStars();
                }
            }

            if (counter != 0) {
                user.setRating(sumScore / counter);
            }
        }
    }
}