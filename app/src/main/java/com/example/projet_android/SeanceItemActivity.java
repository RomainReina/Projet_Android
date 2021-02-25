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
import java.util.List;

import classes.Day;
import classes.Exercice;
import fragments.DaysAdapter;
import fragments.ExerciceAdapter;
import fragments.ExoDayAdapter;
import fragments.SeanceAdapter;

public class SeanceItemActivity extends AppCompatActivity {


    RecyclerView dayListRecyclerView;
    private DaysAdapter.RecyclerViewClickListener listener;
    DaysAdapter dayAdapter;
    ArrayList<Day> mDays;
    ArrayList<Exercice> mExos;
    RequestQueue rq;
    String URL = "https://raw.githubusercontent.com/julianshapiro/julian.com/master/muscle/workout.json";


    @Override
    protected void onCreate(Bundle savedInstanceState) { //Même principe que pour les activités d'exercice et de séance
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seance_item);
        Intent intent = getIntent();
        dayListRecyclerView = findViewById(R.id.daysList);
        rq=Volley.newRequestQueue(this);
        mDays=new ArrayList<>();
        if (intent!=null)
        {
            Bundle extras =intent.getExtras();
            if(extras!=null)
            {
                String seanceName= extras.getString("SeanceName");
                int seanceId=extras.getInt("SeanceId");
                setTitle(seanceName); //On indique le nom du programme sélectionné dans l'action bar
                recupExos(); //Comme on utilise ici 2 recyclers view imbriqués (le 2ème est déclaré dans l'adapter "DayExoAdapter"), on effectue l'appel réseau
                //pour récupérer les exercices dans un premier temps, pour faire la correspondance entre l'id et le nom des exercices par la suite
                recupDays(seanceId); //On récupère l'ensemble des "days" pour le programme sélectionné

            }
        }





        
    }
    private void recupDays(int seanceId) { //Même principe que les autres requête réseau mais avec les données des days dans les programmes
        Gson gson = new Gson();
        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Type mDaysType = new TypeToken<ArrayList<Day>>() {}.getType();
                            mDays=gson.fromJson(String.valueOf(response.getJSONArray("plans").getJSONObject(seanceId).getJSONArray("days")), mDaysType);
                            //Comme pour les exercices, on précise ici que les données récupérées sont stockées en tant qu'instance de "Day"
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                        dayAdapter=new DaysAdapter(URL,listener,mDays,mExos); //On crée notre adapter personnalisé pour les days
                        dayListRecyclerView.setAdapter(dayAdapter);
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

    public void recupExos() { //Même méthode que pour l'activité "ExerciceActivity"
        Gson gson = new Gson();
        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Type mExosType = new TypeToken<ArrayList<Exercice>>() {}.getType();
                        try {
                            mExos = gson.fromJson(String.valueOf(response.getJSONArray("exercises")), mExosType);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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