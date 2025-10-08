package com.ecostay.data.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.ecostay.data.DatabaseHelper;
import com.ecostay.data.model.User;

public class UserDao {
    private final DatabaseHelper dbHelper;

    public UserDao(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    // Insert new user
    public boolean registerUser(User user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", user.name);
        cv.put("email", user.email);
        cv.put("password", user.password);
        cv.put("preferences", user.preferences);

        long result = db.insert("users", null, cv);
        db.close();
        return result != -1;
    }

    // Login check
    public User loginUser(String email, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM users WHERE email=? AND password=?",
                new String[]{email, password}
        );

        User user = null;
        if (cursor.moveToFirst()) {
            user = new User();
            user.id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            user.name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            user.email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
            user.preferences = cursor.getString(cursor.getColumnIndexOrThrow("preferences"));
        }

        cursor.close();
        db.close();
        return user;
    }

    // Check if email already exists
    public boolean isEmailExists(String email) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT id FROM users WHERE email=?", new String[]{email});
        boolean exists = c.moveToFirst();
        c.close();
        db.close();
        return exists;
    }
}
