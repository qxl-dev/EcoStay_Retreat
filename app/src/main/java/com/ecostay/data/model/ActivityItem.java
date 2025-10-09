package com.ecostay.data.model;

public class ActivityItem {
    public int id;
    public String title;
    public String description;
    public double price;
    public long timestamp;  // Add timestamp to track activity creation time

    // Constructor with required parameters
    public ActivityItem(String title, String description, double price, long timestamp) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.timestamp = timestamp;
    }
}
