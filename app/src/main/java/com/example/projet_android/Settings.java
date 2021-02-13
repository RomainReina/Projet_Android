package com.example.projet_android;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

public class Settings extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_layout);
        Switch settingsSwitch = findViewById(R.id.SwitchColor);
        settingsSwitch.setOnClickListener(this);
    }

    public void changeBackground(ConstraintLayout layout)
    {
        layout.setBackgroundColor(Color.BLUE);                          // On modifie la couleur du background
    }

    @Override
    public void onClick(View v) {
        changeBackground(findViewById(R.id.layout));

    }
}
