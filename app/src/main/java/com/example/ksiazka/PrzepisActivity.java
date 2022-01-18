package com.example.ksiazka;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.ksiazka.daos.PrzepisDao;
import com.example.ksiazka.datebase.DateBase;
import com.example.ksiazka.datebase.PrzepisEntity;

public class PrzepisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_przepis);
        TextView tytul = findViewById(R.id.przepis_nazwa);
        ImageView zdjecie = findViewById(R.id.przepis_zdjecie);
        TextView skladniki = findViewById(R.id.przepis_skladniki);
        TextView wykonanie = findViewById(R.id.przepis_wykonanie);
        RatingBar poziom = findViewById(R.id.ratingBar2);
        Button edycja = findViewById(R.id.przepis_edycja);
        Button usun = findViewById(R.id.przepis_usun);
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            String nazwa = extras.getString("nazwa");
            tytul.setText(nazwa);

            new Thread(() -> {
                DateBase dataBase = DateBase.getDateBase(getApplicationContext());
                final PrzepisDao przepisDao = dataBase.przepisDao();
                PrzepisEntity przepis = przepisDao.getByName(nazwa);

                String sklad = przepis.getSkladnik0() + przepis.getSkladnik1() + przepis.getSkladnik2() + przepis.getSkladnik3() + przepis.getSkladnik4() + przepis.getSkladnik5() + przepis.getSkladnik6();
                skladniki.setText(sklad);
                wykonanie.setText(przepis.getWykonanie());
                poziom.setRating(przepis.getPoziom());
            }).start();

            usun.setOnClickListener(v -> new Thread(() -> {
                DateBase dataBase = DateBase.getDateBase(getApplicationContext());
                final PrzepisDao przepisDao = dataBase.przepisDao();
                przepisDao.delete(nazwa);
                this.finish();
            }).start());
        }
    }

}