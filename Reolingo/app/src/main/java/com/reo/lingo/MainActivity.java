package com.reo.lingo;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
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
import android.widget.EditText;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private int studyTime;
    private Button fiveMinButton;
    private Button tenMinButton;
    private Button fifthteenMinButton;
    private Button twentyMinButton;
    private MainActivity main;

    //TODO: needs changing to the right sounds, all beeps ATM
    private AnswerTile tile4 = new AnswerTile("Ngeru", R.mipmap.cat, R.raw.beep, false);
    private AnswerTile tile2 = new AnswerTile("Tama", R.mipmap.boy, R.raw.beep, false);
    private AnswerTile tile3 = new AnswerTile("Kuri", R.mipmap.dog, R.raw.beep, false);
    private AnswerTile tile1 = new AnswerTile("Kotiro", R.mipmap.girl, R.raw.beep, true);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        main = this;

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean previouslyStarted = prefs.getBoolean(getString(R.string.pref_previously_started), false);
        if(previouslyStarted) {
            Intent intent = new Intent(main, QuestionActivity.class);
            intent.putExtra("questionNum", "0");
            intent.putExtra("tile1", tile1);
            intent.putExtra("tile2", tile2);
            intent.putExtra("tile3", tile3);
            intent.putExtra("tile4", tile4);
            startActivity(intent);
        }
        SharedPreferences.Editor edit = prefs.edit();
        edit.putBoolean(getString(R.string.pref_previously_started), Boolean.TRUE);
        edit.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setupButtons();

    }

    public void setupButtons(){
        fiveMinButton = (Button) this.findViewById(R.id.fivemins);
        tenMinButton = (Button) this.findViewById(R.id.tenmins);
        fifthteenMinButton = (Button) this.findViewById(R.id.fifthteenmins);
        twentyMinButton = (Button) this.findViewById(R.id.twentymins);

        fiveMinButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //play success sound
                //popup dialog
                //on dialog close start asking questions
                studyTimeChosen(5);
            }
        });

        tenMinButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                studyTimeChosen(10);
            }
        });

        fifthteenMinButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                studyTimeChosen(15);
            }
        });

        twentyMinButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                studyTimeChosen(20);
            }
        });
    }

    public void studyTimeChosen(int time){
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.beep);
        mp.start();
        studyTime = time;

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Ka Pai, I'll do my best to help you get to "+studyTime+" minutes a day")
                .setTitle("Chur")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface i, int j){
                                Intent intent = new Intent(main, QuestionActivity.class);
                                intent.putExtra("questionNum", "0");
                                intent.putExtra("tile1", tile1);
                                intent.putExtra("tile2", tile2);
                                intent.putExtra("tile3", tile3);
                                intent.putExtra("tile4", tile4);

                                startActivity(intent);
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

    public void createTiles(){

    }
}
