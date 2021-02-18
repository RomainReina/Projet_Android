package com.example.projet_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.projet_android.utils.Constants;
import com.example.projet_android.utils.PreferenceUtils;

public class MainActivity extends AppCompatActivity {
    //Coucou les copains
    @Override
    public void onRetweet(Tweet tweet) {
        Toast.makeText(this, tweet.text, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onViewTweet(Tweet tweet) {
        //Toast.makeText(this, tweet.text, Toast.LENGTH_LONG).show();
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        final TweetDetailFragment fragment = TweetDetailFragment.newInstance(tweet.text, tweet.user.profileImageUrl, tweet.user.name, tweet.user.screenName);
        transaction.add(R.id.container,fragment);
        transaction.setCustomAnimations(R.animator.slide_in_right,0,0,R.animator.slide_out_left);
        transaction.addToBackStack(null).commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Intent intent = getIntent();
        if(intent!=null){
            final Bundle extras = intent.getExtras();
            if(extras!=null){
                final String login = extras.getString(Constants.Login.EXTRA_LOGIN);
                getSupportActionBar().setSubtitle(login);


            }
        }
        if(savedInstanceState==null)
        {
            getSupportFragmentManager().beginTransaction().add(R.id.container,new TweetsFragmentWithRecyclerView()).commit();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.wltwitter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id ==R.id.actionLogout)
        {
            PreferenceUtils.setLogin(null);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
}