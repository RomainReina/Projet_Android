package com.example.projet_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    SharedPreferences preferences = getPreferences(MODE_PRIVATE);
    //preferences.edit().putString("firstname", mUser.getFirstName()).apply();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_account);
    }
}