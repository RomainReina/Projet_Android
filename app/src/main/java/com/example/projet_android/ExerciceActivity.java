package com.example.projet_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

public class ExerciceActivity extends AppCompatActivity {

    /*String[] exos = new String[]{"",
            "Développé couché", "Pompes", "Elévations latérales"};*/

    ListView mListView;

    ArrayList<String> exos = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Design_Light_NoActionBar);
        setContentView(R.layout.activity_exercice);
        mListView = (ListView) findViewById(R.id.list);

        String URL = "https://wger.de/api/v2/exercise/?format=json";
        ArrayList<String> exos=new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest objectRequest=new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            for (int i=0;i<response.getJSONArray("results").length();i++)
                            {
                                exos.add(response.getJSONArray("results").getJSONObject(i).getString("name"));
                                //Log.i("exoos", String.valueOf(exos));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Rest Response",error.toString());
                    }
                });

        requestQueue.add(objectRequest);
        //Log.i("ex", String.valueOf(exos));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,exos);
        //adapter.notifyDataSetChanged();
        mListView.setAdapter(adapter);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                String selectedItem= (String) parent.getItemAtPosition(position);
                startActivity(getIntent(selectedItem));
                //setContentView(R.layout.fragment_exercices_item);
                //  R.id.exerciceName=selectedItem;
            }
        });

        
    }

    private Intent getIntent(String exoName)
    {
        Intent intent= new Intent(this, ExerciceItemActivity.class);
        Bundle extras = new Bundle();
        extras.putString("Name",exoName);
        extras.putString("Desc",exoName+" description");
        extras.putString("Stats", exoName+" statistiques");
        intent.putExtras(extras);

        return intent;
    }

    /*private ArrayList<String> recupExos(String url)
    {
        ArrayList<String> exos=new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest objectRequest=new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            for (int i=0;i<response.getJSONArray("results").length();i++)
                            {
                                exos.add(response.getJSONArray("results").getJSONObject(i).getString("name"));
                                Log.i("exoos", String.valueOf(exos));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Rest Response",error.toString());
                    }
                });

        requestQueue.add(objectRequest);
        return exos;
    }*/



}