package com.example.projet_android;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import fragments.ExerciceAdapter;
import fragments.SeanceAdapter;

public class SeancesActivity extends AppCompatActivity {


    RecyclerView mRecyclerView;
    private SeanceAdapter.RecyclerViewClickListener listener;

    SeanceAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seances);
        mRecyclerView = findViewById(R.id.seanceList);

        String URL = "https://raw.githubusercontent.com/julianshapiro/julian.com/master/muscle/workout.json";

        setOnClickListener();
        adapter= new SeanceAdapter(URL,getBaseContext(),listener);
        adapter.notifyDataSetChanged();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        mRecyclerView.setAdapter(adapter);


        
    }

    private void setOnClickListener() {
        listener= new SeanceAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = getIntent(position);
                startActivity(intent);
            }
        };
    }

    private Intent getIntent(int idSeance)
    {
        Intent intent= new Intent(this, SeanceItemActivity.class);
        Bundle extras = new Bundle();
        String seanceName = adapter.mSeances.get(idSeance).getName();
        int seanceId = adapter.mSeances.get(idSeance).getId();
        extras.putString("SeanceName",seanceName);
        extras.putInt("SeanceId",seanceId);
        intent.putExtras(extras);
        return intent;
    }

}