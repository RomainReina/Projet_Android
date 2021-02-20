package com.example.projet_android;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import fragments.ExercicesItemFragment;

public class ExerciceItemActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_AppCompat_Light_NoActionBar);
        setContentView(R.layout.fragment_exercices_item);
        Intent intent = getIntent();
        TextView nameView = (TextView) findViewById(R.id.exerciceName);
        /*TextView descView = (TextView) findViewById(R.id.exerciceDesc);
        TextView statView = (TextView) findViewById(R.id.exerciceStat);*/
        WebView webView = (WebView) findViewById(R.id.webView);


        if (intent!=null)
        {
            Bundle extras =intent.getExtras();
            if(extras!=null)
            {
                String exoName= extras.getString("Name");
                String exoVideo= extras.getString("Video");
                /*String exoDesc= extras.getString("Desc");
                String exoStat= extras.getString("Stats");*/
                nameView.setText(exoName);
                WebSettings webSettings = webView.getSettings();
                webSettings.setJavaScriptEnabled(true);

                webView.loadUrl(exoVideo);

            }
        }



        
    }



}