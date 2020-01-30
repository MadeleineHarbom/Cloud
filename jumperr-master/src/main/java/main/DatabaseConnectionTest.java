package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.*;

@WebServlet("/DatabaseConnectionTest")
public class DatabaseConnectionTest extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        // users
        out.println("USERS:");
        ArrayList<String> rows1 = Controller.getUsers_GoogleCloudMySQL();
        for (String rowString : rows1) {

            String[] row = rowString.split(";");
            for (int i = 0; i < row.length; i++) {
                out.print(row[i] + " | ");
            }
            out.println();
        }

        out.println();

        // trips
        out.println("TRIPS:");
        ArrayList<String> rows2 = Controller.getTrips_GoogleCloudMySQL();
        for (String rowString : rows2) {

            String[] row = rowString.split(";");
            for (int i = 0; i < row.length; i++) {
                out.print(row[i] + " | ");
            }
            out.println();
        }

        out.println();

        // pickuppoints
        out.println("PICKUPPOINTS:");
        ArrayList<String> rows3 = Controller.getPickuppoints_GoogleCloudMySQL();
        for (String rowString : rows3) {

            String[] row = rowString.split(";");
            for (int i = 0; i < row.length; i++) {
                out.print(row[i] + " | ");
            }
            out.println();
        }

        out.println();

        out.println("AUTHENTICATE USER (Username: Rabeea_M, Password: privat):");
        if (Controller.authenticateUser_GoogleCloudMySQL("Rabeea_M", "privat") != null) {
            out.println("The username & password are correct!");
        } else {
            out.println("The username & password combination is incorrect!!");
        }

        out.println();

        out.println("GET USER BY ID (ID 1):");
        if (Controller.getUserById_GoogleCloudMySQL("1") != null) {
            out.println("The user exists!");
        } else {
            out.println("The user don't exist!");
        }

        out.println();

        out.println("CHECK IF USERNAME EXISTS (Rabeea_M):");
        if (Controller.checkIfUserExists_GoogleCloudMySQL("Rabeea_M")) {
            out.println("The username exists!");
        } else {
            out.println("The username don't exist!");
        }

        out.println();

        out.println("CREATE USER (RALLE bliver oprettet):");
        Controller.createUser_GoogleCloudMySQL("Ralle", "Ralle@hotmail.com", "Vestergade 44", 10203040, "Ralle_N",
                "password123", 0);
        ArrayList<String> rows4 = Controller.getUsers_GoogleCloudMySQL();
        for (String rowString : rows4) {

            String[] row = rowString.split(";");
            for (int i = 0; i < row.length; i++) {
                out.print(row[i] + " | ");
            }
            out.println();
        }

        out.println();

        // metoden skal ændres i Controller så at den opdaterer hvor id = ? og ikke
        // hvor id > ?
        out.println("UPDATE USER (RALLE bliver opdateret):");
        Controller.updateUser_GoogleCloudMySQL("4", "Ralle", "Ralle@hotmail.com", "Vestergade 44", 10203040,
                "Ralle_TeamKiller", "hemmelig", "avatar.png");
        ArrayList<String> rows5 = Controller.getUsers_GoogleCloudMySQL();
        for (String rowString : rows5) {

            String[] row = rowString.split(";");
            for (int i = 0; i < row.length; i++) {
                out.print(row[i] + " | ");
            }
            out.println();
        }

        out.println();

        // metoden skal ændres i Controller så at den opdaterer hvor id = ? og ikke
        // hvor id > ?
        out.println("DELETE USER (RALLE bliver slettet):");
        Controller.deleteUser_GoogleCloudMySQL("4");
        ArrayList<String> rows6 = Controller.getUsers_GoogleCloudMySQL();
        for (String rowString : rows6) {

            String[] row = rowString.split(";");
            for (int i = 0; i < row.length; i++) {
                out.print(row[i] + " | ");
            }
            out.println();
        }

        out.println();

        out.println("GET TRIP BY ID (ID 1):");
        if (Controller.getTripById_GoogleCloudMySQL("1") != null) {
            out.println("The trip exists!");
        } else {
            out.println("The trip don't exist!");
        }

        out.println();

        out.println("CREATE TRIP (Trip3 bliver oprettet):");
        User driver = Controller.getUserById_GoogleCloudMySQL("1");
        Controller.createTrip_GoogleCloudMySQL("20-10-2019", "12:00", "15:00", "Sigridsvej 41", "Viby torv", driver, 2,
                4);
        ArrayList<String> rows7 = Controller.getTrips_GoogleCloudMySQL();
        for (String rowString : rows7) {

            String[] row = rowString.split(";");
            for (int i = 0; i < row.length; i++) {
                out.print(row[i] + " | ");
            }
            out.println();
        }

        out.println();

        // metoden skal ændres i Controller så at den opdaterer hvor id = ? og ikke
        // hvor id > ?
        out.println("UPDATE TRIP (Trip3 bliver opdateret):");
        Controller.updateTrip_GoogleCloudMySQL("2", "2019-10-20", "12:00", "15:00", "Sigridsvej 41", "Banegården 1",
                driver, 4);
        ArrayList<String> rows8 = Controller.getTrips_GoogleCloudMySQL();
        for (String rowString : rows8) {

            String[] row = rowString.split(";");
            for (int i = 0; i < row.length; i++) {
                out.print(row[i] + " | ");
            }
            out.println();
        }

        out.println();

        // metoden skal ændres i Controller så at den opdaterer hvor id = ? og ikke
        // hvor id > ?
        out.println("DELETE TRIP (Trip3 bliver slettet):");
        Controller.deleteTrip_GoogleCloudMySQL("2");
        ArrayList<String> rows9 = Controller.getTrips_GoogleCloudMySQL();
        for (String rowString : rows9) {

            String[] row = rowString.split(";");
            for (int i = 0; i < row.length; i++) {
                out.print(row[i] + " | ");
            }
            out.println();
        }

        out.println();

        out.println("SEARCH FOR TRIPS BY DATE AND TIME: (Date: 10-10-2019, Time: 18:50):");
        ArrayList<Trip> rows10 = Controller.searchForTrips_GoogleCloudMySQL("2019-10-10", "18:50");
        for (Trip trip : rows10) {

            out.print("ID for Trip: " + trip.getId());
            out.println();
        }

        out.println();

        out.println("GET PICKUPPOINT BY ID (ID 1):");
        if (Controller.getPickUpPointById_GoogleCloudMySQL("1") != null) {
            out.println("The pickuppoint exists!");
        } else {
            out.println("The pickuppoint don't exist!");
        }

        out.println();

        out.println("GET PICKUPPOINTS BY TRIP-ID (ID 1):");
        ArrayList<String> rows11 = Controller.getPickUpPointsByTripId_GoogleCloudMySQL("1");
        for (String rowString : rows11) {

            String[] row = rowString.split(";");
            for (int i = 0; i < row.length; i++) {
                out.print(row[i] + " | ");
            }
            out.println();
        }

        out.println();

        out.println("CREATE PICKUPPOINT (Pickuppoint2 bliver oprettet):");
        User jumper = Controller.getUserById_GoogleCloudMySQL("1");
        Controller.createPickUpPoint_GoogleCloudMySQL(jumper, 1, "Vestergade 48", "Nørre alle 77", 10.5, 1.2);
        ArrayList<String> rows12 = Controller.getPickuppoints_GoogleCloudMySQL();
        for (String rowString : rows12) {

            String[] row = rowString.split(";");
            for (int i = 0; i < row.length; i++) {
                out.print(row[i] + " | ");
            }
            out.println();
        }

        out.println();

        // metoden skal ændres i Controller så at den opdaterer hvor id = ? og ikke
        // hvor id > ?
        out.println("DELETE PICKUPPOINT (Pickuppoint2 bliver slettet):");
        Controller.deletePickUpPoint_GoogleCloudMySQL("1");
        ArrayList<String> rows13 = Controller.getPickuppoints_GoogleCloudMySQL();
        for (String rowString : rows13) {

            String[] row = rowString.split(";");
            for (int i = 0; i < row.length; i++) {
                out.print(row[i] + " | ");
            }
            out.println();
        }

    }
}
