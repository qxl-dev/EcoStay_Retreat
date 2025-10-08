package com.ecostay.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.ecostay.R;
import com.ecostay.data.DatabaseHelper;
import com.ecostay.util.SessionManager;
import com.ecostay.ui.auth.LoginActivity;
import com.ecostay.ui.auth.BookingHistoryActivity;   // ✅ NEW import

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;

public class ProfileActivity extends AppCompatActivity {
    EditText etName, etEmail, etPreferences;
    Button btnUpdate, btnLogout, btnBookings;  // ✅ Added new button
    DatabaseHelper dbHelper;
    int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPreferences = findViewById(R.id.etPreferences);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnLogout = findViewById(R.id.btnLogout);
        btnBookings = findViewById(R.id.btnBookings); // ✅ Initialize new button

        dbHelper = new DatabaseHelper(this);
        userId = SessionManager.getUserId(this);

        if (userId == 0) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        loadUser();

        btnUpdate.setOnClickListener(v -> updateUser());

        btnLogout.setOnClickListener(v -> {
            SessionManager.clear(this);
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });

        // ✅ When clicked, show the user's booking history
        btnBookings.setOnClickListener(v -> {
            startActivity(new Intent(this, BookingHistoryActivity.class));
        });
    }

    private void loadUser() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE id=?", new String[]{String.valueOf(userId)});

        if (cursor.moveToFirst()) {
            etName.setText(cursor.getString(cursor.getColumnIndexOrThrow("name")));
            etEmail.setText(cursor.getString(cursor.getColumnIndexOrThrow("email")));
            etPreferences.setText(cursor.getString(cursor.getColumnIndexOrThrow("preferences")));
        }

        cursor.close();
        db.close();
    }

    private void updateUser() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", etName.getText().toString().trim());
        cv.put("email", etEmail.getText().toString().trim());
        cv.put("preferences", etPreferences.getText().toString().trim());

        int rows = db.update("users", cv, "id=?", new String[]{String.valueOf(userId)});
        db.close();

        if (rows > 0)
            Toast.makeText(this, "Profile updated successfully!", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Update failed.", Toast.LENGTH_SHORT).show();
    }
}
