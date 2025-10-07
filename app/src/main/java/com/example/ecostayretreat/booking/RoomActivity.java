package com.example.ecostayretreat.booking;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecostayretreat.R;
import com.example.ecostayretreat.booking.adapter.RoomAdapter;
import com.example.ecostayretreat.booking.data.AppDatabase;
import com.example.ecostayretreat.booking.model.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomActivity extends AppCompatActivity implements RoomAdapter.ClickListener {

    private RecyclerView recyclerViewRooms;
    private RoomAdapter roomAdapter;
    private List<Room> roomList;
    private ProgressBar progressBar;
    private TextView tvEmptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);

        // 1. Initialize Views
        recyclerViewRooms = findViewById(R.id.recyclerViewRooms);
        progressBar = findViewById(R.id.progressBar); // We will add this to the layout
        tvEmptyView = findViewById(R.id.tvEmptyView);   // We will add this too

        // 2. Initialize the list and adapter
        roomList = new ArrayList<>();
        roomAdapter = new RoomAdapter(roomList, this);

        // 3. Setup RecyclerView
        recyclerViewRooms.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewRooms.setAdapter(roomAdapter);

        // 4. Load data from the database
        loadRoomsFromDatabase();

        // Optional: Set a title for the activity
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Eco-Friendly Rooms");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * Fetches the list of rooms from the Room database on a background thread.
     */
    private void loadRoomsFromDatabase() {
        // Show progress bar while loading
        progressBar.setVisibility(View.VISIBLE);
        recyclerViewRooms.setVisibility(View.GONE);
        tvEmptyView.setVisibility(View.GONE);

        // Get a handler that can update the main thread
        Handler handler = new Handler(Looper.getMainLooper());

        // Run the database query on a background thread
        AppDatabase.databaseWriteExecutor.execute(() -> {
            // Background work: fetch rooms
            List<Room> roomsFromDb = AppDatabase.getDatabase(getApplicationContext())
                    .roomDao()
                    .getAllRooms();

            // Post the result back to the main thread
            handler.post(() -> {
                // UI work: update the list and adapter
                progressBar.setVisibility(View.GONE);
                if (roomsFromDb != null && !roomsFromDb.isEmpty()) {
                    roomList.clear();
                    roomList.addAll(roomsFromDb);
                    roomAdapter.notifyDataSetChanged();
                    recyclerViewRooms.setVisibility(View.VISIBLE);
                } else {
                    // Show an empty state message if no rooms are found
                    tvEmptyView.setVisibility(View.VISIBLE);
                }
            });
        });
    }

    /**
     * This method is called when a room in the list is clicked.
     */
    @Override
    public void onItemClick(Room room) {
        Intent intent = new Intent(this, RoomDetailActivity.class);
        intent.putExtra("ROOM_EXTRA", room);
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
