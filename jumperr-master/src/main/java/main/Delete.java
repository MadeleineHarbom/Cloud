package main;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.*;
import storage.LocalStorage;

@WebServlet("/Delete")
public class Delete extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userId = request.getParameter("userId");
        String tripId = request.getParameter("tripId");
        String pickuppointId = request.getParameter("pickuppointId");
        User user1 = (User) request.getSession().getAttribute("user");

        if (user1 != null && user1.getAdmin() == 1) {

            if (userId != null) {

                User user2 = Controller.getUserById(userId);
                LocalStorage.removeUser(user2);

                Controller.updateUsersInGoogleStorage(LocalStorage.getUsers(), "User.txt");

                response.sendRedirect("/");
                return;

            } else if (tripId != null) {
                Trip trip = Controller.getTripById(tripId);
                LocalStorage.removeTrip(trip);

                Controller.updateTripsInGoogleStorage(LocalStorage.getTrips(), "Trip.txt");
                response.sendRedirect("/Trips");
                return;

            } else if (pickuppointId != null) {
                PickUpPoint pickuppoint = Controller.getPickUpPointById(pickuppointId);
                int tripIdForPickUpPoint = pickuppoint.getTripId();
                Controller.getTripById(tripIdForPickUpPoint + "").removePickUpPoint(pickuppoint);
                LocalStorage.removePickUpPoint(pickuppoint);

                Controller.updatePickuppointsInGoogleStorage(LocalStorage.getPickUpPoints(), "Pickuppoint.txt");
                response.sendRedirect("/Trips");
                return;
            }
        }
        response.sendRedirect("/");
    }
}
