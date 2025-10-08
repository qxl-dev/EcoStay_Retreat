package com.example.ecostayretreat.booking.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.ecostayretreat.booking.model.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Room.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract RoomDao roomDao();

    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = androidx.room.Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "ecostay_database")
                            .addCallback(sRoomDatabaseCallback) // Add this line to pre-populate data
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    // This callback is used to pre-populate the database with our initial room list.
    private static final RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@androidx.annotation.NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                // This code runs on a background thread when the database is first created.
                RoomDao dao = INSTANCE.roomDao();
                dao.deleteAll(); // Clear any old data

                List<Room> rooms = new ArrayList<>();
                rooms.add(new Room("101", "Mountain-View Cabin", "A cozy cabin with a stunning view of the mountains.", 150.00, "url_to_image_1"));
                rooms.add(new Room("102", "Forest Eco-Pod", "A unique, sustainable pod nestled in the forest.", 120.50, "url_to_image_2"));
                rooms.add(new Room("103", "Lakeside Loft", "A modern loft with direct access to the lake.", 175.00, "url_to_image_3"));
                rooms.add(new Room("104", "Sunrise Yurt", "A spacious yurt perfect for watching the sunrise.", 135.00, "url_to_image_4"));
                rooms.add(new Room("105", "Canopy Treehouse", "An adventurous stay high up in the trees.", 200.00, "url_to_image_5"));

                dao.insertAll(rooms);
            });
        }
    };
}


