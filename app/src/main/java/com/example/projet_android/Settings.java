package com.example.projet_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Settings extends AppCompatActivity implements View.OnClickListener {


    private Button profil;
    private Button privacy_policy;
    private Button support;
    private Button account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);


        profil = findViewById(R.id.profilButton);
        privacy_policy = findViewById(R.id.privacypolicyButton);
        support = findViewById(R.id.supportButton);
        account =  findViewById(R.id.accountButton);

        profil.setOnClickListener(this);
        privacy_policy.setOnClickListener(this);
        support.setOnClickListener(this);
        account.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.profilButton){
            startActivity(new Intent(this, Settings.class));

        }

        else if(v.getId() == R.id.accountButton){
            startActivity(new Intent(this,Settings.class));

        }
        else if(v.getId() == R.id.privacypolicyButton){
            startActivity(new Intent(this,Settings.class));

        }
        else if(v.getId() == R.id.supportButton){
            startActivity(new Intent(this,Settings.class));

        }
    }
}