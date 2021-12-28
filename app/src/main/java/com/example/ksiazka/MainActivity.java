package com.example.ksiazka;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ksiazka.daos.UserDao;
import com.example.ksiazka.datebase.DateBase;
import com.example.ksiazka.datebase.UserEntity;

public class MainActivity extends AppCompatActivity {

    EditText userName, password;
    Button register, login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        register = findViewById(R.id.goto_register_button);
        login = findViewById(R.id.login_button);

        login.setOnClickListener(v -> {
            String userNameText = userName.getText().toString();
            String passwordText = password.getText().toString();
            if (userNameText.isEmpty() || passwordText.isEmpty()) {
                Toast.makeText(getApplicationContext(),
                        "Fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                DateBase dateBase = DateBase.getDateBase(getApplicationContext());
                UserDao userDao = dateBase.userDao();
                new Thread(() -> {
                    UserEntity userEntity = userDao.login(userNameText, passwordText);
                    if (userEntity == null) {
                        runOnUiThread(() -> Toast.makeText(getApplicationContext(),
                                "Invalid Credentials", Toast.LENGTH_SHORT).show());
                    } else {
                        startActivity(new Intent(MainActivity.this, Menu.class));
                    }

                }).start();
            }
        });

        register.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,
                Register.class)));
    }

}