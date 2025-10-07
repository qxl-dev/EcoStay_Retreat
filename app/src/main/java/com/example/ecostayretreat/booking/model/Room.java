package com.example.ecostayretreat.booking.model;

import androidx.annotation.NonNull; // <-- IMPORT THIS
import androidx.room.Entity;      // <-- IMPORT THIS
import androidx.room.PrimaryKey;    // <-- IMPORT THIS

import java.io.Serializable;

// @Entity tells Room to create a database table for this class.
@Entity(tableName = "rooms")
public class Room implements Serializable {

    // @PrimaryKey identifies the unique ID for each row in the table.
    // @NonNull means this value can never be null.
    @PrimaryKey
    @NonNull
    private String id;

    private String name;
    private String description;
    private double price;
    private String imageUrl;

    // --- ADD AN EMPTY CONSTRUCTOR ---
    // Room needs an empty constructor for it to work.
    public Room() {}
    // --------------------------------

    // Your existing constructor
    public Room(@NonNull String id, String name, String description, double price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    // Getters and Setters remain the same...
    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
