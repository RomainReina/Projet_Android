package com.example.projet_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Register extends Activity implements View.OnClickListener {
    EditText userID,password,weight,height;
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
        userID = findViewById(R.id.user);
        password = findViewById(R.id.password);
        weight = findViewById(R.id.weight);
        height = findViewById(R.id.height);
        button = findViewById(R.id.RegisterButton);
        logButton = findViewById(R.id.LogButton);
        logButton.setOnClickListener(this);
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
            else if (usernames.contains(input))
            {
                userID.setError("Username is already taken");
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
//TODO il faut que tt les champs soient identiques
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.RegisterButton)
        {
            String userIDText = userID.getText().toString();
            String passwordtext = password.getText().toString();
            String weightText = weight.getText().toString();
            String heightText = height.getText().toString();
            UserDatabase db = Room.databaseBuilder(this, UserDatabase.class, "UserDatabase.db").build();
            /*executor2.execute(() ->
            {
                Log.i("ok", "Inside");
                //usernames=db.userDAO().allUsername();
                Log.i("usernames", usernames.toString());

            });

            Log.i("userre",usernames.toString());*/

            if (!CheckUsername() || !Checkpassword() || !CheckWeight() || !CheckHeight())
            {
                return;
            }



            executor.execute(()->
        {



                User user = new User();
                user.setUsername(userIDText);
                user.setPassword(passwordtext);
                user.setWeight(Integer.parseInt(weightText));
                user.setHeight(Integer.parseInt(heightText));
                db.userDAO().insert(user);

                startActivity(new Intent(this,MainActivity.class).putExtra("username",userIDText));
            });

        }

        else if(v.getId() == R.id.LogButton)
        {
            Intent intent = new Intent(this,Login.class);
            Bundle bundle = new Bundle();
            bundle.putString("username",userID.getText().toString());
            bundle.putString("password",password.getText().toString());
            intent.putExtras(bundle);

            startActivity(intent);
        }

    }
}




