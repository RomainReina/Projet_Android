package com.example.projet_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import fragments.ExerciceAdapter;

public class ExerciceActivity extends AppCompatActivity {


    RecyclerView mRecyclerView;
    private ExerciceAdapter.RecyclerViewClickListener listener;

    ExerciceAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Design_Light_NoActionBar);
        setContentView(R.layout.activity_exercice);
        mRecyclerView = findViewById(R.id.exoList);

        //String URL = "https://wger.de/api/v2/exercise/?format=json";
        String URL = "https://raw.githubusercontent.com/julianshapiro/julian.com/master/muscle/workout.json";

        setOnClickListener();
        adapter= new ExerciceAdapter(URL,this,listener);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        
    }

    private void setOnClickListener() {
        listener= new ExerciceAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = getIntent(position);
                startActivity(intent);
            }
        };
    }

    private Intent getIntent(int idExo)
    {
        Intent intent= new Intent(this, ExerciceItemActivity.class);
        Bundle extras = new Bundle();
        extras.putString("Name",adapter.mExos.get(idExo));
        intent.putExtras(extras);

        return intent;
    }




}