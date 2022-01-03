package com.example.ksiazka.daos;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.ksiazka.datebase.PrzepisEntity;

import java.util.List;

@Dao
public interface PrzepisDao {
    @Insert
    void savePrzepis(PrzepisEntity przepisEntity);

    @Query("DELETE FROM przepis WHERE name=(:name) ")
    void delete(String name);

    @Query("SELECT * FROM przepis WHERE name=(:name)")
    PrzepisEntity getByName(String name);

    @Query("SELECT name FROM przepis")
    List<String> showAll();

    @Query("UPDATE przepis SET name=(:name), wykonanie=(:wykonanie), skladnik0=(:skladnik0)," +
            "skladnik1=(:skladnik1), skladnik2=(:skladnik2),skladnik3=(:skladnik3), skladnik4=(:skladnik4), " +
            "skladnik5=(:skladnik5), skladnik6=(:skladnik6) WHERE id =:id")
  void update(Long id, String name, String wykonanie, String skladnik0, String skladnik1,
                         String skladnik2, String skladnik3, String skladnik4, String skladnik5,
                         String skladnik6);
}
