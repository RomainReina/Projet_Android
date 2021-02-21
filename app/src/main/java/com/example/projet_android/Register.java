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
    EditText userID,password,weight,height;
    Button button;
    List<String> usernames = new ArrayList<>();


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
        button = findViewById(R.id.RegisterButton);
        button.setOnClickListener(this);


        }
        public boolean CheckUsername()
        {
            executor.execute(()->
            {
                UserDatabase db = Room.databaseBuilder(this, UserDatabase.class, "UserDatabase.db").build();
                usernames = db.userDAO().allUsername();
            });


            String input = userID.getText().toString();
            if(input.isEmpty())
            {
                userID.setError("Field cannot be empty");
                return false;
            }
            else if (usernames.contains(userID))
                {
                    userID.setError("Username is already taken");
                    return  false;
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
                weight.setError("Field cannot be empty");
                return false;
            }

            else if(!input.matches(("[0-9]+")))
            {
                weight.setError("Weight must be an integer value");
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
                height.setError("Field cannot be empty");
                return false;
            }

            else if(!input.matches(("[0-9]+")))
            {
                height.setError("Height must be an integer value");
                return false;
            }

            else
            {
                height.setError(null);
                return true;
            }
        }

    @Override
    public void onClick(View v) {
        UserDatabase db = Room.databaseBuilder(this, UserDatabase.class, "UserDatabase.db").build();
        User user = new User();


        if(!CheckUsername() || !Checkpassword() || !CheckWeight() || !CheckHeight())
        {
            return;
        }
        String userIDText = userID.getText().toString().trim();
        String passwordtext = password.getText().toString();
        String weightText = weight.getText().toString();
        String heightText = height.getText().toString();

        executor.execute(()->
        {
            user.setUsername(userIDText);
            user.setPassword(passwordtext);
            user.setWeight(Integer.parseInt(weightText));
            user.setHeight(Integer.parseInt(heightText));
            db.userDAO().insert(user);

            startActivity(new Intent(this,MainActivity.class).putExtra("username",userIDText));
        });



    }







    }




