package model;

import storage.LocalStorage;

public class Review {

    private int id;
    private int userId; // brugeren der giver review til en driver
    private int reviewedUserId; // brugeren der får en review - typisk en driver
    private int stars; // 1-5 stjerner

    public Review(int userId, int reviewedUserId, int stars) {
        this.id = generateUniqueID();
        this.userId = userId;
        this.reviewedUserId = reviewedUserId;
        this.stars = stars;
    }

    public int generateUniqueID() {
        int id = 0;

        if (LocalStorage.getReviews().size() == 0) {
            id = 1;
        } else {
            int lastReviewIndex = LocalStorage.getReviews().size() - 1;
            id = LocalStorage.getReviews().get(lastReviewIndex).id + 1;
        }
        return id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getReviewedUserId() {
        return reviewedUserId;
    }

    public void setReviewedUserId(int reviewedUserId) {
        this.reviewedUserId = reviewedUserId;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    // et review-objekt er det samme hvis det har samme reviewedUserId & userId
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + reviewedUserId;
        result = prime * result + userId;
        return result;
    }

    // et review-objekt er det samme hvis det har samme reviewedUserId & userId
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
        Review other = (Review) obj;
        if (reviewedUserId != other.reviewedUserId) {
            return false;
        }
        if (userId != other.userId) {
            return false;
        }
        return true;
    }

}
