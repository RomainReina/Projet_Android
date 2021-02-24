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
        setContentView(R.layout.seance_item);
        Intent intent = getIntent();
        String URL = "https://raw.githubusercontent.com/julianshapiro/julian.com/master/muscle/workout.json";

        dayListRecyclerView = findViewById(R.id.daysList);

        if (intent!=null)
        {
            Bundle extras =intent.getExtras();
            if(extras!=null)
            {
                String seanceName= extras.getString("SeanceName");
                int seanceId=extras.getInt("SeanceId");
                adapter= new DaysAdapter(URL,getBaseContext(),listener,seanceId);
                adapter.notifyDataSetChanged();
                setTitle(seanceName);

            }
        }
        dayListRecyclerView.setAdapter(adapter);





        
    }



}