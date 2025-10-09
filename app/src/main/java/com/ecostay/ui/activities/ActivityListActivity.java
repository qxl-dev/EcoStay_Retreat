package com.ecostay.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.ecostay.R;
import com.ecostay.data.dao.ActivityDao;
import com.ecostay.data.model.ActivityItem;
import java.util.List;

public class ActivityListActivity extends AppCompatActivity {
    ListView listView;
    ActivityDao activityDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_list);

        listView = findViewById(R.id.listActivities);
        activityDao = new ActivityDao(this);

        // Insert sample activities (for testing)
        insertSampleActivities();

        List<ActivityItem> activityList = activityDao.getAllActivities();

        // Adapter for Activity list
        ActivityAdapter adapter = new ActivityAdapter(this, activityList);
        listView.setAdapter(adapter);

        // Set item click listener to navigate to ActivityReserveActivity
        listView.setOnItemClickListener((parent, view, position, id) -> {
            ActivityItem selectedActivity = activityList.get(position);
            Toast.makeText(ActivityListActivity.this, "Selected Activity ID: " + selectedActivity.id, Toast.LENGTH_SHORT).show();

            // Pass activity_id to ActivityReserveActivity
            Intent intent = new Intent(ActivityListActivity.this, ActivityReserveActivity.class);
            intent.putExtra("activity_id", selectedActivity.id);  // Pass the activity ID
            startActivity(intent);
        });
    }

    private void insertSampleActivities() {
        // Add some sample activities if the table is empty
        List<ActivityItem> existingActivities = activityDao.getAllActivities();
        if (existingActivities.isEmpty()) {
            long currentTimestamp = System.currentTimeMillis(); // Get current timestamp
            // Insert sample activities with required arguments (title, description, price, timestamp)
            activityDao.addActivity(new ActivityItem("Guided Eco Tour", "Explore the local flora and fauna.", 50.0, currentTimestamp));
            activityDao.addActivity(new ActivityItem("Sustainability Workshop", "Learn how to live sustainably.", 30.0, currentTimestamp));
            activityDao.addActivity(new ActivityItem("Bird-Watching Session", "A relaxing bird-watching session.", 25.0, currentTimestamp));
        }
    }

}
