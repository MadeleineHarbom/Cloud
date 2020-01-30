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

@WebServlet("/")
public class Frontpage extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {

            // initialiserer nogle user-objekter
            if (LocalStorage.getUsers().size() == 0) {
                Controller.downloadUsersFromGoogleStorage("User.txt");
            }

            // initialiserer nogle trip-objekter
            if (LocalStorage.getTrips().size() == 0) {
                Controller.downloadTripsFromGoogleStorage("Trip.txt");
            }

            // initialiserer nogle pickuppoint-objekter
            if (LocalStorage.getPickUpPoints().size() == 0) {
                Controller.downloadPickuppointsFromGoogleStorage("Pickuppoint.txt");
            }

            // initialiserer nogle reviews-objekter
            if (LocalStorage.getReviews().size() == 0) {
                Controller.downloadReviewsFromGoogleStorage("Review.txt");

                // sætter rating for hver driver, ud fra gennemsnittet af alle ratings som er
                // givet for hver driver
                Controller.getAverageScoreForDrivers();
            }

            request.getRequestDispatcher("/WEB-INF/jsp/notLoggedIn/login.jsp").forward(request, response);

        } else {

            // hvis det er Admin
            if (user.getAdmin() == 1) {

                request.setAttribute("users", LocalStorage.getUsers());
                request.setAttribute("username", user.getUsername());

                request.getRequestDispatcher("/WEB-INF/jsp/admin/users_adminPage.jsp").forward(request, response);

            } else {

                request.setAttribute("username", user.getUsername());
                request.getRequestDispatcher("/WEB-INF/jsp/loggedIn/myRecentTrips.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String error = null;

        User user = Controller.authenticateUser(username, password);

        if (user != null) {

            request.getSession().setAttribute("user", user);
            response.sendRedirect("/");

        } else {
            error = "The username & password combination are incorrect...";
            request.setAttribute("error", error);

            request.getRequestDispatcher("/WEB-INF/jsp/notLoggedIn/login.jsp").forward(request, response);
        }

    }
}