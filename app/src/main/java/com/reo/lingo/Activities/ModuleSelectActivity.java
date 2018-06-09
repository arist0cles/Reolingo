package com.reo.lingo.Activities;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;

import com.reo.lingo.R;

/**
 * Created by patrick on 17/03/18.
 */

public class ModuleSelectActivity extends AppCompatActivity {

    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_selector);

        Intent intent = getIntent();

        String topic = intent.getStringExtra("topic_name");

        Resources res = getResources();
        title = res.getString(res.getIdentifier(topic, "string", getPackageName()));

        CardView module1 = (CardView)findViewById(R.id.module1);
        CardView module2 = (CardView)findViewById(R.id.module2);

        ImageView i1 = null;
        ImageView i2 = null;

        switch (title) {
            case "Mealtime" :
                i1 = (ImageView)findViewById(R.id.module1_image);
                i2 = (ImageView)findViewById(R.id.module2_image);
                i1.setImageDrawable(getResources().getDrawable(R.drawable.bread));
                i2.setImageDrawable(getResources().getDrawable(R.drawable.coffee));
                break;
            case "Getting Dressed" :
                i1 = (ImageView)findViewById(R.id.module1_image);
                i2 = (ImageView)findViewById(R.id.module2_image);
                i1.setImageDrawable(getResources().getDrawable(R.drawable.hat));
                i2.setImageDrawable(getResources().getDrawable(R.drawable.jacket));
                break;
        }

        setTitle(title);



        module1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ModuleSelectActivity.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("topic_name", title);
                bundle.putString("module_name", "1");
                i.putExtras(bundle);
                startActivity(i);
            }
        });

        module2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ModuleSelectActivity.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("topic_name", title);
                bundle.putString("module_name", "2");
                i.putExtras(bundle);
                startActivity(i);
                startActivity(i);
            }
        });
    }
}
