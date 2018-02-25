package com.reo.lingo.Activities;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.reo.lingo.Fragments.FourTileQuestionFragment;
import com.reo.lingo.Fragments.TranslateQuestionFragment;
import com.reo.lingo.Models.FourTileQuestion;
import com.reo.lingo.Models.Question;
import com.reo.lingo.Parceable.AnswerTile;
import com.reo.lingo.R;

import java.util.List;

public class ModuleActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int QUESTION_PROGRESS_AMOUNT = 10;

    private ModuleActivity ques = this;
    private ProgressBar progress;
    private Button check;

    private List<Question> questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.question_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            questions = getIntent().getParcelableArrayListExtra("questions");
            //TODO: Parse questions and give startQuestions the first one
            if(questions != null){
                start(questions.get(0));
            }

            setProgress();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void start(Question q){
        // Create new fragment and transaction
        android.support.v4.app.Fragment newFragment = q.getFragment();
        Bundle bundle = q.getBundle();

        //TODO: Attach answertiles to bundle
        newFragment.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(R.id.fragment_placeholder, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }

    public void setProgress(){
        progress = (ProgressBar) this.findViewById(R.id.progress);
        if (android.os.Build.VERSION.SDK_INT >= 24) {
            progress.setProgress(MainActivity.counter*QUESTION_PROGRESS_AMOUNT, true);
        } else {
            progress.setProgress(MainActivity.counter*QUESTION_PROGRESS_AMOUNT);
        }
    }

    public void buttonChosen(String name) {
//        if (rightAnswer) {
//            showCorrect(name);
//        } else {
//            showIncorrect(name);
//        }
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
        getMenuInflater().inflate(R.menu.question, menu);
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
    }

