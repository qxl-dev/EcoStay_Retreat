package com.ecostay.ui.auth;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.ecostay.R;
import com.ecostay.data.dao.BookingDao;
import com.ecostay.data.model.Booking;
import com.ecostay.util.SessionManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingHistoryActivity extends AppCompatActivity {

    ListView listView;
    BookingDao bookingDao;
    List<Booking> bookingList;
    SimpleAdapter adapter;
    List<Map<String, String>> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_booking_history);

        listView = findViewById(R.id.listBookings);
        bookingDao = new BookingDao(this);

        loadBookings();

        // 🟡 Long press → Confirm delete
        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            Booking selected = bookingList.get(position);

            new AlertDialog.Builder(this)
                    .setTitle("Cancel Booking?")
                    .setMessage("Do you want to remove the booking for \"" + selected.roomTitle + "\"?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        bookingDao.deleteBooking(selected.id);
                        Toast.makeText(this, "Booking canceled.", Toast.LENGTH_SHORT).show();
                        loadBookings(); // refresh list
                    })
                    .setNegativeButton("No", null)
                    .show();

            return true;
        });
    }

    private void loadBookings() {
        int userId = SessionManager.getUserId(this);
        bookingList = bookingDao.getBookingsForUser(userId);
        if (bookingList == null) bookingList = new ArrayList<>();


        data = new ArrayList<>();
        for (Booking b : bookingList) {
            Map<String, String> map = new HashMap<>();
            map.put("title", b.roomTitle);
            map.put("detail", "$" + b.price + " • " +
                    android.text.format.DateFormat.format("MMM dd, yyyy", b.timestamp));
            data.add(map);
        }

        adapter = new SimpleAdapter(
                this,
                data,
                android.R.layout.simple_list_item_2,
                new String[]{"title", "detail"},
                new int[]{android.R.id.text1, android.R.id.text2}
        );

        listView.setAdapter(adapter);
    }
}
