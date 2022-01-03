package com.example.ksiazka.datebase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.ksiazka.daos.PrzepisDao;
import com.example.ksiazka.daos.UserDao;

@Database(entities = {UserEntity.class, PrzepisEntity.class}, version = 1)
public abstract class DateBase extends RoomDatabase {
    private static final String dbName = "app";
    private static DateBase dateBase;

    public static synchronized DateBase getDateBase(Context context) {
        if (dateBase == null) {
            dateBase = Room.databaseBuilder(context, DateBase.class, dbName)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return dateBase;
    }

    public abstract UserDao userDao();

    public abstract PrzepisDao przepisDao();
}
