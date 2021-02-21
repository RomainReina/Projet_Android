package com.example.projet_android;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.example.projet_android.utils.PreferenceUtils;
import com.example.projet_android.utils.Constants;

import java.util.prefs.Preferences;

public class SBLoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mLoginEditText;
    private EditText mPasswordEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        mLoginEditText = (EditText) findViewById(R.id.loginEditText);
        mPasswordEditText = (EditText) findViewById(R.id.passwordEditText);

        findViewById(R.id.loginButton).setOnClickListener(this);
        final String login = PreferenceUtils.getLogin();
        if(!TextUtils.isEmpty(login)){
            startActivity(getHomeIntent(login));
        }
    }

    @Override
    public void onClick(View v) {

        String login;

        if (TextUtils.isEmpty(mLoginEditText.getText())){
            Toast.makeText(this, R.string.error_no_login, Toast.LENGTH_LONG).show(); //Ici l'objet this=l'activity
            return;
        }
        if (TextUtils.isEmpty(mPasswordEditText.getText())) {
            Toast.makeText(this, R.string.error_no_password, Toast.LENGTH_LONG).show(); //Ici l'objet this=l'activity
            return;
        }
        login= mLoginEditText.getText().toString();
        PreferenceUtils.setLogin(login); //Stocker le login dans les préférences

        startActivity(getHomeIntent(login));
    }

    private Intent getHomeIntent(String userName)
    {
        Intent intent = new Intent(this, MainActivity.class); //Après saisie des login et password : on est redirigé vers une autre page (prochaine activity)
        final Bundle extras= new Bundle(); //Bundle = permet de transmettre des infos d'une page à une autre, ici le login qu'on affiche dans la 2ème page
        extras.putString(Constants.Login.EXTRA_LOGIN, userName); //Pour voir pour l'affichage, cf TwitterActivity
        intent.putExtras(extras);

        return intent;
    }
}
