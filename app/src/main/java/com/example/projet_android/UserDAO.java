package com.example.projet_android;

import android.os.Bundle;

import androidx.annotation.RequiresPermission;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Dao;

import java.util.ArrayList;
import java.util.List;
@Dao
public interface UserDAO {

    @Query("select * from users where username =(:username)")
    User retrieveUserInfo(String username);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);
}
