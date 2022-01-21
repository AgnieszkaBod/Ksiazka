package com.example.ksiazka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ksiazka.daos.PrzepisDao;
import com.example.ksiazka.datebase.DateBase;
import com.example.ksiazka.datebase.PrzepisEntity;

public class EdytujActivity extends AppCompatActivity {
    EditText wykonanie, skladnik0, skladnik1, skladnik2, skladnik3, skladnik4, skladnik5, skladnik6;
    RatingBar poziom;
    Button zapiszPrzepis, show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edytuj);
        Bundle extras = getIntent().getExtras();
        DateBase dataBase = DateBase.getDateBase(getApplicationContext());
        final PrzepisDao przepisDao = dataBase.przepisDao();

        String nazwa = extras.getString("NazwaPrzepisu");
        TextView edycjaNazwa = findViewById(R.id.edycjaNazwa);
        edycjaNazwa.setText(nazwa);

        wykonanie = findViewById(R.id.wykonanie);
        skladnik0 = findViewById(R.id.skladnik0);
        skladnik1 = findViewById(R.id.skladnik1);
        skladnik2 = findViewById(R.id.skladnik2);
        skladnik3 = findViewById(R.id.skladnik3);
        skladnik4 = findViewById(R.id.skladnik4);
        skladnik5 = findViewById(R.id.skladnik5);
        skladnik6 = findViewById(R.id.skladnik6);
        zapiszPrzepis = findViewById(R.id.zapisz_przepis);
        poziom = findViewById(R.id.rating);

        new Thread(() -> {
            PrzepisEntity przepisEntity = przepisDao.getByName(nazwa);
            wykonanie.setText(przepisEntity.getWykonanie());
            skladnik0.setText(przepisEntity.getSkladnik0());
            skladnik1.setText(przepisEntity.getSkladnik1());
            skladnik2.setText(przepisEntity.getSkladnik2());
            skladnik3.setText(przepisEntity.getSkladnik3());
            skladnik4.setText(przepisEntity.getSkladnik4());
            skladnik5.setText(przepisEntity.getSkladnik5());
            skladnik6.setText(przepisEntity.getSkladnik6());
            poziom.setRating(przepisEntity.getPoziom());
        }).start();


        zapiszPrzepis.setOnClickListener(v -> {
            PrzepisEntity przepisEntitytoUpdate = new PrzepisEntity();
            przepisEntitytoUpdate.setWykonanie(wykonanie.getText().toString());
            przepisEntitytoUpdate.setSkladnik0(skladnik0.getText().toString());
            przepisEntitytoUpdate.setSkladnik1(skladnik1.getText().toString());
            przepisEntitytoUpdate.setSkladnik2(skladnik2.getText().toString());
            przepisEntitytoUpdate.setSkladnik3(skladnik3.getText().toString());
            przepisEntitytoUpdate.setSkladnik4(skladnik4.getText().toString());
            przepisEntitytoUpdate.setSkladnik5(skladnik5.getText().toString());
            przepisEntitytoUpdate.setSkladnik6(skladnik6.getText().toString());
            przepisEntitytoUpdate.setPoziom(poziom.getRating());

            new Thread(() -> {
                przepisDao.update(nazwa, przepisEntitytoUpdate.getWykonanie(), przepisEntitytoUpdate.getSkladnik0(),
                        przepisEntitytoUpdate.getSkladnik1(), przepisEntitytoUpdate.getSkladnik2(), przepisEntitytoUpdate.getSkladnik3(),
                        przepisEntitytoUpdate.getSkladnik4(), przepisEntitytoUpdate.getSkladnik5(), przepisEntitytoUpdate.getSkladnik6(),
                        przepisEntitytoUpdate.getPoziom());
                runOnUiThread(() -> Toast.makeText(getApplicationContext(),
                        "Zaaktualizowano przepis!", Toast.LENGTH_SHORT)
                        .show());
                this.finish();
            }).start();

        });
    }

}