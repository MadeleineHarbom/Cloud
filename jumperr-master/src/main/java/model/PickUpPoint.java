package model;

import controller.Controller;
import storage.LocalStorage;

public class PickUpPoint {

    private int id;
    private User jumper;
    private int tripId;
    private String departureAddress; // afgangsadresse
    private String arrivalAddress; // ankomstadresse
    private double price;
    private double km;

    // bliver brugt når Admin opretter PickUpPoints på trip-siden
    public PickUpPoint(User jumper, int tripId, String departureAddress, String arrivalAddress, double price,
            double km) {
        this.id = generateUniqueID();
        this.jumper = jumper;
        this.tripId = tripId;
        this.departureAddress = departureAddress;
        this.arrivalAddress = arrivalAddress;
        this.price = price;
        this.km = km;
    }

    // bliver brugt når almindelige brugere/Jumper opretter en PickUpPoint ved at
    // klikke på "Book seat" på jumper-siden
    public PickUpPoint(User jumper, int tripId, String departureAddress, String arrivalAddress) {
        this.id = generateUniqueID();
        this.jumper = jumper;
        this.tripId = tripId;
        this.departureAddress = departureAddress;
        this.arrivalAddress = arrivalAddress;
    }

    /*
     * bliver brugt når Admin opretter PickUpPoints på trip-siden - bliver også
     * brugt når Pickuppoint-objekterne hentes fra databasen og skal oprettes som
     * Pickuppoint-objekter
     */
    public PickUpPoint(int id, User jumper, int tripId, String departureAddress, String arrivalAddress, double price,
            double km) {
        this.id = id;
        this.jumper = jumper;
        this.tripId = tripId;
        this.departureAddress = departureAddress;
        this.arrivalAddress = arrivalAddress;
        this.price = price;
        this.km = km;
    }

    /*
     * bliver brugt når almindelige brugere/Jumper opretter en PickUpPoint ved at
     * klikke på "Book seat" på jumper-siden - bliver også brugt når
     * Pickuppoint-objekterne hentes fra databasen og skal oprettes som
     * Pickuppoint-objekter
     */
    public PickUpPoint(int id, User jumper, int tripId, String departureAddress, String arrivalAddress) {
        this.id = id;
        this.jumper = jumper;
        this.tripId = tripId;
        this.departureAddress = departureAddress;
        this.arrivalAddress = arrivalAddress;
    }

    public int generateUniqueID() {
        int id = 0;
        int numberOfPickUpPointsInTrip = LocalStorage.getPickUpPoints().size();

        if (numberOfPickUpPointsInTrip == 0) {
            id = 1;
        } else {
            id = (numberOfPickUpPointsInTrip + 1);
        }
        return id;
    }

    public double calculatePrice() {
        double km = getKm();
        Trip trip = Controller.getTripById(String.valueOf(getTripId()));
        double price = (trip.getPricePerKm() * km);

        return price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getJumper() {
        return jumper;
    }

    public void setJumper(User jumper) {
        this.jumper = jumper;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getKm() {
        return km;
    }

    public void setKm(double km) {
        this.km = km;
    }

    // et pickuppoint-objekt er det samme hvis det har samme arrivalAddress,
    // departureAddress, jumper, tripId
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((arrivalAddress == null) ? 0 : arrivalAddress.hashCode());
        result = prime * result + ((departureAddress == null) ? 0 : departureAddress.hashCode());
        result = prime * result + ((jumper == null) ? 0 : jumper.hashCode());
        result = prime * result + tripId;
        return result;
    }

    // et pickuppoint-objekt er det samme hvis det har samme arrivalAddress,
    // departureAddress, jumper, tripId
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
        PickUpPoint other = (PickUpPoint) obj;
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
        if (jumper == null) {
            if (other.jumper != null) {
                return false;
            }
        } else if (!jumper.equals(other.jumper)) {
            return false;
        }
        if (tripId != other.tripId) {
            return false;
        }
        return true;
    }

}
