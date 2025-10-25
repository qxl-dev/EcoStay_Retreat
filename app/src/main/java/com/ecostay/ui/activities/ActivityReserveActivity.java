package com.ecostay.ui.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
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
    boolean isBooked = false;
    String bookingDate = "";

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
        checkBookingStatus();

        Button btnBookActivity = findViewById(R.id.btnBookActivity);
        Button btnCancelBooking = findViewById(R.id.btnCancelBooking);
        
        btnBookActivity.setOnClickListener(v -> bookActivity());
        btnCancelBooking.setOnClickListener(v -> cancelBooking());
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

    private void checkBookingStatus() {
        int userId = SessionManager.getUserId(this);
        if (userId > 0) {
            isBooked = activityBookingDao.hasExistingBooking(userId, activityId);
            if (isBooked) {
                // Get booking date
                bookingDate = activityBookingDao.getBookingDate(userId, activityId);
                updateUIForBookedStatus();
            }
        }
    }

    private void updateUIForBookedStatus() {
        LinearLayout bookingStatusCard = findViewById(R.id.bookingStatusCard);
        Button btnBookActivity = findViewById(R.id.btnBookActivity);
        TextView tvBookingDate = findViewById(R.id.tvBookingDate);

        if (isBooked) {
            bookingStatusCard.setVisibility(View.VISIBLE);
            btnBookActivity.setVisibility(View.GONE);
            tvBookingDate.setText("Booking Date: " + bookingDate);
        } else {
            bookingStatusCard.setVisibility(View.GONE);
            btnBookActivity.setVisibility(View.VISIBLE);
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
            isBooked = true;
            this.bookingDate = bookingDate;
            updateUIForBookedStatus();
        } else {
            Toast.makeText(this, "Booking failed. Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }

    private void cancelBooking() {
        int userId = SessionManager.getUserId(this);
        
        if (userId == 0) {
            Toast.makeText(this, "Please login to cancel bookings", Toast.LENGTH_SHORT).show();
            return;
        }

        if (selectedActivity == null) {
            Toast.makeText(this, "Activity information not available", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean cancelled = activityBookingDao.cancelBooking(userId, activityId);
        
        if (cancelled) {
            Toast.makeText(this, "Booking for '" + selectedActivity.title + "' cancelled successfully!", Toast.LENGTH_LONG).show();
            isBooked = false;
            bookingDate = "";
            updateUIForBookedStatus();
        } else {
            Toast.makeText(this, "Failed to cancel booking. Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }
}
