package com.ecostay.data.model;

public class ActivityItem {
    public int id;
    public String title;
    public String description;
    public double price;

    // Constructor
    public ActivityItem(String title, String description, double price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }

    // Constructor with ID
    public ActivityItem(int id, String title, String description, double price) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
    }

    // Getters and setters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
}
