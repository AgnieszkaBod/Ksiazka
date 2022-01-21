package com.example.ksiazka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
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
        wykonanie = findViewById(R.id.wykonanie);
        skladnik0 = findViewById(R.id.skladnik0);
        skladnik1 = findViewById(R.id.skladnik1);
        skladnik2 = findViewById(R.id.skladnik2);
        skladnik3 = findViewById(R.id.skladnik3);
        skladnik4 = findViewById(R.id.skladnik4);
        skladnik5 = findViewById(R.id.skladnik5);
        skladnik6 = findViewById(R.id.skladnik6);
        zapiszPrzepis = findViewById(R.id.zapisz_przepis);
        show = findViewById(R.id.show);
        poziom = findViewById(R.id.rating);


        zapiszPrzepis.setOnClickListener(v -> {
            PrzepisEntity przepisEntity = new PrzepisEntity();
            przepisEntity.setWykonanie(wykonanie.getText().toString());
            przepisEntity.setSkladnik0(skladnik0.getText().toString());
            przepisEntity.setSkladnik1(skladnik1.getText().toString());
            przepisEntity.setSkladnik2(skladnik2.getText().toString());
            przepisEntity.setSkladnik3(skladnik3.getText().toString());
            przepisEntity.setSkladnik4(skladnik4.getText().toString());
            przepisEntity.setSkladnik5(skladnik5.getText().toString());
            przepisEntity.setSkladnik6(skladnik6.getText().toString());
            przepisEntity.setPoziom(poziom.getRating());

            DateBase dataBase = DateBase.getDateBase(getApplicationContext());
            final PrzepisDao przepisDao = dataBase.przepisDao();
            new Thread(() -> {
                przepisDao.update(przepisEntity.getId(), przepisEntity.getWykonanie(), przepisEntity.getSkladnik0(),
                        przepisEntity.getSkladnik1(), przepisEntity.getSkladnik2(), przepisEntity.getSkladnik3(),
                        przepisEntity.getSkladnik4(), przepisEntity.getSkladnik5(), przepisEntity.getSkladnik6(), przepisEntity.getPoziom());
                runOnUiThread(() -> Toast.makeText(getApplicationContext(),
                        "Zaaktualizowano przepis!", Toast.LENGTH_SHORT)
                        .show());

            }).start();

        });
        show.setOnClickListener(v -> {
            startActivity(new Intent(EdytujActivity.this, WszystkiePrzepisy.class));
        });
    }

}