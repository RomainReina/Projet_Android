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

public class ExerciceActivity extends AppCompatActivity {


    RecyclerView mRecyclerView;
    private ExerciceAdapter.RecyclerViewClickListener listener;

    ExerciceAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice);
        mRecyclerView = findViewById(R.id.exoList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        String URL = "https://raw.githubusercontent.com/julianshapiro/julian.com/master/muscle/workout.json";

        setOnClickListener();
        adapter= new ExerciceAdapter(URL,getBaseContext(),listener);

        adapter.notifyDataSetChanged();
        Log.i("set", String.valueOf(adapter.mExos));
        mRecyclerView.setAdapter(adapter);



        
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

}