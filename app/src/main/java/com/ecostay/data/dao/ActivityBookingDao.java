package com.ecostay.data.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ecostay.data.DatabaseHelper;
import com.ecostay.data.model.ActivityBooking;

public class ActivityBookingDao {
    private DatabaseHelper dbHelper;

    public ActivityBookingDao(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    // Add activity booking
    public long addActivityBooking(int userId, int activityId, String date) {
        if (userId <= 0 || activityId <= 0) {
            return -1; // Invalid parameters
        }

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("user_id", userId);
        cv.put("activity_id", activityId);
        cv.put("date", date);

        long id = db.insert("activity_bookings", null, cv);
        db.close();
        return id;
    }

    // Check if user already has a booking for this activity
    public boolean hasExistingBooking(int userId, int activityId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(
            "SELECT COUNT(*) FROM activity_bookings WHERE user_id = ? AND activity_id = ?",
            new String[]{String.valueOf(userId), String.valueOf(activityId)}
        );
        
        boolean hasBooking = false;
        if (cursor.moveToFirst()) {
            hasBooking = cursor.getInt(0) > 0;
        }
        
        cursor.close();
        db.close();
        return hasBooking;
    }

    // Get all bookings for a user
    public java.util.List<com.ecostay.data.model.ActivityBooking> getUserBookings(int userId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        java.util.List<com.ecostay.data.model.ActivityBooking> bookings = new java.util.ArrayList<>();
        
        Cursor cursor = db.rawQuery(
            "SELECT * FROM activity_bookings WHERE user_id = ?",
            new String[]{String.valueOf(userId)}
        );
        
        if (cursor.moveToFirst()) {
            do {
                com.ecostay.data.model.ActivityBooking booking = new com.ecostay.data.model.ActivityBooking(
                    cursor.getInt(cursor.getColumnIndexOrThrow("user_id")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("activity_id")),
                    cursor.getString(cursor.getColumnIndexOrThrow("date"))
                );
                booking.id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                bookings.add(booking);
            } while (cursor.moveToNext());
        }
        
        cursor.close();
        db.close();
        return bookings;
    }

    // Get activity booking details with activity information
    public java.util.List<java.util.Map<String, String>> getActivityBookingDetails(int userId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        java.util.List<java.util.Map<String, String>> bookingDetails = new java.util.ArrayList<>();
        
        String query = "SELECT ab.id, ab.date, a.title, a.description, a.price " +
                      "FROM activity_bookings ab " +
                      "JOIN activities a ON ab.activity_id = a.id " +
                      "WHERE ab.user_id = ? " +
                      "ORDER BY ab.date DESC";
        
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(userId)});
        
        if (cursor.moveToFirst()) {
            do {
                java.util.Map<String, String> booking = new java.util.HashMap<>();
                booking.put("id", String.valueOf(cursor.getInt(cursor.getColumnIndexOrThrow("id"))));
                booking.put("title", cursor.getString(cursor.getColumnIndexOrThrow("title")));
                booking.put("description", cursor.getString(cursor.getColumnIndexOrThrow("description")));
                booking.put("price", String.valueOf(cursor.getDouble(cursor.getColumnIndexOrThrow("price"))));
                booking.put("date", cursor.getString(cursor.getColumnIndexOrThrow("date")));
                bookingDetails.add(booking);
            } while (cursor.moveToNext());
        }
        
        cursor.close();
        db.close();
        return bookingDetails;
    }

    // Delete activity booking
    public boolean deleteActivityBooking(int bookingId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rowsAffected = db.delete("activity_bookings", "id = ?", new String[]{String.valueOf(bookingId)});
        db.close();
        return rowsAffected > 0;
    }
}
