package com.example.ksiazka;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ksiazka.daos.PrzepisDao;
import com.example.ksiazka.datebase.DateBase;

import java.util.ArrayList;
import java.util.List;


public class WszystkiePrzepisy extends AppCompatActivity implements SearchView.OnQueryTextListener, PrzepisAdapter.ItemClickListener {

    protected RecyclerView recyclerView;
    protected PrzepisAdapter przepisAdapter;
    protected SearchView searchEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wszystkie_przepisy);
        recyclerView = findViewById(R.id.przepisView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        searchEvent = findViewById(R.id.search);
        searchEvent.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                przepisAdapter.getFilter().filter(newText);
                return true;
            }
        });

        new Thread(() -> {
            DateBase dataBase = DateBase.getDateBase(getApplicationContext());
            final PrzepisDao przepisDao = dataBase.przepisDao();
            List<String> przepisName = przepisDao.showAll();
            przepisAdapter = new PrzepisAdapter(this, przepisName);
            przepisAdapter.setClickListener(this);
            recyclerView.setAdapter(przepisAdapter);
        }).start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<String> przepisName = new ArrayList<>();
        Thread t = new Thread(
                () -> {
                    DateBase dataBase = DateBase.getDateBase(getApplicationContext());
                    final PrzepisDao przepisDao = dataBase.przepisDao();
                    List<String> przepisy = przepisDao.showAll();
                    przepisName.addAll(przepisy);
                }
        );
        t.start();
        try {
            t.join();
            przepisAdapter = new PrzepisAdapter(this, przepisName);
            przepisAdapter.setClickListener(this);
            recyclerView.setAdapter(przepisAdapter);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.e("msg", newText);
        przepisAdapter.getFilter().filter(newText);
        przepisAdapter.notifyDataSetChanged();
        return true;
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(this, PrzepisActivity.class);
        intent.putExtra("nazwa", przepisAdapter.getItem(position));
        startActivity(intent);
    }
}