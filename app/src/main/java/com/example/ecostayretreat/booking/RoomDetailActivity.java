package com.example.ecostayretreat.booking;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ecostayretreat.R;
import com.example.ecostayretreat.booking.model.Room;

import java.util.Locale;

public class RoomDetailActivity extends AppCompatActivity {

    private ImageView ivRoomDetailImage;
    private TextView tvRoomDetailName, tvRoomDetailPrice, tvRoomDetailDescription;
    private Button btnBookNow;
    private Room room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // This links to the activity_room_detail.xml layout file
        setContentView(R.layout.activity_room_detail);

        // Find all the views from the layout
        ivRoomDetailImage = findViewById(R.id.ivRoomDetailImage);
        tvRoomDetailName = findViewById(R.id.tvRoomDetailName);
        tvRoomDetailPrice = findViewById(R.id.tvRoomDetailPrice);
        tvRoomDetailDescription = findViewById(R.id.tvRoomDetailDescription);
        btnBookNow = findViewById(R.id.btnBookNow);

        // Get the Room object that was passed from RoomActivity
        // This is the modern, safe way to get a Serializable extra

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            room = getIntent().getSerializableExtra("ROOM_EXTRA", Room.class);
        } else {
            // The old way for older Android versions
            @SuppressWarnings("deprecation")
            Room fetchedRoom = (Room) getIntent().getSerializableExtra("ROOM_EXTRA");
            room = fetchedRoom;
        }


        // Check if the room object is not null before using it
        if (room != null) {
            populateUI();
        } else {
            // If the room is null, show an error and finish the activity
            Toast.makeText(this, "Error: Room data not found.", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private void populateUI() {
        // Set the activity title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(room.getName());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Populate the views with room data
        tvRoomDetailName.setText(room.getName());
        tvRoomDetailDescription.setText(room.getDescription());
        tvRoomDetailPrice.setText(String.format(Locale.getDefault(), "$%.2f / night", room.getPrice()));

        // In a real app, you would load the image from a URL using a library like Glide or Picasso
        // For now, we'll just set a placeholder.
        ivRoomDetailImage.setImageResource(R.drawable.ic_launcher_background); // Placeholder image

        btnBookNow.setOnClickListener(v -> {
            // Handle the booking action
            Toast.makeText(this, "Booking for " + room.getName() + " is not implemented yet.", Toast.LENGTH_SHORT).show();
        });
    }

    // Handle the back button press in the action bar
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
