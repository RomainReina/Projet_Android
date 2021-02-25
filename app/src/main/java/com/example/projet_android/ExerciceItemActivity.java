package com.example.projet_android;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class ExerciceItemActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_AppCompat_Light_NoActionBar);
        setContentView(R.layout.exercice_item);
        Intent intent = getIntent();
        WebView webView = (WebView) findViewById(R.id.webView);


        if (intent!=null) //Ici on récupère les infos transmises par l'activité "ExerciceActivity" : ici le lien de la vidéo de l'exercice sélectionné
        {
            Bundle extras =intent.getExtras();
            if(extras!=null)
            {
                String exoVideo= extras.getString("Video");
                WebSettings webSettings = webView.getSettings();
                webSettings.setJavaScriptEnabled(true); //On active le javascript pour que la web view s'actualise lorsque l'activité est chargée,
                //ce qui permet donc de lancer la vidéo

                webView.loadUrl(exoVideo);

            }
        }



        
    }



}