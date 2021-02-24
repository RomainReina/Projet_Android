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
        TextView nameView = (TextView) findViewById(R.id.exerciceName);
        WebView webView = (WebView) findViewById(R.id.webView);


        if (intent!=null)
        {
            Bundle extras =intent.getExtras();
            if(extras!=null)
            {
                String exoName= extras.getString("Name");
                String exoVideo= extras.getString("Video");
                nameView.setText(exoName);
                WebSettings webSettings = webView.getSettings();
                webSettings.setJavaScriptEnabled(true);

                webView.loadUrl(exoVideo);

            }
        }



        
    }



}