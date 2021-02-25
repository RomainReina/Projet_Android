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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

import classes.Exercice;
import classes.Seance;
import fragments.ExerciceAdapter;
import fragments.SeanceAdapter;

public class SeancesActivity extends AppCompatActivity {


    RecyclerView mRecyclerView;
    private SeanceAdapter.RecyclerViewClickListener listener;

    SeanceAdapter adapter;
    ArrayList<Seance> mSeances;
    RequestQueue rq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Séances");
        setContentView(R.layout.activity_seances);
        mRecyclerView = findViewById(R.id.seanceList);

        String URL = "https://raw.githubusercontent.com/julianshapiro/julian.com/master/muscle/workout.json";

        setOnClickListener();
        mSeances=new ArrayList<>();
        rq=Volley.newRequestQueue(this);
        //adapter= new SeanceAdapter(URL,getBaseContext(),listener);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //mRecyclerView.setAdapter(adapter);
        recupSeances(URL);

        
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

    private void recupSeances(String url) {
        Gson gson = new Gson();
        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Type mSeancesType = new TypeToken<ArrayList<Seance>>() {}.getType();
                            mSeances=gson.fromJson(String.valueOf(response.getJSONArray("plans")), mSeancesType);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter=new SeanceAdapter(url,listener,mSeances);
                        mRecyclerView.setAdapter(adapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Rest Response", error.toString());
                    }
                });

        rq.add(objectRequest);
    }

}