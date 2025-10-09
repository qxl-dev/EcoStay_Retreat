package com.ecostay.ui.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.ecostay.R;
import com.ecostay.data.dao.ActivityBookingDao;
import com.ecostay.data.dao.ActivityDao;
import com.ecostay.data.model.ActivityItem;
import com.ecostay.util.SessionManager;

public class ActivityReserveActivity extends AppCompatActivity {
    int activityId;
    ActivityBookingDao activityBookingDao;
    ActivityDao activityDao;
    ActivityItem selectedActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_reserve);

        // Get passed data (activity ID)
        activityId = getIntent().getIntExtra("activity_id", -1);
        if (activityId == -1) {
            Toast.makeText(this, "No Activity ID received!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        activityBookingDao = new ActivityBookingDao(this);
        activityDao = new ActivityDao(this);

        // Load activity details
        loadActivityDetails();

        Button btnBookActivity = findViewById(R.id.btnBookActivity);
        btnBookActivity.setOnClickListener(v -> bookActivity());
    }

    private void loadActivityDetails() {
        // Get activity by ID directly
        selectedActivity = activityDao.getActivityById(activityId);

        if (selectedActivity != null) {
            // Update UI with activity details
            TextView tvActivityTitle = findViewById(R.id.tvActivityTitle);
            TextView tvActivityDescription = findViewById(R.id.tvActivityDescription);
            TextView tvActivityPrice = findViewById(R.id.tvActivityPrice);

            if (tvActivityTitle != null) tvActivityTitle.setText(selectedActivity.title);
            if (tvActivityDescription != null) tvActivityDescription.setText(selectedActivity.description);
            if (tvActivityPrice != null) tvActivityPrice.setText("$" + selectedActivity.price);
        } else {
            Toast.makeText(this, "Activity not found!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void bookActivity() {
        int userId = SessionManager.getUserId(this);

        if (userId == 0) {
            Toast.makeText(this, "Please login to book activities", Toast.LENGTH_SHORT).show();
            return;
        }

        if (selectedActivity == null) {
            Toast.makeText(this, "Activity information not available", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if user already has a booking for this activity
        if (activityBookingDao.hasExistingBooking(userId, activityId)) {
            Toast.makeText(this, "You have already booked this activity!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a more readable date format
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.getDefault());
        String bookingDate = dateFormat.format(new java.util.Date());

        long bookingId = activityBookingDao.addActivityBooking(userId, activityId, bookingDate);

        if (bookingId > 0) {
            Toast.makeText(this, "Activity '" + selectedActivity.title + "' booked successfully!", Toast.LENGTH_LONG).show();
            // Optionally finish the activity or go back
            finish();
        } else {
            Toast.makeText(this, "Booking failed. Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }
}
