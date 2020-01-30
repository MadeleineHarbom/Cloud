package main;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import controller.Controller;
import model.Trip;
import model.User;
import storage.LocalStorage;

@WebServlet("/Update")

// @MultipartConfig gør det muligt at uploade filer (i det her tilfælde er det
// til at uploade billeder)
@MultipartConfig(location = "/tmp", fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024
        * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class Update extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userId = request.getParameter("userId");
        String tripId = request.getParameter("tripId");
        User user1 = (User) request.getSession().getAttribute("user");

        if (user1 != null && user1.getAdmin() == 1) {

            if (userId != null) {

                User user2 = Controller.getUserById(userId);

                request.setAttribute("userId", userId);
                request.setAttribute("profilePicture", user2.getProfilePicture());
                request.setAttribute("name", user2.getName());
                request.setAttribute("email", user2.getEmail());
                request.setAttribute("address", user2.getAddress());
                request.setAttribute("telephoneNumber", Integer.toString(user2.getTelephoneNumber()));
                request.setAttribute("username", user2.getUsername());
                request.setAttribute("password", user2.getPassword());

                request.getRequestDispatcher("/WEB-INF/jsp/admin/updateUser_adminPage.jsp").forward(request, response);

            } else if (tripId != null) {

                Trip trip = Controller.getTripById(tripId);

                request.setAttribute("tripId", tripId);
                request.setAttribute("date", trip.getDate());
                request.setAttribute("timeOfDeparture", trip.getTimeOfDeparture());
                request.setAttribute("timeOfArrival", trip.getTimeOfArrival());
                request.setAttribute("departureAddress", trip.getDepartureAddress());
                request.setAttribute("arrivalAddress", trip.getArrivalAddress());

                request.getRequestDispatcher("/WEB-INF/jsp/admin/updateTrip_adminPage.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("/");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userId = request.getParameter("userId");
        String tripId = request.getParameter("tripId");
        User userLoggedIn = (User) request.getSession().getAttribute("user");

        // når en bruger/user skal opdateres
        if (userId != null) {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            int telephoneNumber = Integer.parseInt(request.getParameter("telephoneNumber"));
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            // billede-navnet skal være unikt - derfor bliver billedets navn ændret til
            // brugerens username som også er unik
            Part file = request.getPart("file");
            String imageName = Controller.getFileName(file);
            String newImageName = userLoggedIn.getUsername() + Controller.getFileExtension(imageName);
            Controller.getUserById(userId).setProfilePicture(newImageName);
            Controller.uploadImageToGoogleStorage(newImageName, request, response);

            Controller.updateUser(userId, email, name, address, telephoneNumber, username, password);
            Controller.updateUsersInGoogleStorage(LocalStorage.getUsers(), "User.txt");

            // hvis admin opdaterer sit profilbillede
            if (userLoggedIn.getAdmin() == 1 && String.valueOf(userLoggedIn.getId()).equals(userId)) {
                int userID = Controller.getUserById(userLoggedIn.getId() + "").getId();
                request.getSession().removeAttribute("user");

                User newAdminObject = Controller.getUserById(userID + "");
                request.getSession().setAttribute("user", newAdminObject);

                // hvis en almindelig bruger opdaterer sit profilbillede
            } else if (userLoggedIn.getAdmin() == 0 && String.valueOf(userLoggedIn.getId()).equals(userId)) {
                request.getSession().removeAttribute("user");

                User newUser = Controller.getUserById(userId);
                request.getSession().setAttribute("user", newUser);
            }

            User userLoggedIn2 = (User) request.getSession().getAttribute("user");

            // hvis det er Admin skal man navigeres videre til forsiden ellers er det til
            // /MyProfile
            if (userLoggedIn2.getAdmin() == 1) {
                response.sendRedirect("/");

            } else {
                response.sendRedirect("/MyProfile");
            }

            // når en trip skal opdateres
        } else if (tripId != null) {

            User user = (User) request.getSession().getAttribute("user");
            String date = request.getParameter("date");
            String timeOfDeparture = request.getParameter("timeOfDeparture");
            String timeOfArrival = request.getParameter("timeOfArrival");
            String departureAddress = request.getParameter("departureAddress");
            String arrivalAddress = request.getParameter("arrivalAddress");
            int availableSeats = Controller.getTripById(tripId).getAvailableSeats();

            Controller.updateTrip(tripId, date, timeOfDeparture, timeOfArrival, departureAddress, arrivalAddress, user,
                    availableSeats);

            Controller.updateTripsInGoogleStorage(LocalStorage.getTrips(), "Trip.txt");

            response.sendRedirect("/Trips");

        } else {
            response.sendRedirect("/");
        }
    }
}
