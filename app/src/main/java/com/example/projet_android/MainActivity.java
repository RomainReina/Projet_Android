package com.example.projet_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import pl.pawelkleczkowski.customgauge.CustomGauge;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener, View.OnClickListener {

    //TODO esthétisme page d'accueil
    //TODO d'abord page login puis redirection si nécessaire vers register
    //TODO CComparer les usernames dans la bdd pour register et login
    

    Executor executor = Executors.newSingleThreadExecutor();
    public String id;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Design_Light_NoActionBar);              // on cache l'action barre
        setContentView(R.layout.home_page_layout);                  // on charge le layout de la page d'accueil
        Button button1 = findViewById(R.id.MenuButton);             // on récupère la référence du composant
        button1.setOnClickListener(this);// on crée un listener pour le composant pour gérer son intéraction
        Button LogoutButton = findViewById(R.id.LogoutButton);
        LogoutButton.setOnClickListener(this);
        TextView stepText = findViewById(R.id.NombreDePasTextView);
        CustomGauge gauge = findViewById(R.id.imcGauge);
        TextView imcTextView = findViewById(R.id.GaugeTextView);
        TextView welcomeTextView = findViewById(R.id.loginUsername);
        UserDatabase db = Room.databaseBuilder(this,UserDatabase.class,"UserDatabase.db").build();

        executor.execute(()->
        {

            User user;
            user = db.userDAO().retrieveUserInfo(getIntent().getStringExtra("username"));
            float imc = ComputeIMC(user.getWeight(),user.getHeight());
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    welcomeTextView.setText(user.getUsername());
                    stepText.setText("15"); //UI manipulation, forbidden in background thread
                    imcTextView.setText(String.valueOf(imc));
                    gauge.setValue((int)imc);
                }
            });

        });
    }

    public void showPopup (View v) {                                // On crée la pop-up
        PopupMenu popup = new PopupMenu(this, v);

        // This activity implements OnMenuItemClickListener
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.main_menu);
        popup.show();
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {                 // on gére les actions à effectuer en fonction de la section cliquée
        switch (item.getItemId()) {
            case R.id.Settings:
                startActivity(new Intent(this,Settings.class));
                return true;
            case R.id.Exercices:
                startActivity(new Intent(this, ExerciceActivity.class));
                return true;
            case R.id.Séances:
                startActivity(new Intent(this, SeancesActivity.class));
                return true;
            default:
                return false;
        }
    }

    @Override
    public void onClick(View v) {                               // si l'appuie sur le bouton est détecté, on affiche la pop-up
        if(v.getId() == R.id.MenuButton)
        {
            showPopup(v);
        }

        else if(v.getId() == R.id.LogoutButton)
        {
            startActivity(new Intent(this,Register.class));
        }


    }

    public float ComputeIMC(double weight, double height)
    {
        return Math.round( weight / Math.pow(height/100,2));
    }

}