package com.ecostay.data.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.ecostay.data.DatabaseHelper;
import com.ecostay.data.model.Room;
import java.util.ArrayList;
import java.util.List;

public class RoomDao {
    private final DatabaseHelper dbHelper;

    public RoomDao(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    // Insert sample rooms (only if empty)
    public void insertSampleRooms() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT COUNT(*) FROM rooms", null);
        if (c.moveToFirst() && c.getInt(0) == 0) {
            insertRoom("Forest View Suite", "Suite",
                    "Panoramic forest views, balcony, and organic linens.",
                    189.0, 1);
            insertRoom("Eco Pod Cabin", "Cabin",
                    "Compact, energy-efficient cabin near hiking trails.",
                    129.0, 1);
            insertRoom("Lakeside Family Room", "Family",
                    "Spacious room with lake access and kids’ play area.",
                    159.0, 1);
        }
        c.close();
        db.close();
    }

    private void insertRoom(String title, String type, String desc, double price, int available) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("title", title);
        cv.put("type", type);
        cv.put("description", desc);
        cv.put("price", price);
        cv.put("available", available);
        db.insert("rooms", null, cv);
        db.close();
    }

    public List<Room> getAllRooms() {
        List<Room> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM rooms", null);
        while (cursor.moveToNext()) {
            Room r = new Room();
            r.id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            r.title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
            r.type = cursor.getString(cursor.getColumnIndexOrThrow("type"));
            r.description = cursor.getString(cursor.getColumnIndexOrThrow("description"));
            r.price = cursor.getDouble(cursor.getColumnIndexOrThrow("price"));
            r.available = cursor.getInt(cursor.getColumnIndexOrThrow("available"));
            list.add(r);
        }
        cursor.close();
        db.close();
        return list;
    }


    public Room getRoomById(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Room r = null;
        Cursor cursor = db.rawQuery("SELECT * FROM rooms WHERE id=?", new String[]{String.valueOf(id)});
        if (cursor.moveToFirst()) {
            r = new Room();
            r.id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            r.title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
            r.type = cursor.getString(cursor.getColumnIndexOrThrow("type"));
            r.description = cursor.getString(cursor.getColumnIndexOrThrow("description"));
            r.price = cursor.getDouble(cursor.getColumnIndexOrThrow("price"));
            r.available = cursor.getInt(cursor.getColumnIndexOrThrow("available"));
        }
        cursor.close();
        db.close();
        return r;
    }

}
