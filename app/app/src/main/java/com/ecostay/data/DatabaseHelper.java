package com.ecostay.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "ecostay.db";
    private static final int DB_VERSION = 2; // ✅ bump version to recreate DB

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "email TEXT UNIQUE, " +
                "password TEXT, " +
                "preferences TEXT)");

        db.execSQL("CREATE TABLE IF NOT EXISTS rooms (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT, " +
                "type TEXT, " +
                "description TEXT, " +
                "price REAL, " +
                "available INTEGER)");

        // ✅ UPDATED bookings table — matches Booking.java + BookingDao.java
        db.execSQL("CREATE TABLE IF NOT EXISTS bookings (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "user_id INTEGER, " +
                "room_id INTEGER, " +
                "room_title TEXT, " +          // added column
                "price REAL, " +               // renamed total_price → price
                "timestamp INTEGER)");         // ✅ added for date tracking

        db.execSQL("CREATE TABLE IF NOT EXISTS activities (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT, " +
                "description TEXT, " +
                "price REAL)");

        db.execSQL("CREATE TABLE IF NOT EXISTS activity_bookings (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "user_id INTEGER, " +
                "activity_id INTEGER, " +
                "date TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS activity_bookings");
        db.execSQL("DROP TABLE IF EXISTS activities");
        db.execSQL("DROP TABLE IF EXISTS bookings");
        db.execSQL("DROP TABLE IF EXISTS rooms");
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }
}
