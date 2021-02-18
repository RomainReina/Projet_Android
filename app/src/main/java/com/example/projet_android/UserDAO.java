package com.example.projet_android;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Dao;

import java.util.List;
@Dao
public interface UserDAO {

    @Query("select steps from User")
    int retrieveInfo();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<User> persons);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User person);
    //Delete
    @Delete
    void delete(User person);
}
