package com.reo.lingo;

import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by Warena on 11/02/2018.
 */

public class Question2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ImageView word1;
    private ImageView word2;
    private ImageView word3;
    private ImageView word4;

    public void setupButtons() {
        check = (Button) this.findViewById(R.id.check);
        check.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (highlighted.getId() == R.id.tile1) {
                    if (a1.getCorrect()) {
                        rightAnswer = true;
                        MediaPlayer mp = MediaPlayer.create(Question2Activity.this, R.raw.beep);
                        mp.start();
                    } else {
                        rightAnswer = false;
                    }
                }
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }


}
