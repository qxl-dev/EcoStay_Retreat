package com.ecostay.ui.rooms;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.ecostay.R;
import com.ecostay.data.DatabaseHelper;
import com.ecostay.data.model.Room;
import com.ecostay.data.dao.RoomDao;
import com.ecostay.data.dao.BookingDao;
import com.ecostay.data.model.Booking;
import com.ecostay.util.SessionManager;

public class RoomDetailActivity extends AppCompatActivity {

    TextView tvTitle, tvType, tvDesc, tvPrice;
    Button btnBookNow;
    RoomDao roomDao;
    BookingDao bookingDao;
    int roomId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_detail);

        tvTitle = findViewById(R.id.tvTitle);
        tvType = findViewById(R.id.tvType);
        tvDesc = findViewById(R.id.tvDescription);
        tvPrice = findViewById(R.id.tvPrice);
        btnBookNow = findViewById(R.id.btnBookNow);

        roomDao = new RoomDao(this);
        bookingDao = new BookingDao(this);

        // Get the clicked room’s ID
        roomId = getIntent().getIntExtra("room_id", -1);
        if (roomId == -1) {
            Toast.makeText(this, "Room not found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        Room room = roomDao.getRoomById(roomId);
        if (room != null) {
            tvTitle.setText(room.title);
            tvType.setText(room.type);
            tvDesc.setText(room.description);
            tvPrice.setText("$" + room.price + "/night");
        }

        btnBookNow.setOnClickListener(v -> {
            int userId = SessionManager.getUserId(this);
            if (userId == 0) {
                Toast.makeText(this, "Please login first", Toast.LENGTH_SHORT).show();
                return;
            }
            Booking booking = new Booking(userId, roomId, room.title, room.price);
            long id = bookingDao.addBooking(booking);
            if (id > 0)
                Toast.makeText(this, "Room booked successfully!", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Booking failed!", Toast.LENGTH_SHORT).show();
        });
    }
}
