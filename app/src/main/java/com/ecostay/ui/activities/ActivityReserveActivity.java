package com.ecostay.ui.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.ecostay.R;
import com.ecostay.data.dao.ActivityBookingDao;
import com.ecostay.util.SessionManager;

public class ActivityReserveActivity extends AppCompatActivity {
    int activityId;
    ActivityBookingDao activityBookingDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_reserve);

        // Get passed data (activity ID)
        activityId = getIntent().getIntExtra("activity_id", -1);  // Check if activity ID is passed
        if (activityId == -1) {
            Toast.makeText(this, "No Activity ID received!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        activityBookingDao = new ActivityBookingDao(this);

        Button btnBookActivity = findViewById(R.id.btnBookActivity);
        btnBookActivity.setOnClickListener(v -> bookActivity());
    }

    private void bookActivity() {
        int userId = SessionManager.getUserId(this);

        if (userId == 0) {
            Toast.makeText(this, "Please login to book activities", Toast.LENGTH_SHORT).show();
            return;
        }

        long bookingDate = System.currentTimeMillis(); // Use current timestamp
        long bookingId = activityBookingDao.addActivityBooking(userId, activityId, bookingDate);

        if (bookingId > 0) {
            Toast.makeText(this, "Activity booked successfully!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Booking failed. Try again later.", Toast.LENGTH_SHORT).show();
        }
    }
}
