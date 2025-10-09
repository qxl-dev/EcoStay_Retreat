package com.ecostay.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.ecostay.R;
import com.ecostay.ui.auth.LoginActivity;
import com.ecostay.ui.auth.ProfileActivity;
import com.ecostay.ui.auth.BookingHistoryActivity;
import com.ecostay.ui.activities.ActivityListActivity;
import com.ecostay.ui.rooms.RoomListActivity;
import com.ecostay.ui.info.InfoActivity;
import com.ecostay.util.SessionManager;
import com.ecostay.util.NotificationHelper;
import com.ecostay.data.dao.UserDao;

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

        // Initialize UI components
        initializeViews();
        setupClickListeners();
        updateWelcomeMessage();
        loadPersonalizedRecommendations();
    }

    private void initializeViews() {
        // Initialize buttons
        Button btnProfile = findViewById(R.id.btnProfile);
        Button btnLogout = findViewById(R.id.btnLogout);
        Button btnActivities = findViewById(R.id.btnActivities);
        Button btnBookRoom = findViewById(R.id.btnBookRoom);
        Button btnInfo = findViewById(R.id.btnInfo);
        Button btnBookingHistory = findViewById(R.id.btnBookingHistory);
        
        // Initialize welcome text
        TextView tvWelcomeUser = findViewById(R.id.tvWelcomeUser);
    }

    private void setupClickListeners() {
        // Navigate to ProfileActivity when the profile button is clicked
        findViewById(R.id.btnProfile).setOnClickListener(v -> 
            startActivity(new Intent(this, ProfileActivity.class)));

        // Navigate to BookingHistoryActivity when the booking history button is clicked
        findViewById(R.id.btnBookingHistory).setOnClickListener(v -> 
            startActivity(new Intent(this, BookingHistoryActivity.class)));

        // Handle logout functionality
        findViewById(R.id.btnLogout).setOnClickListener(v -> {
            SessionManager.clear(this);
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });

        // Navigate to ActivityListActivity when the activities button is clicked
        findViewById(R.id.btnActivities).setOnClickListener(v -> 
            startActivity(new Intent(this, ActivityListActivity.class)));

        // Navigate to RoomListActivity when the Book Room button is clicked
        findViewById(R.id.btnBookRoom).setOnClickListener(v -> 
            startActivity(new Intent(this, RoomListActivity.class)));

        // Navigate to InfoActivity when the info button is clicked
        findViewById(R.id.btnInfo).setOnClickListener(v -> 
            startActivity(new Intent(this, InfoActivity.class)));

        // Demo notification for eco events (in a real app, this would be triggered by events)
        findViewById(R.id.btnInfo).setOnLongClickListener(v -> {
            NotificationHelper.showEcoEventNotification(this, 
                "🌿 Earth Day Special Event", 
                "Join us for our Earth Day celebration! 20% off all eco-tours and activities. Book now!");
            return true;
        });
    }

    private void updateWelcomeMessage() {
        try {
            TextView tvWelcomeUser = findViewById(R.id.tvWelcomeUser);
            if (tvWelcomeUser != null) {
                String userName = SessionManager.getUserName(this);
                if (userName != null && !userName.isEmpty()) {
                    tvWelcomeUser.setText("Welcome back, " + userName + "!");
                } else {
                    tvWelcomeUser.setText("Welcome back!");
                }
            }
        } catch (Exception e) {
            // Fallback if there's any error
        }
    }

    private void loadPersonalizedRecommendations() {
        try {
            TextView tvRecommendations = findViewById(R.id.tvRecommendations);
            UserDao userDao = new UserDao(this);
            int userId = SessionManager.getUserId(this);
            
            if (userId > 0) {
                java.util.List<String> recommendations = userDao.getPersonalizedRecommendations(userId);
                StringBuilder recommendationsText = new StringBuilder();
                
                for (String recommendation : recommendations) {
                    recommendationsText.append("• ").append(recommendation).append("\n");
                }
                
                if (recommendationsText.length() > 0) {
                    tvRecommendations.setText(recommendationsText.toString().trim());
                } else {
                    tvRecommendations.setText("🌿 Discover our eco-friendly activities and nature experiences!");
                }
            } else {
                tvRecommendations.setText("🌿 Discover our eco-friendly activities and nature experiences!");
            }
        } catch (Exception e) {
            // Fallback if there's any error loading recommendations
            TextView tvRecommendations = findViewById(R.id.tvRecommendations);
            if (tvRecommendations != null) {
                tvRecommendations.setText("🌿 Discover our eco-friendly activities and nature experiences!");
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Update welcome message in case user info changed
        updateWelcomeMessage();
        // Reload recommendations in case user preferences changed
        loadPersonalizedRecommendations();
    }
}
