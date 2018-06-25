package com.reo.lingo.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.reo.lingo.Models.BlanksQuestion;
import com.reo.lingo.Models.EnglishMaoriTranslateQuestion;
import com.reo.lingo.Models.FourTileQuestion;
import com.reo.lingo.Models.Question;
import com.reo.lingo.Models.MaoriEnglishTranslateQuestion;
import com.reo.lingo.R;
import com.reo.lingo.ReoApplication;

import java.util.ArrayList;
import java.util.List;

import jsonobj.Module;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private int studyTime;
    private Button fiveMinButton;
    private Button tenMinButton;
    private Button fifthteenMinButton;
    private Button twentyMinButton;
    private MainActivity main;
    public static int counter = 0;
    public static int wrongCounter = 0;
    public static int rightCounter = 0;

    ReoApplication mApplication;

    List<Question> questions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        main = this;

        mApplication = new ReoApplication(this);

        Module moduleOne = mApplication.loadModuleFromFile(R.raw.mealtime);
        Module moduleTwo = mApplication.loadModuleFromFile(R.raw.moduletwo);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean previouslyStarted = prefs.getBoolean(getString(R.string.pref_previously_started), false);

        SharedPreferences.Editor edit = prefs.edit();
        edit.putBoolean(getString(R.string.pref_previously_started), Boolean.TRUE);
        edit.commit();

        Intent intent = getIntent();
        if (intent != null) {
            String StrData = intent.getStringExtra("topic_name");
            if (StrData != null){
                if (StrData.equals("Mealtime")){
                    questions = getQuestions(moduleOne);
                } else {
                    questions = getQuestions(moduleTwo);
                }
                askQuestion();
            } else {
                if (previouslyStarted) {
                    Intent i = new Intent(MainActivity.this, TopicSelectActivity.class);
                    startActivity(i);
                }
            }
        }
    }

    public List<Question> getQuestions(Module m){
        questions = new ArrayList<>();
        for (jsonobj.Question q : m.questions){
            switch(q.type){
                case "FOUR_TILE_QUESTION":
                    questions.add(new FourTileQuestion(q.correctEnglish, q.correctMaori, q.sentence, q.options, q.milestone));
                    break;
                case "MAORI_ENGLISH_TRANSLATE_QUESTION":
                    questions.add(new MaoriEnglishTranslateQuestion(q.correctEnglish, q.correctMaori, q.sentence, q.options, q.milestone));
                    break;
                case "ENGLISH_MAORI_TRANSLATE_QUESTION":
                    questions.add(new EnglishMaoriTranslateQuestion(q.correctEnglish, q.correctMaori, q.sentence, q.options, q.milestone));
                    break;
                case "BLANKS_QUESTION":
                    questions.add(new BlanksQuestion(q.correctEnglish, q.correctMaori, q.sentence, q.options, q.milestone));
                    break;
            }
        }
        return questions;
    }

    public void setupButtons() {
        fiveMinButton = (Button) this.findViewById(R.id.fivemins);
        tenMinButton = (Button) this.findViewById(R.id.tenmins);
        fifthteenMinButton = (Button) this.findViewById(R.id.fifthteenmins);
        twentyMinButton = (Button) this.findViewById(R.id.twentymins);

        fiveMinButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                studyTimeChosen(5);
            }
        });

        tenMinButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                studyTimeChosen(10);
            }
        });

        fifthteenMinButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                studyTimeChosen(15);
            }
        });

        twentyMinButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                studyTimeChosen(20);
            }
        });
    }

    public void studyTimeChosen(int time) {
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.beep);
        mp.start();
        studyTime = time;

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Ka Pai, I'll do my best to help you get to " + studyTime + " minutes a day")
                .setTitle("Chur")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface i, int j) {
                                askQuestion();
                            }
                        }
                );
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void askQuestion(){
        ArrayList<Question> q = (ArrayList<Question>) questions;
        Intent intent = new Intent(main, ModuleActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("questions", q);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}