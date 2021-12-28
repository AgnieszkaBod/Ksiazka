package com.example.ksiazka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    Button dodaj, show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        dodaj = findViewById(R.id.dodaj_przepis_menu);

        show = findViewById(R.id.pokaz_przepisy_menu);

        dodaj.setOnClickListener(v -> {
            startActivity(new Intent(Menu.this, HomePage.class));
        });

        show.setOnClickListener(v -> {
            startActivity(new Intent(Menu.this, WszystkiePrzepisy.class));
        });

    }
}