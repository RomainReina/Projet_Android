package com.example.projet_android;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import fragments.SeanceAdapter;

public class SeanceItemActivity extends AppCompatActivity {


    RecyclerView mRecyclerView;
    private SeanceAdapter.RecyclerViewClickListener listener;
    SeanceAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_AppCompat_Light_NoActionBar);
        setContentView(R.layout.seance_item);
        Intent intent = getIntent();
        TextView seanceView= (TextView) findViewById(R.id.seance);
        /*mRecyclerView = findViewById(R.id.exoCardList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        /*TextView nameView = (TextView) findViewById(R.id.exName);
        /*TextView descView = (TextView) findViewById(R.id.exerciceDesc);
        TextView statView = (TextView) findViewById(R.id.exerciceStat);*/
        //WebView webView = (WebView) findViewById(R.id.webView);


        if (intent!=null)
        {
            Bundle extras =intent.getExtras();
            if(extras!=null)
            {
                String seanceName= extras.getString("Seance");
                //String exoVideo= extras.getString("Video");
                /*String exoDesc= extras.getString("Desc");
                String exoStat= extras.getString("Stats");*/
                /*nameView.setText(exoName);
                WebSettings webSettings = webView.getSettings();
                webSettings.setJavaScriptEnabled(true);
                webView.loadUrl(exoVideo);*/
                seanceView.setText(seanceName);

            }
        }



        
    }



}