package com.example.ecostayretreat.booking;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.ecostayretreat.booking.model.User;

@Dao
public interface UserDao {

    /**
     * Inserts a new user into the database. If a user with the same email already exists,
     * the insert will be ignored.
     * @param user The user object to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(User user);

    /**
     * Finds a user in the database by their email address.
     * This is used to check credentials during the login process.
     * @param email The email address to search for.
     * @return The User object if found, otherwise null.
     */
    @Query("SELECT * FROM user_table WHERE email = :email LIMIT 1")
    User findByEmail(String email);

    /**
     * Checks if an email already exists in the database.
     * This is useful during registration to prevent duplicate accounts.
     * @param email The email address to check.
     * @return The User object if the email exists, otherwise null.
     */
    @Query("SELECT * FROM user_table WHERE email = :email LIMIT 1")
    User checkUserExists(String email);
}
