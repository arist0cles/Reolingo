package com.reo.lingo.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.nfc.cardemulation.CardEmulation;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.view.View;

import com.reo.lingo.Models.Topic;
import com.reo.lingo.R;

import static com.reo.lingo.Activities.MainActivity.balance;
import static com.reo.lingo.Activities.MainActivity.balance2DP;
import static com.reo.lingo.Activities.MainActivity.getMoneyColor;

/**
 * Created by patrick on 17/03/18.
 */

public class TopicSelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(Html.fromHtml("<font color='"+getMoneyColor()+"'>$"+balance2DP()+" </font>"));

        setContentView(R.layout.topic_selector);

        createCards();
    }

    private void createCards() {
        CardView mealTime = (CardView)findViewById(R.id.mealtime_card);
        CardView gettingDressed = (CardView)findViewById(R.id.getting_dressed_card);

        //sport is blanked out
        CardView sport = (CardView)findViewById(R.id.sport_card);
        sport.setCardBackgroundColor(Color.argb(100,100,100,100));

        setupCardListeners(mealTime, gettingDressed);
    }

    private void setupCardListeners(CardView mealTime, CardView gettingDressed) {
        final Intent i = new Intent(TopicSelectActivity.this, ModuleSelectActivity.class);

        mealTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Topic t = new Topic("mealtime");
                i.putExtra("topic", t);
                startActivity(i);
            }
        });

        gettingDressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Topic t = new Topic("getting_dressed");
                i.putExtra("topic", t);
                startActivity(i);
            }
        });
    }
}
