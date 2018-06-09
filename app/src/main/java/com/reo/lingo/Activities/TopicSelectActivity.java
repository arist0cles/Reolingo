package com.reo.lingo.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.nfc.cardemulation.CardEmulation;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

import com.reo.lingo.R;

/**
 * Created by patrick on 17/03/18.
 */

public class TopicSelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topic_selector);

        CardView mealtime = (CardView)findViewById(R.id.mealtime_card);
        CardView getting_dressed = (CardView)findViewById(R.id.getting_dressed_card);
        CardView sport = (CardView)findViewById(R.id.sport_card);
        sport.setCardBackgroundColor(Color.argb(5,200,200,200));

        mealtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TopicSelectActivity.this, ModuleSelectActivity.class);
                //TODO: attach name of topic
                i.putExtra("topic_name", "mealtime");
                startActivity(i);
            }
        });

        getting_dressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TopicSelectActivity.this, ModuleSelectActivity.class);
                //TODO: attach name of topic
                i.putExtra("topic_name", "getting_dressed");
                startActivity(i);
            }
        });
    }


}
