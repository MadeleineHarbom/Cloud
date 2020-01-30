package main;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.PickUpPoint;
import model.Review;
import model.Trip;
import model.User;
import storage.LocalStorage;

@WebServlet("/Jumper")
public class Jumper extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");
        String tripId = request.getParameter("tripId");
        String departureAddress = request.getParameter("departureAddress");
        String arrivalAddress = request.getParameter("arrivalAddress");

        if (user == null) {

            response.sendRedirect("/");

        } else {

            // hvis man har søgt efter en trip på Jumper siden og klikker på 'Book a seat'
            // for at joine en trip
            if (tripId != null) {

                PickUpPoint pickUpPoint = Controller.createPickUpPoint(user, Integer.parseInt(tripId), departureAddress,
                        arrivalAddress);

                String key = "AIzaSyDRfXcW_SSX-xnAuMlF2by63o8jtqUqDNk";

                String url = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins="
                        + departureAddress + "&destinations=" + arrivalAddress + "&key=" + key;

                //https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=Vestergade+80&destinations=Laurvigsgade+2b&key=AIzaSyAp6OrD4f7H7L78dpUDjxLiHGreAkld6vw
                //Egen API key da den andre er udløbet
                
                url = url.replaceAll("\\s+", "+"); // erstatter whitespaces med + (til URL'en)

                String km = Controller.getTravelDistanceInKm(url);
                pickUpPoint.setKm(Double.parseDouble(km));
                double price = pickUpPoint.calculatePrice();
                pickUpPoint.setPrice(price);

                if (user.getAdmin() == 1) {

                    response.sendRedirect("/Trips");

                } else {

                    response.sendRedirect("/ProfileSettings");
                }

                // når man besøger Jumper-siden og man endnu ikke har søgt på noget
            } else {

                // hvis det er Admin
                if (user.getAdmin() == 1) {

                    request.getRequestDispatcher("/WEB-INF/jsp/admin/jumper_adminPage.jsp").forward(request, response);

                } else {

                    request.getRequestDispatcher("/WEB-INF/jsp/loggedIn/jumper.jsp").forward(request, response);
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // hvis der skal gives en rating til en driver
        User user1 = (User) request.getSession().getAttribute("user");
        String driverScore = request.getParameter("driverScore");
        String driverId = request.getParameter("driverId");
        boolean reviewUpdated = false;

        if (driverScore != null && driverId != null) {
            ArrayList<Review> reviews = LocalStorage.getReviews();

            // hvis brugeren allerede har rated denne driver bliver brugerens rating bare
            // opdateret ellers bliver der tilføjet en ny rating for denne driver
            for (Review review : reviews) {

                int reviewedUserID = Integer.parseInt(driverId);

                if (review.getReviewedUserId() == reviewedUserID && review.getUserId() == user1.getId()) {
                    int score = Integer.parseInt(driverScore);
                    review.setStars(score);
                    reviewUpdated = true;
                    break;
                }

            }
            if (!reviewUpdated) {
                reviews.add(new Review(user1.getId(), Integer.parseInt(driverId), Integer.parseInt(driverScore)));
            }

            // opdaterer rating for drivers lokalt samt på Google Cloud
            Controller.getAverageScoreForDrivers();
            Controller.updateReviewsInGoogleStorage(reviews, "Review.txt");

            // success meddelelse - vises når man giver rating til en driver på jumper-siden
            String success = "Your review is now registered - thanks for your feedback!";
            request.setAttribute("success", success);

            if (user1.getAdmin() == 1) {

                request.getRequestDispatcher("/WEB-INF/jsp/admin/jumper_adminPage.jsp").forward(request, response);

            } else {

                request.getRequestDispatcher("/WEB-INF/jsp/loggedIn/jumper.jsp").forward(request, response);
            }
            return;
        }

        // hvis der søges på en trip via Recent trips -siden
        String recentTrip = request.getParameter("recentTrip");
        String noResults = null;

        if (recentTrip != null && recentTrip.equals("true")) {

            String tripId = request.getParameter("tripId");
            String date = request.getParameter("date");
            String pickUpPointDepartureTime = request.getParameter("timeOfDeparture");
            String pickUpPointFrom = request.getParameter("pickUpPointFrom");
            String pickUpPointTo = request.getParameter("pickUpPointTo");

            Trip t = Controller.getTripById(tripId);
            Trip trip = Controller.searchForRecentTrip(date, pickUpPointDepartureTime, t.getDriver(),
                    t.getDepartureAddress(), t.getArrivalAddress(), t.getTimeOfDeparture(), t.getTimeOfArrival());

            if (trip == null) {
                noResults = "No results match your search criteria...";
                request.setAttribute("noResults", noResults);
            } else {

                request.setAttribute("trip", trip);
                request.setAttribute("pickUpPointFrom", pickUpPointFrom);
                request.setAttribute("pickUpPointTo", pickUpPointTo);
            }

            request.getRequestDispatcher("/WEB-INF/jsp/loggedIn/jumper.jsp").forward(request, response);
            return;
        }

        // hvis der søges på trips via Jumper -siden
        User user = (User) request.getSession().getAttribute("user");

        String date = request.getParameter("date");
        String timeOfDeparture = request.getParameter("timeOfDeparture");
        String fromAddress = request.getParameter("departureAddress");
        String toAddress = request.getParameter("arrivalAddress");

        ArrayList<Trip> matches = Controller.searchForTrips(date, timeOfDeparture);

        if (matches.size() != 0) {

            request.setAttribute("fromAddress", fromAddress);
            request.setAttribute("toAddress", toAddress);
            request.setAttribute("matches", matches);

            if (user.getAdmin() == 1) {

                request.getRequestDispatcher("/WEB-INF/jsp/admin/jumper_adminPage.jsp").forward(request, response);

            } else {

                request.getRequestDispatcher("/WEB-INF/jsp/loggedIn/jumper.jsp").forward(request, response);
            }

        } else {

            noResults = "No results match your search criteria...";
            request.setAttribute("noResults", noResults);

            if (user.getAdmin() == 1) {

                request.getRequestDispatcher("/WEB-INF/jsp/admin/jumper_adminPage.jsp").forward(request, response);

            } else {

                request.getRequestDispatcher("/WEB-INF/jsp/loggedIn/jumper.jsp").forward(request, response);
            }
        }

    }
}
