package com.example.projet_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.util.ArrayList;

import classes.Exercice;

public class ExerciceActivity extends AppCompatActivity {

    String[] prenoms = new String[]{
            "Antoine", "Benoit", "Cyril", "David", "Eloise", "Florent",
            "Gerard", "Hugo", "Ingrid", "Jonathan", "Kevin", "Logan",
            "Mathieu", "Noemie", "Olivia", "Philippe", "Quentin", "Romain","Sophie",
            "Tristan", "Ulric", "Vincent", "Willy", "Xavier","Yann", "Zoé"};

    ListView mListView;

    ArrayList<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice);
        mListView = (ListView) findViewById(R.id.list);
        String data;
        /*try {
            InputStream is = getAssets().open("exercices.json");
            int size= is.available();
            byte[] buffer=new byte[size];
            is.read(buffer);
            is.close();
            data = new String(buffer,"UTF-8");
            JSONObject json = new JSONObject(data);
            String test= json.getString("name");
            Log.i("fj",test);


        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }*/

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ExerciceActivity.this,
                android.R.layout.simple_list_item_1,prenoms);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                String selectedItem= (String) parent.getItemAtPosition(position);
                setContentView(R.layout.fragment_exercices_item);
            }
        });

        
    }



}