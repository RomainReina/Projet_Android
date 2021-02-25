package com.example.projet_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Register extends Activity implements View.OnClickListener {
    EditText userName,password,weight,height;
    Button button;
    Button logButton;
    public List<String> usernames = new ArrayList<>();


    Executor executor = Executors.newSingleThreadExecutor();
    Executor executor2 = Executors.newSingleThreadExecutor();



    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        userName = findViewById(R.id.user);
        password = findViewById(R.id.password);
        weight = findViewById(R.id.weight);
        height = findViewById(R.id.height);
        button = findViewById(R.id.RegisterButton);
        logButton = findViewById(R.id.LogButton);
        logButton.setOnClickListener(this);
        button.setOnClickListener(this);


        }

        public boolean Checkpassword()
        {
            String input = password.getText().toString();
            if(input.isEmpty())
            {
                password.setError("Ce champ ne peut pas être vide");
                return false;
            }
            else if(input.length() < 6)
            {
                password.setError("Le mot de passe doit contenir au moins 6 caractères");
                return false;
            }
            else
                {
                    password.setError(null);
                    return true;
                }
        }

        public boolean CheckWeight()
        {
            String input = weight.getText().toString();
            if(input.isEmpty())
            {
                weight.setError("Ce champ ne peut pas être vide");
                return false;
            }

            else if(!input.matches(("[0-9]+")))
            {
                weight.setError("Veuillez saisir un nombre");
                return false;
            }

            else
                {
                    weight.setError(null);
                    return true;
                }
        }

        public boolean CheckHeight()
        {
            String input = height.getText().toString();
            if(input.isEmpty())
            {
                height.setError("Ce champ ne peut pas être vide");
                return false;
            }

            else if(!input.matches(("[0-9]+")))
            {
                height.setError("Veuillez saisir un nombre");
                return false;
            }

            else
            {
                height.setError(null);
                return true;
            }
        }
//TODO il faut que tt les champs soient identiques
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.RegisterButton)
        {
            String userNameText = userName.getText().toString();
            String passwordText = password.getText().toString();
            String weightText = weight.getText().toString();
            String heightText = height.getText().toString();
            UserDatabase db = Room.databaseBuilder(this, UserDatabase.class, "UserDatabase.db").build();

            if(userNameText.isEmpty()){
                userName.setError("Ce champ ne peut pas être vide");
            }
            else{
                if (Checkpassword() && CheckWeight() && CheckHeight()){
                    executor.execute(()->
                    {
                        if(db.userDAO().retrieveUserInfo(userNameText)==null){
                            User newUser = new User();
                            newUser.setUsername(userNameText);
                            newUser.setPassword(passwordText);
                            newUser.setWeight(Integer.parseInt(weightText));
                            newUser.setHeight(Integer.parseInt(heightText));
                            db.userDAO().insert(newUser); //On insère le nouvel utilisateur dans la base de données

                            startActivity(new Intent(this, HomeActivity.class).putExtra("username",userNameText));
                        }
                        else{
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    userName.setError("Ce nom d'utilisateur est déjà pris");
                                }
                            });
                        }

                    });


                }
            }
            }




        else if(v.getId() == R.id.LogButton)
        {
            Intent intent = new Intent(this,Login.class);
            Bundle bundle = new Bundle();
            bundle.putString("username",userName.getText().toString());
            bundle.putString("password",password.getText().toString());
            intent.putExtras(bundle);

            startActivity(intent);
        }

    }
}




