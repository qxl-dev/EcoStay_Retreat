package com.example.ecostayretreat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ecostayretreat.activities.ActivityListActivity;
import com.example.ecostayretreat.auth.ProfileActivity;
import com.example.ecostayretreat.booking.RoomListActivity;
import com.example.ecostayretreat.info.ResortInfoActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnBrowseRooms = findViewById(R.id.btnBrowseRooms);
        Button btnBookActivities = findViewById(R.id.btnBookActivities);
        Button btnViewProfile = findViewById(R.id.btnViewProfile);
        Button btnResortInfo = findViewById(R.id.btnResortInfo);

        // For simplicity, we navigate directly. In a real app, you'd check if the user is logged in.
        // If not logged in, you would redirect to LoginActivity first.

        btnBrowseRooms.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, RoomListActivity.class));
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
