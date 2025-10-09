package com.ecostay.ui.rooms;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.ecostay.R;
import com.ecostay.data.dao.RoomDao;
import com.ecostay.data.model.Room;
import java.util.List;

public class RoomListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RoomAdapter adapter;
    RoomDao roomDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);

        recyclerView = findViewById(R.id.recyclerRooms);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        roomDao = new RoomDao(this);
        roomDao.insertSampleRooms(); // Populate on first run

        List<Room> rooms = roomDao.getAllRooms();
        adapter = new RoomAdapter(rooms, this);
        recyclerView.setAdapter(adapter);
    }
}
