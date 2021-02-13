package com.example.projet_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener, View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page_layout);                  // on charge le layout de la page d'accueil
        Button button1 = findViewById(R.id.MenuButton);             // on récupère la référence du composant
        button1.setOnClickListener(this);                           // on crée un listener pour le compposant pour gérer son intéraction
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
                Toast.makeText(this,"option1",Toast.LENGTH_SHORT).show();
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