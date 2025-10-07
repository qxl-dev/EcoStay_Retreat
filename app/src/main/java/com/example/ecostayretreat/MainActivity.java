package com.example.ecostayretreat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

// CORRECTED IMPORTS
import com.example.ecostayretreat.booking.RoomActivity; // Correctly points to our existing RoomActivity
import com.example.ecostayretreat.activities.ActivityListActivity; // Placeholder
import com.example.ecostayretreat.auth.ProfileActivity; // Placeholder
import com.example.ecostayretreat.info.ResortInfoActivity; // Placeholder

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnBrowseRooms = findViewById(R.id.btnBrowseRooms);
        Button btnBookActivities = findViewById(R.id.btnBookActivities);
        Button btnViewProfile = findViewById(R.id.btnViewProfile);
        Button btnResortInfo = findViewById(R.id.btnResortInfo);

        btnBrowseRooms.setOnClickListener(v -> {
            // THIS IS THE KEY FIX: Changed RoomListActivity.class to RoomActivity.class
            startActivity(new Intent(MainActivity.this, RoomActivity.class));
        });

        btnBookActivities.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ActivityListActivity.class));
        });

        btnViewProfile.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ProfileActivity.class));
        });

        btnResortInfo.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ResortInfoActivity.class));
        });
    }
}
