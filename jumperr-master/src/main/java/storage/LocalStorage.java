package storage;

import java.util.ArrayList;
import model.*;

public class LocalStorage {

    public static ArrayList<User> users = new ArrayList<>();
    public static ArrayList<Trip> trips = new ArrayList<>();
    public static ArrayList<PickUpPoint> pickUpPoints = new ArrayList<>();
    public static ArrayList<Review> reviews = new ArrayList<>();

    // users
    public static ArrayList<User> getUsers() {
        return users;
    }

    public static void addUser(User user) {
        if (!users.contains(user)) {
            users.add(user);
        }
    }

    public static void removeUser(User user) {
        if (users.contains(user)) {
            users.remove(user);
        }
    }

    // trips
    public static ArrayList<Trip> getTrips() {
        return trips;
    }

    public static void addTrip(Trip trip) {
        trips.add(trip);
    }

    public static void removeTrip(Trip trip) {
        if (trips.contains(trip)) {
            trips.remove(trip);
        }
    }

    // pickUpPoints
    public static ArrayList<PickUpPoint> getPickUpPoints() {
        return pickUpPoints;
    }

    public static void addPickUpPoint(PickUpPoint pickUpPoint) {
        pickUpPoints.add(pickUpPoint);
    }

    public static void removePickUpPoint(PickUpPoint pickUpPoint) {
        if (pickUpPoints.contains(pickUpPoint)) {
            pickUpPoints.remove(pickUpPoint);
        }
    }

    // reviews
    public static ArrayList<Review> getReviews() {
        return reviews;
    }

    public static void addReview(Review review) {
        if (!reviews.contains(review)) {
            reviews.add(review);
        }
    }

    public static void removeReview(Review review) {
        if (reviews.contains(review)) {
            reviews.remove(review);
        }
    }

}