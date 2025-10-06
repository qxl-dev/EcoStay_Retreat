package com.example.ecostayretreat.booking;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecostayretreat.R;
import com.example.ecostayretreat.booking.adapter.RoomAdapter;
import com.example.ecostayretreat.booking.model.Room;

import java.util.ArrayList;
import java.util.List;

// Implement the ClickListener interface from the adapter
public class RoomActivity extends AppCompatActivity implements RoomAdapter.ClickListener {

    private RecyclerView recyclerViewRooms;
    private RoomAdapter roomAdapter;
    private List<Room> roomList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);

        // 1. Initialize the RecyclerView from the layout
        recyclerViewRooms = findViewById(R.id.recyclerViewRooms);

        // 2. Prepare the data source
        loadRoomData();

        // 3. Create an instance of the adapter, passing 'this' as the listener
        roomAdapter = new RoomAdapter(roomList, this);

        // 4. Set the Layout Manager and Adapter for the RecyclerView
        recyclerViewRooms.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewRooms.setAdapter(roomAdapter);

        // Optional: Set a title for the activity
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Eco-Friendly Rooms");
        }
    }

    /**
     * Creates a dummy list of rooms for display.
     */
    private void loadRoomData() {
        roomList = new ArrayList<>();
        // Note: The image URLs are placeholders.
        roomList.add(new Room("101", "Mountain-View Cabin", "A cozy cabin with a stunning view of the mountains.", 150.00, "url_to_image_1"));
        roomList.add(new Room("102", "Forest Eco-Pod", "A unique, sustainable pod nestled in the forest.", 120.50, "url_to_image_2"));
        roomList.add(new Room("103", "Lakeside Loft", "A modern loft with direct access to the lake.", 175.00, "url_to_image_3"));
        roomList.add(new Room("104", "Sunrise Yurt", "A spacious yurt perfect for watching the sunrise.", 135.00, "url_to_image_4"));
        roomList.add(new Room("105", "Canopy Treehouse", "An adventurous stay high up in the trees.", 200.00, "url_to_image_5"));
    }

    /**
     * This method is called when a room in the list is clicked.
     */
    @Override
    public void onItemClick(Room room) {
        Intent intent = new Intent(this, RoomDetailActivity.class);

        // We will pass the room data in the next step.
        // For now, this just opens the blank detail activity.
        startActivity(intent);
    }
}
