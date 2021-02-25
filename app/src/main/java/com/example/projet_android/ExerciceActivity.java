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
import fragments.ExerciceAdapter;

public class ExerciceActivity extends AppCompatActivity {


    RecyclerView mRecyclerView;
    private ExerciceAdapter.RecyclerViewClickListener listener;

    ExerciceAdapter adapter;
    RequestQueue rq;
    ArrayList<Exercice> mExos;
    String URL = "https://raw.githubusercontent.com/julianshapiro/julian.com/master/muscle/workout.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice);
        mRecyclerView = findViewById(R.id.exoList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        setOnClickListener();
        rq=Volley.newRequestQueue(this);
        mExos=new ArrayList<>();
        recupExos();
        
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        ;
        return true;
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
        String video= adapter.mExos.get(idExo).getVideo();
        extras.putString("Video",video);
        intent.putExtras(extras);

        return intent;
    }
    public void recupExos() {
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
                        adapter=new ExerciceAdapter(URL,listener,mExos);
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