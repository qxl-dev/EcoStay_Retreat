package com.ecostay.data.model;

public class User {
    public int id;
    public String name;
    public String email;
    public String password;
    public String preferences;
    public String travelDates;
    public String ecoInterests;

    public User(int id, String name, String email, String password, String preferences) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.preferences = preferences;
    }

    public User(int id, String name, String email, String password, String preferences, String travelDates, String ecoInterests) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.preferences = preferences;
        this.travelDates = travelDates;
        this.ecoInterests = ecoInterests;
    }

    // Empty constructor
    public User() {
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPreferences() {
        return preferences;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }

    public String getTravelDates() {
        return travelDates;
    }

    public void setTravelDates(String travelDates) {
        this.travelDates = travelDates;
    }

    public String getEcoInterests() {
        return ecoInterests;
    }

    public void setEcoInterests(String ecoInterests) {
        this.ecoInterests = ecoInterests;
    }
}
