package com.ecostay.data.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ecostay.data.DatabaseHelper;
import com.ecostay.data.model.ActivityItem;

import java.util.ArrayList;
import java.util.List;

public class ActivityDao {
    private DatabaseHelper dbHelper;

    public ActivityDao(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    // Add new activity
    public long addActivity(ActivityItem activity) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("title", activity.title);
        cv.put("description", activity.description);
        cv.put("price", activity.price);

        long id = db.insert("activities", null, cv);
        db.close();
        return id;
    }

    // Get all activities
    public List<ActivityItem> getAllActivities() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<ActivityItem> activityList = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM activities", null);
        if (cursor.moveToFirst()) {
            do {
                ActivityItem activity = new ActivityItem(
                        cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("title")),
                        cursor.getString(cursor.getColumnIndexOrThrow("description")),
                        cursor.getDouble(cursor.getColumnIndexOrThrow("price"))
                );
                activityList.add(activity);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return activityList;
    }

    // Get activity by ID
    public ActivityItem getActivityById(int activityId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ActivityItem activity = null;

        Cursor cursor = db.rawQuery(
            "SELECT * FROM activities WHERE id = ?",
            new String[]{String.valueOf(activityId)}
        );

        if (cursor.moveToFirst()) {
            activity = new ActivityItem(
                cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                cursor.getString(cursor.getColumnIndexOrThrow("title")),
                cursor.getString(cursor.getColumnIndexOrThrow("description")),
                cursor.getDouble(cursor.getColumnIndexOrThrow("price"))
            );
        }

        cursor.close();
        db.close();
        return activity;
    }
}
