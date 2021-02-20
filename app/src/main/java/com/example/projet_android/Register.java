package com.example.projet_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Register extends Activity implements View.OnClickListener {
    EditText userID,password,weight,height;
    Button button;


    Executor executor = Executors.newSingleThreadExecutor();


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        userID = findViewById(R.id.user);
        password = findViewById(R.id.password);
        weight = findViewById(R.id.weight);
        height = findViewById(R.id.height);
        button = findViewById(R.id.button);
        button.setOnClickListener(this);


        }

    @Override
    public void onClick(View v) {
        UserDatabase db = Room.databaseBuilder(this, UserDatabase.class, "UserDatabase.db").build();
        User user = new User();
        String userIDText = userID.getText().toString().trim();
        String passwordtext = password.getText().toString();
        String weightText = weight.getText().toString();
        String heightText = height.getText().toString();
        button.setVisibility(View.GONE);


        if (userIDText.isEmpty()) {
            Toast.makeText(this, "Username is required", Toast.LENGTH_SHORT).show();
        }

        if (passwordtext.isEmpty()) {
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();

        }

        if (passwordtext.length() < 6) {
            Toast.makeText(this, "Password must contains at least 6 characters", Toast.LENGTH_SHORT).show();

        }

        if (weightText.isEmpty() || !weightText.matches(("[0-9]+"))) {
            Toast.makeText(this, "Invalid information", Toast.LENGTH_SHORT).show();

        }

        if (heightText.isEmpty() || !heightText.matches(("[0-9]+"))) {
            Toast.makeText(this, "Invalid information", Toast.LENGTH_SHORT).show();

        }
        executor.execute(()->
        {
            user.setUsername(userIDText);
            user.setPassword(passwordtext);
            user.setWeight(Integer.parseInt(weightText));
            db.userDAO().insert(user);

            startActivity(new Intent(this,MainActivity.class).putExtra("username",userIDText));
        });



    }







    }




