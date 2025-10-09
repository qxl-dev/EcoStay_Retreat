package com.ecostay.data.model;

public class Room {
    public int id;
    public String title;
    public String type;
    public String description;
    public double price;
    public boolean available;

    // Constructor with parameters
    public Room(int id, String title, String type, String description, double price, boolean available) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.description = description;
        this.price = price;
        this.available = available;
    }

    // Empty constructor
    public Room() {
    }

    // Getters and setters (optional)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Add other getter and setter methods for the rest of the fields
}
