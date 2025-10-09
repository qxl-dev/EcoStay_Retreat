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
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("user_id", userId);
        cv.put("activity_id", activityId);
        cv.put("date", date);

        long id = db.insert("activity_bookings", null, cv);
        db.close();
        return id;
    }
}
