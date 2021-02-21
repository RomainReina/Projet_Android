package com.example.projet_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Login extends AppCompatActivity implements View.OnClickListener {

    EditText userID,password;
    Executor executor = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        userID = findViewById(R.id.loginUsername);
        password = findViewById(R.id.loginPassword);
        Button button = findViewById(R.id.button2);
        button.setOnClickListener(this);
    }

    public boolean CheckUsername()
    {
        executor.execute(()->
        {
            UserDatabase db = Room.databaseBuilder(this, UserDatabase.class, "UserDatabase.db").build();
            //usernames = db.userDAO().allUsername();
        });


        String input = userID.getText().toString();
        if(input.isEmpty())
        {
            userID.setError("Field cannot be empty");
            return false;
        }
//        else if (usernames.contains(userID))
//        {
//            userID.setError("Username is already taken");
//            return  false;
//        }
        else
        {
            userID.setError(null);
            return true;
        }
    }

    public boolean Checkpassword()
    {
        String input = password.getText().toString();
        if(input.isEmpty())
        {
            password.setError("Field cannot be empty");
            return false;
        }
        else if(input.length() < 6)
        {
            password.setError("Password must contains at least 6 characters");
            return false;
        }
        else
        {
            password.setError(null);
            return true;
        }
    }

    @Override
    public void onClick(View v) {
        UserDatabase db = Room.databaseBuilder(this, UserDatabase.class, "UserDatabase.db").build();
        User user = new User();


        if(!CheckUsername() || !Checkpassword())
        {
            return;
        }
        String userIDText = userID.getText().toString().trim();
        String passwordtext = password.getText().toString();

        executor.execute(()->
        {
            user.setUsername(userIDText);
            user.setPassword(passwordtext);
            db.userDAO().insert(user);

            startActivity(new Intent(this,MainActivity.class).putExtra("username",userIDText));

        });
    }
}