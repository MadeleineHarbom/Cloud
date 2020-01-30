package main;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.User;
import storage.LocalStorage;

@WebServlet("/Trips")
public class Trips extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");

        if (user != null && user.getAdmin() == 1) {

            request.setAttribute("trips", LocalStorage.getTrips());
            request.setAttribute("username", user.getUsername());

            request.getRequestDispatcher("/WEB-INF/jsp/admin/trips_adminPage.jsp").forward(request, response);

        } else {
            response.sendRedirect("/");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int tripId = Integer.parseInt(request.getParameter("tripId"));
        if (Controller.getTripById(String.valueOf(tripId)).getAvailableSeats() > 0) {
            User jumper = (User) request.getSession().getAttribute("user");
            String departureAddress = request.getParameter("departureAddress");
            String arrivalAddress = request.getParameter("arrivalAddress");
            double price = Double.parseDouble(request.getParameter("price"));
            double km = Double.parseDouble(request.getParameter("km"));

            Controller.createPickUpPoint(jumper, tripId, departureAddress, arrivalAddress, price, km);
            Controller.updatePickuppointsInGoogleStorage(LocalStorage.getPickUpPoints(), "Pickuppoint.txt");
            response.sendRedirect("/Trips");

        } else {

            String error = "There are no more seats available for this trip...";
            request.setAttribute("error", error);
            request.getRequestDispatcher("/WEB-INF/jsp/admin/trips_adminPage.jsp").forward(request, response);
        }

    }

}