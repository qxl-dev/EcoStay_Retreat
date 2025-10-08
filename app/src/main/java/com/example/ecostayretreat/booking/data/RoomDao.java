package com.example.ecostayretreat.booking.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.ecostayretreat.booking.model.Room;

import java.util.List;

@Dao // Tells Room that this is a Data Access Object
public interface RoomDao {

    /**
     * Gets all rooms from the database.
     * The returned list is wrapped in LiveData to be observable.
     * @return A list of all rooms.
     */
    @Query("SELECT * FROM rooms ORDER BY name ASC")
    List<Room> getAllRooms();

    /**
     * Inserts a list of rooms into the database. If a room with the same
     * primary key already exists, it will be replaced.
     * @param rooms The list of rooms to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Room> rooms);

    /**
     * Deletes all rooms from the database.
     */
    @Query("DELETE FROM rooms")
    void deleteAll();
}
