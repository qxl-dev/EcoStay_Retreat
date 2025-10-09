package com.ecostay.data.model;

public class ActivityBooking {
    public int id;
    public int userId;
    public int activityId;
    public String date;  // Booking date

    // Constructor
    public ActivityBooking(int userId, int activityId, String date) {
        this.userId = userId;
        this.activityId = activityId;
        this.date = date;
    }

    // Getters and setters
    public int getId() { return id; }
    public int getUserId() { return userId; }
    public int getActivityId() { return activityId; }
    public String getDate() { return date; }
}
