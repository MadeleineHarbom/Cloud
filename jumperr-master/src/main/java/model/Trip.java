package model;

import java.util.ArrayList;

import storage.LocalStorage;

public class Trip {

    private int id;
    private String date;
    private String timeOfDeparture; // afgangstidspunkt
    private String timeOfArrival; // ankomsttidspunkt
    private String departureAddress; // afgangsadresse
    private String arrivalAddress; // ankomstadresse
    private User driver;
    private int pricePerKm;
    private int availableSeats;
    private ArrayList<PickUpPoint> pickUpPoints = new ArrayList<>();

    public Trip(String date, String timeOfDeparture, String timeOfArrival, String departureAddress,
            String arrivalAddress, User driver, int pricePerKm, int availableSeats) {
        this.id = generateUniqueID();
        this.date = date;
        this.timeOfDeparture = timeOfDeparture;
        this.timeOfArrival = timeOfArrival;
        this.departureAddress = departureAddress;
        this.arrivalAddress = arrivalAddress;
        this.driver = driver;
        this.pricePerKm = pricePerKm;
        this.availableSeats = availableSeats;
    }

    // bliver brugt når Trip-objekterne hentes fra databasen og skal oprettes som
    // Trip-objekter
    public Trip(int id, String date, String timeOfDeparture, String timeOfArrival, String departureAddress,
            String arrivalAddress, User driver, int pricePerKm, int availableSeats) {
        this.id = id;
        this.date = date;
        this.timeOfDeparture = timeOfDeparture;
        this.timeOfArrival = timeOfArrival;
        this.departureAddress = departureAddress;
        this.arrivalAddress = arrivalAddress;
        this.driver = driver;
        this.pricePerKm = pricePerKm;
        this.availableSeats = availableSeats;
    }

    public int generateUniqueID() {
        int id = 0;

        if (LocalStorage.getTrips().size() == 0) {
            id = 1;
        } else {
            int lastTripIndex = LocalStorage.getTrips().size() - 1;
            id = LocalStorage.getTrips().get(lastTripIndex).id + 1;
        }
        return id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeOfDeparture() {
        return timeOfDeparture;
    }

    public void setTimeOfDeparture(String timeOfDeparture) {
        this.timeOfDeparture = timeOfDeparture;
    }

    public String getTimeOfArrival() {
        return timeOfArrival;
    }

    public void setTimeOfArrival(String timeOfArrival) {
        this.timeOfArrival = timeOfArrival;
    }

    public String getDepartureAddress() {
        return departureAddress;
    }

    public void setDepartureAddress(String departureAddress) {
        this.departureAddress = departureAddress;
    }

    public String getArrivalAddress() {
        return arrivalAddress;
    }

    public void setArrivalAddress(String arrivalAddress) {
        this.arrivalAddress = arrivalAddress;
    }

    public User getDriver() {
        return driver;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public int getPricePerKm() {
        return pricePerKm;
    }

    public void setPricePerKm(int pricePerKm) {
        this.pricePerKm = pricePerKm;
    }

    public ArrayList<PickUpPoint> getPickUpPoints() {
        return pickUpPoints;
    }

    public void addPickUpPoint(PickUpPoint pickUpPoint) {
        this.pickUpPoints.add(pickUpPoint);
        this.availableSeats = this.availableSeats - 1;
    }

    public void removePickUpPoint(PickUpPoint pickUpPoint) {
        this.pickUpPoints.remove(pickUpPoint);
        this.availableSeats = this.availableSeats + 1;
    }

    // et trip-objekt er det samme hvis det har samme arrivalAddress,
    // departureAddress, driver, timeOfArrival, timeOfDeparture
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((arrivalAddress == null) ? 0 : arrivalAddress.hashCode());
        result = prime * result + ((departureAddress == null) ? 0 : departureAddress.hashCode());
        result = prime * result + ((driver == null) ? 0 : driver.hashCode());
        result = prime * result + ((timeOfArrival == null) ? 0 : timeOfArrival.hashCode());
        result = prime * result + ((timeOfDeparture == null) ? 0 : timeOfDeparture.hashCode());
        return result;
    }

    // et trip-objekt er det samme hvis det har samme arrivalAddress,
    // departureAddress, driver, timeOfArrival, timeOfDeparture
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Trip other = (Trip) obj;
        if (arrivalAddress == null) {
            if (other.arrivalAddress != null) {
                return false;
            }
        } else if (!arrivalAddress.equals(other.arrivalAddress)) {
            return false;
        }
        if (departureAddress == null) {
            if (other.departureAddress != null) {
                return false;
            }
        } else if (!departureAddress.equals(other.departureAddress)) {
            return false;
        }
        if (driver == null) {
            if (other.driver != null) {
                return false;
            }
        } else if (!driver.equals(other.driver)) {
            return false;
        }
        if (timeOfArrival == null) {
            if (other.timeOfArrival != null) {
                return false;
            }
        } else if (!timeOfArrival.equals(other.timeOfArrival)) {
            return false;
        }
        if (timeOfDeparture == null) {
            if (other.timeOfDeparture != null) {
                return false;
            }
        } else if (!timeOfDeparture.equals(other.timeOfDeparture)) {
            return false;
        }
        return true;
    }

}
