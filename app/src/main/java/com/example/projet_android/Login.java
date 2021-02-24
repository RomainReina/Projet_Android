package com.example.projet_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Login extends AppCompatActivity implements View.OnClickListener {

    EditText userName,password;
    Executor executor = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        setTheme(R.style.Theme_Design_Light_NoActionBar);
        userName = findViewById(R.id.loginUsername);
        password = findViewById(R.id.loginPassword);
        Button button = findViewById(R.id.Login);
        Button createAccount = findViewById(R.id.CreateAccountButton);
        createAccount.setOnClickListener(this);
        button.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.Login)
        {
            String userNameText = String.valueOf(userName.getText());
            String passwordText = String.valueOf(password.getText());
            UserDatabase db = Room.databaseBuilder(this, UserDatabase.class, "UserDatabase.db").build();
            if(userNameText.isEmpty()){
                userName.setError("Ce champ ne peut pas être vide");
            }
            else if(passwordText.isEmpty()){
                password.setError("Ce champ ne peut pas être vide");
            }
            else {
                executor.execute(() ->
                {
                    User user = db.userDAO().retrieveUserInfo(userNameText);
                    if (user == null) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                userName.setError("Ce nom d'utilisateur n'existe pas");
                            }
                        });
                    } else {
                        if (user.getPassword().equalsIgnoreCase(passwordText)) {
                            startActivity(new Intent(getBaseContext(), MainActivity.class).putExtra("username", userNameText));
                        }
                        else{
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    password.setError("Mot de passe incorrect");
                                }
                            });
                        }
                    }
                });

            }
        }
        else if (v.getId() == R.id.CreateAccountButton)
        {
            startActivity(new Intent(this, Register.class));
        }

}
}