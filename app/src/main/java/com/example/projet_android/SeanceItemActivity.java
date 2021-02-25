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
import fragments.ExoDayAdapter;
import fragments.SeanceAdapter;

public class SeanceItemActivity extends AppCompatActivity {


    RecyclerView dayListRecyclerView;
    RecyclerView dayExoRecyclerView;
    private DaysAdapter.RecyclerViewClickListener listener;
    DaysAdapter dayAdapter;
    ExoDayAdapter exoDayAdapter;
    public ArrayList<Day> mDays;
    RequestQueue rq;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seance_item);
        Intent intent = getIntent();
        String URL = "https://raw.githubusercontent.com/julianshapiro/julian.com/master/muscle/workout.json";

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
                /*adapter= new DaysAdapter(URL,getBaseContext(),listener,seanceId);
                adapter.notifyDataSetChanged();*/
                setTitle(seanceName);
                recupDays(URL,seanceId);

            }
        }
        //dayListRecyclerView.setAdapter(adapter);





        
    }
    private void recupDays(String url,int seanceId) {
        Gson gson = new Gson();
        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Type mDaysType = new TypeToken<ArrayList<Day>>() {}.getType();
                            mDays=gson.fromJson(String.valueOf(response.getJSONArray("plans").getJSONObject(seanceId).getJSONArray("days")), mDaysType);
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                        dayAdapter=new DaysAdapter(url,listener,mDays);
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



}