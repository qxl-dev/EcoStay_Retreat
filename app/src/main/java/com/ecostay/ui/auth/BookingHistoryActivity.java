package com.ecostay.ui.auth;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.ecostay.R;
import com.ecostay.data.dao.BookingDao;
import com.ecostay.data.dao.ActivityBookingDao;
import com.ecostay.data.model.Booking;
import com.ecostay.util.SessionManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingHistoryActivity extends AppCompatActivity {

    ListView listView;
    BookingDao bookingDao;
    ActivityBookingDao activityBookingDao;
    List<Booking> bookingList;
    SimpleAdapter adapter;
    List<Map<String, String>> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_booking_history);

        listView = findViewById(R.id.listBookings);
        bookingDao = new BookingDao(this);
        activityBookingDao = new ActivityBookingDao(this);

        loadBookings();

        // 🟡 Long press → Confirm delete
        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            Map<String, String> selectedItem = data.get(position);
            String bookingType = selectedItem.get("type");
            String bookingId = selectedItem.get("id");
            String bookingTitle = selectedItem.get("title").substring(2); // Remove emoji prefix

            new AlertDialog.Builder(this)
                    .setTitle("Cancel Booking?")
                    .setMessage("Do you want to remove the booking for \"" + bookingTitle + "\"?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        if ("room".equals(bookingType)) {
                            bookingDao.deleteBooking(Integer.parseInt(bookingId));
                        } else if ("activity".equals(bookingType)) {
                            activityBookingDao.deleteActivityBooking(Integer.parseInt(bookingId));
                        }
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
        
        // Add room bookings
        for (Booking b : bookingList) {
            Map<String, String> map = new HashMap<>();
            map.put("title", "🏨 " + b.roomTitle);
            map.put("detail", "$" + b.price + " • " +
                    android.text.format.DateFormat.format("MMM dd, yyyy", b.timestamp));
            map.put("type", "room");
            map.put("id", String.valueOf(b.id));
            data.add(map);
        }
        
        // Add activity bookings
        List<Map<String, String>> activityBookings = activityBookingDao.getActivityBookingDetails(userId);
        for (Map<String, String> activityBooking : activityBookings) {
            Map<String, String> map = new HashMap<>();
            map.put("title", "🎯 " + activityBooking.get("title"));
            map.put("detail", "$" + activityBooking.get("price") + " • " + activityBooking.get("date"));
            map.put("type", "activity");
            map.put("id", activityBooking.get("id"));
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
