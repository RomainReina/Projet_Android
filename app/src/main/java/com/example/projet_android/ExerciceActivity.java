package com.example.projet_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import fragments.ExercicesItemFragment;

public class ExerciceActivity extends AppCompatActivity {

    String[] exos = new String[]{"",
            "Développé couché", "Pompes", "Elévations latérales"};

    ListView mListView;

    ArrayList<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Design_Light_NoActionBar);
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

        /*String path = "raw/exercices.json";
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(path));
            Gson gson = new Gson();
            Object json = gson.fromJson(bufferedReader, Object.class);
            Log.i("dsc",json.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,exos);
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



}