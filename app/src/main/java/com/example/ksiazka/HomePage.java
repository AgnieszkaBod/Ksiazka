package com.example.ksiazka;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ksiazka.daos.PrzepisDao;
import com.example.ksiazka.datebase.DateBase;
import com.example.ksiazka.datebase.PrzepisEntity;

public class HomePage extends AppCompatActivity {

    EditText wykonanie, skladnik0, skladnik1, skladnik2, skladnik3, skladnik4, skladnik5, skladnik6;
    EditText name;
    RatingBar poziom;
    Button dodajPrzepis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        name = findViewById(R.id.name);
        wykonanie = findViewById(R.id.wykonanie);
        skladnik0 = findViewById(R.id.skladnik0);
        skladnik1 = findViewById(R.id.skladnik1);
        skladnik2 = findViewById(R.id.skladnik2);
        skladnik3 = findViewById(R.id.skladnik3);
        skladnik4 = findViewById(R.id.skladnik4);
        skladnik5 = findViewById(R.id.skladnik5);
        skladnik6 = findViewById(R.id.skladnik6);
        dodajPrzepis = findViewById(R.id.zapisz_przepis);
        poziom = findViewById(R.id.rating);

        dodajPrzepis.setOnClickListener(v -> {
            PrzepisEntity przepisEntity = new PrzepisEntity();
            przepisEntity.setName(name.getText().toString());
            przepisEntity.setWykonanie(wykonanie.getText().toString());
            przepisEntity.setSkladnik0(skladnik0.getText().toString());
            przepisEntity.setSkladnik1(skladnik1.getText().toString());
            przepisEntity.setSkladnik2(skladnik2.getText().toString());
            przepisEntity.setSkladnik3(skladnik3.getText().toString());
            przepisEntity.setSkladnik4(skladnik4.getText().toString());
            przepisEntity.setSkladnik5(skladnik5.getText().toString());
            przepisEntity.setSkladnik6(skladnik6.getText().toString());
            przepisEntity.setPoziom(poziom.getRating());

            if (validate(przepisEntity.getName())) {
                DateBase dataBase = DateBase.getDateBase(getApplicationContext());
                final PrzepisDao przepisDao = dataBase.przepisDao();
                new Thread(() -> {
                    String check = przepisDao.check(przepisEntity.getName());
                    if (check == null) {
                        przepisDao.savePrzepis(przepisEntity);
                        runOnUiThread(() -> Toast.makeText(getApplicationContext(),
                                "Dodano przepis!", Toast.LENGTH_SHORT)
                                .show());
                        this.finish();
                    } else {
                        runOnUiThread(() -> Toast.makeText(getApplicationContext(),
                                "Nazwa przepisu musi być unikalna ta nazwa już istnieje!", Toast.LENGTH_SHORT)
                                .show());
                    }
                }).start();
            } else {
                Toast.makeText(getApplicationContext(), "Nazwa przepisu jest pusta!", Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

    private Boolean validate(String name) {
        return !name.isEmpty();
    }
}