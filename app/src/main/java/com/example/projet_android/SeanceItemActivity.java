package com.example.projet_android;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import classes.Exercice;
import fragments.DaysAdapter;
import fragments.ExoDayAdapter;
import fragments.SeanceAdapter;

public class SeanceItemActivity extends AppCompatActivity {


    RecyclerView dayListRecyclerView;
    RecyclerView dayExoRecyclerView;
    private DaysAdapter.RecyclerViewClickListener listener;
    DaysAdapter adapter;
    ExoDayAdapter exoDayAdapter;


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
        String URL = "https://raw.githubusercontent.com/julianshapiro/julian.com/master/muscle/workout.json";

        dayListRecyclerView = findViewById(R.id.daysList);
        dayExoRecyclerView=findViewById(R.id.exoDayList);

        if (intent!=null)
        {
            Bundle extras =intent.getExtras();
            if(extras!=null)
            {
                String seanceName= extras.getString("SeanceName");
                int seanceId=extras.getInt("SeanceId");
                //String exoVideo= extras.getString("Video");
                /*String exoDesc= extras.getString("Desc");
                String exoStat= extras.getString("Stats");*/
                /*nameView.setText(exoName);
                WebSettings webSettings = webView.getSettings();
                webSettings.setJavaScriptEnabled(true);
                webView.loadUrl(exoVideo);*/
                seanceView.setText(seanceName);
                adapter= new DaysAdapter(URL,this,listener,seanceId);


            }
        }
        //dayListRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        /*for(int i=0;i<adapter.mDays.get(0).getExos().size();i++){
            Log.i("exoId", String.valueOf(adapter.mDays.get(0).getExos().get(i).getId()));
            Log.i("exoWeight", String.valueOf(adapter.mDays.get(0).getExos().get(i).getWeight()));
            Log.i("exoSets", String.valueOf(adapter.mDays.get(0).getExos().get(i).getSets()));
            Log.i("exoUnit", String.valueOf(adapter.mDays.get(0).getExos().get(i).getUnit()));
        }*/

        //exoDayAdapter=new ExoDayAdapter();





        
    }



}