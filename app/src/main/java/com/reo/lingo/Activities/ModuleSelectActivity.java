package com.reo.lingo.Activities;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;

import com.reo.lingo.Helpers.JSONHelper;
import com.reo.lingo.Helpers.TextHelper;
import com.reo.lingo.Models.Questions.FourTileQuestion;
import com.reo.lingo.Models.Questions.Question;
import com.reo.lingo.Models.Topic;
import com.reo.lingo.R;

import java.util.ArrayList;
import java.util.List;

import static com.reo.lingo.Activities.MainActivity.balance2DP;
import static com.reo.lingo.Activities.MainActivity.getMoneyColor;

/**
 * Created by patrick on 17/03/18.
 */

public class ModuleSelectActivity extends AppCompatActivity {

    private String title;
    private JSONHelper jsonHelper = new JSONHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(Html.fromHtml("<font color='"+getMoneyColor()+"'>$"+balance2DP()+" </font>"));

        setContentView(R.layout.module_selector);

        Intent intent = getIntent();

        Topic topic = intent.getParcelableExtra("topic");

        setupListeners();

        title = TextHelper.getCorrectName(topic.getName());
        //setTitle(title);

        ImageView i1 = null;
        ImageView i2 = null;
        ImageView i3 = null;
        ImageView i4 = null;

        CardView c2 = (CardView)findViewById(R.id.module2);
        c2.setCardBackgroundColor(Color.argb(100,100,100,100));
        CardView c3 = (CardView)findViewById(R.id.module3);
        c3.setCardBackgroundColor(Color.argb(100,100,100,100));

        switch (title) {
            case "Mealtime" :
                i1 = (ImageView)findViewById(R.id.module1_image);
                i2 = (ImageView)findViewById(R.id.module2_image);
                i3 = (ImageView)findViewById(R.id.module3_image);
                i4 = (ImageView)findViewById(R.id.module4_image);
                i1.setImageDrawable(getResources().getDrawable(R.drawable.bread));
                i2.setImageDrawable(getResources().getDrawable(R.drawable.coffee));
                i3.setImageDrawable(getResources().getDrawable(R.drawable.cup));
                i4.setImageDrawable(getResources().getDrawable(R.drawable.toast));
                break;
            case "Getting Dressed" :
                i1 = (ImageView)findViewById(R.id.module1_image);
                i2 = (ImageView)findViewById(R.id.module2_image);
                i3 = (ImageView)findViewById(R.id.module3_image);
                i4 = (ImageView)findViewById(R.id.module4_image);
                i1.setImageDrawable(getResources().getDrawable(R.drawable.jacket));
                i2.setImageDrawable(getResources().getDrawable(R.drawable.hat));
                i3.setImageDrawable(getResources().getDrawable(R.drawable.gloves));
                i4.setImageDrawable(getResources().getDrawable(R.drawable.washing_machine));
                break;
        }
    }

    public void setupListeners() {
        CardView module1 = (CardView)findViewById(R.id.module1);
        CardView module4 = (CardView)findViewById(R.id.module4);

        module1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askQuestion(jsonHelper.parseModule(R.raw.mealtime_lvl_1, getResources()).getQuestions());
            }
        });

        module4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askQuestion(jsonHelper.parseModule(R.raw.getting_dressed_lvl_4, getResources()).getQuestions());
            }
        });
    }

    private void askQuestion(List<Question> questions) {
        for(Question question : questions) {
            if(question.getClass() == FourTileQuestion.class) {
                FourTileQuestion ftq = (FourTileQuestion)question;
                ftq.makeTiles();
            }
        }
        Intent intent = new Intent(this, ModuleActivity.class);
        Bundle bundle = new Bundle();
        ArrayList<Question> q = (ArrayList<Question>) questions;
        bundle.putParcelableArrayList("questions", q);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
