package com.ecostay.data.model;

public class Booking {
    public int id;
    public int userId;
    public int roomId;
    public String roomTitle;
    public double price;
    public long timestamp;

    public Booking() {}

    public Booking(int userId, int roomId, String roomTitle, double price) {
        this.userId = userId;
        this.roomId = roomId;
        this.roomTitle = roomTitle;
        this.price = price;
        this.timestamp = System.currentTimeMillis();
    }
}
