package com.ecostay.ui.rooms;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.ecostay.R;
import com.ecostay.data.dao.RoomDao;
import com.ecostay.data.model.Room;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RoomListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RoomAdapter adapter;
    RoomDao roomDao;
    Spinner spinnerFilter;
    Button btnSortPrice, btnSortType, btnShowAll;
    List<Room> allRooms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);

        recyclerView = findViewById(R.id.recyclerRooms);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize filter controls
        spinnerFilter = findViewById(R.id.spinnerFilter);
        btnSortPrice = findViewById(R.id.btnSortPrice);
        btnSortType = findViewById(R.id.btnSortType);
        btnShowAll = findViewById(R.id.btnShowAll);

        try {
            roomDao = new RoomDao(this);
            roomDao.insertSampleRooms(); // Populate on first run

            allRooms = roomDao.getAllRooms();
            setupFilterSpinner();
            setupClickListeners();
            
            // Show all rooms initially
            showAllRooms();
        } catch (Exception e) {
            // Handle any initialization errors
            Toast.makeText(this, "Error loading rooms. Please try again.", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void setupFilterSpinner() {
        List<String> filterOptions = new ArrayList<>();
        filterOptions.add("All Rooms");
        filterOptions.add("Available Only");
        filterOptions.addAll(roomDao.getAllRoomTypes());
        
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, 
                android.R.layout.simple_spinner_item, filterOptions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFilter.setAdapter(adapter);
        
        spinnerFilter.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, android.view.View view, int position, long id) {
                String selectedFilter = (String) parent.getItemAtPosition(position);
                applyFilter(selectedFilter);
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {}
        });
    }

    private void setupClickListeners() {
        btnSortPrice.setOnClickListener(v -> sortByPrice());
        btnSortType.setOnClickListener(v -> sortByType());
        btnShowAll.setOnClickListener(v -> showAllRooms());
    }

    private void applyFilter(String filter) {
        List<Room> filteredRooms;
        
        if (filter.equals("All Rooms")) {
            filteredRooms = new ArrayList<>(allRooms);
        } else if (filter.equals("Available Only")) {
            filteredRooms = roomDao.getAvailableRooms();
        } else {
            filteredRooms = roomDao.getRoomsByType(filter);
        }
        
        updateRecyclerView(filteredRooms);
    }

    private void sortByPrice() {
        List<Room> sortedRooms = new ArrayList<>(allRooms);
        Collections.sort(sortedRooms, new Comparator<Room>() {
            @Override
            public int compare(Room r1, Room r2) {
                return Double.compare(r1.price, r2.price);
            }
        });
        updateRecyclerView(sortedRooms);
        Toast.makeText(this, "Sorted by Price (Low to High)", Toast.LENGTH_SHORT).show();
    }

    private void sortByType() {
        List<Room> sortedRooms = new ArrayList<>(allRooms);
        Collections.sort(sortedRooms, new Comparator<Room>() {
            @Override
            public int compare(Room r1, Room r2) {
                return r1.type.compareTo(r2.type);
            }
        });
        updateRecyclerView(sortedRooms);
        Toast.makeText(this, "Sorted by Type", Toast.LENGTH_SHORT).show();
    }

    private void showAllRooms() {
        updateRecyclerView(allRooms);
        spinnerFilter.setSelection(0); // Reset to "All Rooms"
    }

    private void updateRecyclerView(List<Room> rooms) {
        adapter = new RoomAdapter(rooms, this);
        recyclerView.setAdapter(adapter);
    }
}
