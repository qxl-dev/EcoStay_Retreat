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
            // Handle new fields with null checks
            int travelDatesIndex = cursor.getColumnIndex("travel_dates");
            int ecoInterestsIndex = cursor.getColumnIndex("eco_interests");
            user.travelDates = travelDatesIndex >= 0 ? cursor.getString(travelDatesIndex) : "";
            user.ecoInterests = ecoInterestsIndex >= 0 ? cursor.getString(ecoInterestsIndex) : "";
        }

        cursor.close();
        db.close();
        return user;
    }

    // Get personalized recommendations based on user preferences
    public java.util.List<String> getPersonalizedRecommendations(int userId) {
        java.util.List<String> recommendations = new java.util.ArrayList<>();
        try {
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT preferences, eco_interests FROM users WHERE id = ?", 
                    new String[]{String.valueOf(userId)});
            
            if (cursor.moveToFirst()) {
                String preferences = cursor.getString(cursor.getColumnIndexOrThrow("preferences"));
                int ecoInterestsIndex = cursor.getColumnIndex("eco_interests");
                String ecoInterests = ecoInterestsIndex >= 0 ? cursor.getString(ecoInterestsIndex) : "";
                
                // Generate recommendations based on preferences
                if (preferences != null && preferences.toLowerCase().contains("hiking")) {
                    recommendations.add("🌲 Guided Nature Hikes - Perfect for your hiking interests!");
                }
                if (preferences != null && preferences.toLowerCase().contains("bird")) {
                    recommendations.add("🦅 Bird Watching Sessions - Spot rare species in their habitat!");
                }
                if (ecoInterests != null && ecoInterests.toLowerCase().contains("sustainability")) {
                    recommendations.add("♻️ Sustainability Workshops - Learn eco-friendly practices!");
                }
                if (preferences != null && preferences.toLowerCase().contains("family")) {
                    recommendations.add("👨‍👩‍👧‍👦 Family Eco Tours - Great for family adventures!");
                }
                
                // Default recommendations
                if (recommendations.isEmpty()) {
                    recommendations.add("🌿 Eco-Friendly Activities - Discover our sustainable experiences!");
                    recommendations.add("🏞️ Nature Reserve Tours - Explore pristine wilderness areas!");
                }
            }
            
            cursor.close();
            db.close();
        } catch (Exception e) {
            // Fallback recommendations if there's any error
            recommendations.add("🌿 Eco-Friendly Activities - Discover our sustainable experiences!");
            recommendations.add("🏞️ Nature Reserve Tours - Explore pristine wilderness areas!");
        }
        return recommendations;
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
