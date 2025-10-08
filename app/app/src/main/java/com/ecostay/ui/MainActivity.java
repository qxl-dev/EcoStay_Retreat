package com.ecostay.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.ecostay.R;
import com.ecostay.ui.auth.LoginActivity;
import com.ecostay.ui.auth.ProfileActivity;
import com.ecostay.ui.rooms.RoomListActivity;   // ✅ add import
import com.ecostay.util.SessionManager;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // If not logged in → go to LoginActivity
        if (SessionManager.getUserId(this) == 0) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        Button btnProfile = findViewById(R.id.btnProfile);
        Button btnLogout = findViewById(R.id.btnLogout);
        Button btnBookRoom = findViewById(R.id.btnBookRoom); // ✅ new button

        btnProfile.setOnClickListener(v ->
                startActivity(new Intent(this, ProfileActivity.class)));

        btnLogout.setOnClickListener(v -> {
            SessionManager.clear(this);
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });

        // ✅ Navigate to room booking list
        btnBookRoom.setOnClickListener(v ->
                startActivity(new Intent(this, RoomListActivity.class)));
    }
}
