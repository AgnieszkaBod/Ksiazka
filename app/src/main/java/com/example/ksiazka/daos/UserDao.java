package com.example.ksiazka.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.ksiazka.datebase.UserEntity;

@Dao
public interface UserDao {

    @Insert
    void registerUser(UserEntity userEntity);

    @Query("SELECT * FROM users WHERE user_name=(:userName) and password=(:password)")
    UserEntity login(String userName, String password);

    @Query("SELECT user_name FROM users WHERE user_name=(:userName)")
    String check(String userName);
}
