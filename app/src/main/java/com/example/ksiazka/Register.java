package com.example.ksiazka;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ksiazka.daos.UserDao;
import com.example.ksiazka.datebase.DateBase;
import com.example.ksiazka.datebase.UserEntity;

public class Register extends AppCompatActivity {
    EditText userName, password;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register_button);

        register.setOnClickListener(v -> {
            UserEntity userEntity = new UserEntity();
            userEntity.setUserName(userName.getText().toString());
            userEntity.setPassword(password.getText().toString());
            DateBase dataBase = DateBase.getDateBase(getApplicationContext());
            final UserDao userDao = dataBase.userDao();
            if (validateInput(userEntity)) {
                new Thread(() -> {
                    String check = userDao.check(userName.getText().toString());
                    if (check == null || !check.equals(userName.getText().toString())) {
                        userDao.registerUser(userEntity);
                        runOnUiThread(() -> {
                            Toast.makeText(getApplicationContext(), "User registered!",
                                    Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Register.this, MainActivity.class));
                        });
                    } else {
                        runOnUiThread(() -> Toast.makeText(getApplicationContext(),
                                "User already exists !", Toast.LENGTH_SHORT).show());
                    }
                }).start();
            } else {
                runOnUiThread(() -> Toast.makeText(getApplicationContext(),
                        "Fill all fields!", Toast.LENGTH_SHORT).show());
            }
        });
    }

    private Boolean validateInput(UserEntity userEntity) {
        if (userEntity.getUserName().isEmpty() && userEntity.getPassword().isEmpty()) {
            return false;
        } else return true;
    }
}