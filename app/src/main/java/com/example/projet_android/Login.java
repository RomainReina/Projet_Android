package com.example.projet_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Login extends AppCompatActivity implements View.OnClickListener {

    EditText userID,password;
    Executor executor = Executors.newSingleThreadExecutor();
    public List<String> usernames = new ArrayList();
    public String checkPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        userID = findViewById(R.id.loginUsername);
        password = findViewById(R.id.loginPassword);
        Button button = findViewById(R.id.Login);
        Button createAccount = findViewById(R.id.CreateAccountButton);
        createAccount.setOnClickListener(this);
        button.setOnClickListener(this);
    }

    public boolean CheckUsername()
    {

        String input = userID.getText().toString();
        if(input.isEmpty())
        {
            userID.setError("Field cannot be empty");
            return false;
        }
        else if (!(usernames.contains(input)))
            {
                userID.setError("Incorrect Username");
                return false;
            }

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

        else if(!checkPassword.equalsIgnoreCase(input))
        {
            password.setError("Incorrect password");
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

        if(v.getId() == R.id.Login)
        {
            UserDatabase db = Room.databaseBuilder(this, UserDatabase.class, "UserDatabase.db").build();

            executor.execute(() ->
            {
                usernames = db.userDAO().allUsername();
                checkPassword = db.userDAO().retrievePassword(password.getText().toString());
            });



            if(!CheckUsername() || !Checkpassword())
            {
                return;
            }
            String userIDText = userID.getText().toString();
            String passwordtext = password.getText().toString();

            executor.execute(()->
            {
                User user = new User();
                user.setUsername(userIDText);
                user.setPassword(passwordtext);
                db.userDAO().insert(user);

                startActivity(new Intent(this,MainActivity.class).putExtra("username",userIDText));

            });
        }

        else if(v.getId() == R.id.CreateAccountButton)
        {
            startActivity(new Intent(this,Register.class));
        }
    }

}