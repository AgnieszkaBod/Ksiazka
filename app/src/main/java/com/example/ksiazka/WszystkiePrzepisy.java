package com.example.ksiazka;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.SearchView;

import com.example.ksiazka.daos.PrzepisDao;
import com.example.ksiazka.datebase.DateBase;

import java.util.List;


public class WszystkiePrzepisy extends AppCompatActivity implements SearchView.OnQueryTextListener {
    protected RecyclerView recyclerView;
    protected PrzepisAdapter przepisAdapter;
    protected SearchView searchEvent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wszystkie_przepisy);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        searchEvent = findViewById(R.id.search);
        searchEvent.setOnQueryTextListener(this);
    }

    DateBase dataBase = DateBase.getDateBase(getApplicationContext());
    final PrzepisDao przepisDao = dataBase.przepisDao();
    List<String> przepisName = przepisDao.showAll();

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }


    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}