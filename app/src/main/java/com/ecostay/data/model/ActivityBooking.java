package com.ecostay.data.model;

public class ActivityBooking {
    public int id;
    public int userId;
    public int activityId;
    public long bookingDate;

    // Default constructor
    public ActivityBooking() {}

    // Constructor with values
    public ActivityBooking(int userId, int activityId, long bookingDate) {
        this.userId = userId;
        this.activityId = activityId;
        this.bookingDate = bookingDate;
    }
}
