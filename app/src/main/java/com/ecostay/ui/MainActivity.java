package com.ecostay.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.ecostay.R;
import com.ecostay.ui.auth.LoginActivity;
import com.ecostay.ui.auth.ProfileActivity;
import com.ecostay.ui.activities.ActivityListActivity;
import com.ecostay.util.SessionManager;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check if the user is logged in; if not, redirect to LoginActivity
        if (SessionManager.getUserId(this) == 0) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        // Initialize buttons
        Button btnProfile = findViewById(R.id.btnProfile);
        Button btnLogout = findViewById(R.id.btnLogout);
        Button btnActivities = findViewById(R.id.btnActivities); // Button for activities list

        // Navigate to ProfileActivity when the profile button is clicked
        btnProfile.setOnClickListener(v -> startActivity(new Intent(this, ProfileActivity.class)));

        // Handle logout functionality
        btnLogout.setOnClickListener(v -> {
            SessionManager.clear(this);
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });

        // Navigate to ActivityListActivity when the activities button is clicked
        btnActivities.setOnClickListener(v -> startActivity(new Intent(this, ActivityListActivity.class)));
    }
}
