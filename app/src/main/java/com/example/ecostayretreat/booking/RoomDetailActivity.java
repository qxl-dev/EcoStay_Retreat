package com.example.ecostayretreat.booking;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ecostayretreat.R;
import com.example.ecostayretreat.booking.model.Room;

import java.io.Serializable;
import java.util.Locale;

public class RoomDetailActivity extends AppCompatActivity {

    private ImageView ivRoomDetailImage;
    private TextView tvRoomDetailName;
    private TextView tvRoomDetailDescription;
    private TextView tvRoomDetailPrice;
    private Button btnBookNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_detail);

        // Initialize views
        ivRoomDetailImage = findViewById(R.id.ivRoomDetailImage);
        tvRoomDetailName = findViewById(R.id.tvRoomDetailName);
        tvRoomDetailDescription = findViewById(R.id.tvRoomDetailDescription);
        tvRoomDetailPrice = findViewById(R.id.tvRoomDetailPrice);
        btnBookNow = findViewById(R.id.btnBookNow);

        // Get the Room object from the intent
        Room room = getSerializable(getIntent(), "ROOM_EXTRA", Room.class);


        if (room != null) {
            // Populate the views with room data
            tvRoomDetailName.setText(room.getName());
            tvRoomDetailDescription.setText(room.getDescription());
            tvRoomDetailPrice.setText(String.format(Locale.getDefault(), "$%.2f / night", room.getPrice()));

            // TODO: Load the image using a library like Glide or Picasso

            // Set a title for the activity
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(room.getName());
                getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Adds a back button
            }

            // Handle the "Book Now" button click
            btnBookNow.setOnClickListener(v -> {
                Toast.makeText(this, "Booking feature coming soon for " + room.getName(), Toast.LENGTH_SHORT).show();
            });
        }
    }

    // Helper method to get serializable extra with API level compatibility
    public static <T extends Serializable> T getSerializable(Intent intent, String key, Class<T> clazz) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            return intent.getSerializableExtra(key, clazz);
        } else {
            // This cast is safe but deprecated.
            @SuppressWarnings("deprecation")
            T t = (T) intent.getSerializableExtra(key);
            return t;
        }
    }

    // Handle the back button in the action bar
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
