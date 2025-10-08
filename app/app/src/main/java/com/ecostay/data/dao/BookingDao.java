package com.ecostay.data.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.ecostay.data.DatabaseHelper;
import com.ecostay.data.model.Booking;
import java.util.ArrayList;
import java.util.List;


public class BookingDao {
    private final DatabaseHelper dbHelper;

    public BookingDao(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public long addBooking(Booking booking) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("user_id", booking.userId);
        cv.put("room_id", booking.roomId);
        cv.put("room_title", booking.roomTitle);
        cv.put("price", booking.price);
        cv.put("timestamp", System.currentTimeMillis());
        long id = db.insert("bookings", null, cv);
        db.close();
        return id;
    }

    public List<Booking> getBookingsForUser(int userId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<Booking> list = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM bookings WHERE user_id=? ORDER BY timestamp DESC",
                new String[]{String.valueOf(userId)});

        if (cursor.moveToFirst()) {
            do {
                Booking b = new Booking();
                b.id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                b.userId = cursor.getInt(cursor.getColumnIndexOrThrow("user_id"));
                b.roomId = cursor.getInt(cursor.getColumnIndexOrThrow("room_id"));
                b.roomTitle = cursor.getString(cursor.getColumnIndexOrThrow("room_title"));
                b.price = cursor.getDouble(cursor.getColumnIndexOrThrow("price"));
                b.timestamp = cursor.getLong(cursor.getColumnIndexOrThrow("timestamp"));
                list.add(b);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return list;
    }

    public void deleteBooking(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("bookings", "id=?", new String[]{String.valueOf(id)});
        db.close();
    }


}
