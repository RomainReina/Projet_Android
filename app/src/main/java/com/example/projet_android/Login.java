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

    EditText userName,password;
    Executor executor = Executors.newSingleThreadExecutor(); //On utilise l'executor pour faire nos requêtes de base de données en arrière-plan

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Design_Light_NoActionBar);
        setContentView(R.layout.login);
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
            UserDatabase db = Room.databaseBuilder(this, UserDatabase.class, "UserDatabase.db").build(); //On build la base de données
            if(userNameText.isEmpty()){
                userName.setError("Ce champ ne peut pas être vide");
            }
            else if(passwordText.isEmpty()){
                password.setError("Ce champ ne peut pas être vide");
            }
            else {
                executor.execute(() ->
                {
                    User user = db.userDAO().retrieveUserInfo(userNameText);//On utilise la requête définie dans userDAO pour récupérer l'utilisateur par son pseudo
                    if (user == null) {
                        runOnUiThread(new Runnable() { //On exécute l'instruction suivante dans le UI Thread car on effectue une action sur une des views
                            //du UI Thread
                            @Override
                            public void run() {
                                userName.setError("Ce nom d'utilisateur n'existe pas");
                            }
                        });
                    } else {
                        if (user.getPassword().equalsIgnoreCase(passwordText)) {
                            startActivity(new Intent(getBaseContext(), HomeActivity.class).putExtra("username", userNameText)); //On lance l'activité
                            //home en lui transmettant des informations sur l'utilisateur
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