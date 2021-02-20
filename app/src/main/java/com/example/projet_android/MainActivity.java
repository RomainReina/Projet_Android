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
import android.widget.Toast;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import pl.pawelkleczkowski.customgauge.CustomGauge;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener, View.OnClickListener {

    //TODO utilisation parcable pour récupérer plussieurs donées en passant de login à main
    //TODO bdd elle fait quoi ?
    //TODO esthétisme page d'accueil
    

    Executor executor = Executors.newSingleThreadExecutor();
    public int value;
    public String id;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Design_Light_NoActionBar);              // on cache l'action barre
        setContentView(R.layout.home_page_layout);                  // on charge le layout de la page d'accueil
        Button button1 = findViewById(R.id.MenuButton);             // on récupère la référence du composant
        button1.setOnClickListener(this);// on crée un listener pour le compposant pour gérer son intéraction
        TextView stepText = findViewById(R.id.NombreDePasTextView);
        CustomGauge gauge = findViewById(R.id.imcGauge);
        TextView imcTextView = findViewById(R.id.GaugeTextView);
        TextView welcomeTextView = findViewById(R.id.loginUsername);
        UserDatabase db = Room.databaseBuilder(this,UserDatabase.class,"UserDatabase.db").build();

        executor.execute(()->
        {

              User user;
              user = db.userDAO().retrieveUserInfo(getIntent().getStringExtra("username"));
//            user.setUsername("pat");
//            user.setPassword("password");
//            user.setSteps("10");
//            user.setHeight(1.80);
//            user.setWeight(180);
//            value = user.ComputeIMC();

            welcomeTextView.setText(user.getUsername());
            stepText.setText("15"); //UI manipulation, forbidden in background thread
            imcTextView.setText(String.valueOf(value));
            gauge.setValue(value);

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
            case R.id.option1:
                startActivity(new Intent(this, Register.class));
                return true;
            default:
                return false;
        }
    }

    @Override
    public void onClick(View v) {                               // si l'appuie sur le bouton est détecté, on affiche la pop-up
        showPopup(v);

    }

    private int cpt = 0;
    @Override
    public void onBackPressed()             // On gère le cas où l'utilisateur appuie sur la touche retour
    {
        cpt++;
        switch(cpt)
        {
            case 1:
                Toast.makeText(this, "Souhaitez vous vraiment quitter l'application ? Appuyer sur retour pour confirmer", Toast.LENGTH_LONG).show();
                break;
            case 2:
                super.onBackPressed();
                break;
        }
    }
}