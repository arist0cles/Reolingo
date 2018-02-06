package com.reo.lingo;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.content.Context;
import android.os.Bundle;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Button button1;
    private Button button2; //the right answer
    private Button button3;
    private Button button4;
    private AnswerTile a1;
    private AnswerTile a2;
    private AnswerTile a3;
    private AnswerTile a4;
    private boolean rightAnswer;
    private QuestionActivity ques = this;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_question);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            a1 = (AnswerTile) getIntent().getParcelableExtra("tile1");
            a2 = (AnswerTile) getIntent().getParcelableExtra("tile2");
            a3 = (AnswerTile) getIntent().getParcelableExtra("tile3");
            a4 = (AnswerTile) getIntent().getParcelableExtra("tile4");

                String title = (String) extras.get("questionTitle");
                TextView t = (TextView) this.findViewById(R.id.questionTitle);
                t.setText(title);


            progress = (ProgressBar) this.findViewById(R.id.progress);
            if (android.os.Build.VERSION.SDK_INT >= 24) {
                progress.setProgress(MainActivity.counter*25, true);
            } else {
                progress.setProgress(MainActivity.counter*25);
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setupButtons();
        setupTiles(a1, a2, a3, a4);
    }

    public void setupButtons() {
        button1 = (Button) this.findViewById(R.id.button1);
        button2 = (Button) this.findViewById(R.id.button2);
        button3 = (Button) this.findViewById(R.id.button3);
        button4 = (Button) this.findViewById(R.id.button4);

        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (a1.getCorrect()) {
                    rightAnswer = true;
                } else
                    rightAnswer = false;
                buttonChosen(a1.getAnswer());
                MediaPlayer mp = MediaPlayer.create(QuestionActivity.this, a1.getSound());
                mp.start();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (a2.getCorrect()) {
                    rightAnswer = true;
                } else
                    rightAnswer = false;
                buttonChosen(a2.getAnswer());
                MediaPlayer mp = MediaPlayer.create(QuestionActivity.this, a2.getSound());
                mp.start();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (a3.getCorrect()) {
                    rightAnswer = true;
                } else
                    rightAnswer = false;
                buttonChosen(a3.getAnswer());
                MediaPlayer mp = MediaPlayer.create(QuestionActivity.this, a3.getSound());
                mp.start();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (a4.getCorrect()) {
                    rightAnswer = true;
                } else
                    rightAnswer = false;
                buttonChosen(a4.getAnswer());
                MediaPlayer mp = MediaPlayer.create(QuestionActivity.this, a4.getSound());
                mp.start();
            }
        });

    }

    public void buttonChosen(String name) {
        if (rightAnswer) {
            showCorrect(name);


        } else {
            showIncorrect(name);
        }
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

    private void setupTiles(AnswerTile a1, AnswerTile a2, AnswerTile a3, AnswerTile a4){
        button1.setText(a1.getAnswer());
        button2.setText(a2.getAnswer());
        button3.setText(a3.getAnswer());
        button4.setText(a4.getAnswer());

        button1.setBackground(this.getResources().getDrawable(a1.getImage()));
        button2.setBackground(this.getResources().getDrawable(a2.getImage()));
        button3.setBackground(this.getResources().getDrawable(a3.getImage()));
        button4.setBackground(this.getResources().getDrawable(a4.getImage()));
    }
      public void showIncorrect(String name) {
          MainActivity.counter++;
          MainActivity.wrongCounter++;
          String correctWord = "Nah";
          if(a1.getCorrect()){
              correctWord = a1.getAnswer();
          }
          if(a2.getCorrect()){
              correctWord = a2.getAnswer();
          }
          if(a3.getCorrect()){
              correctWord = a3.getAnswer();
          }
          if(a4.getCorrect()){
              correctWord = a4.getAnswer();
          }
          AlertDialog.Builder builder = new AlertDialog.Builder(QuestionActivity.this, R.style.WrongDialogTheme);
          builder.setMessage("That was incorrect. The correct answer was " + correctWord)
                  .setTitle("Aue")
                  .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                              public void onClick(DialogInterface i, int j) {
                                  Intent intent = new Intent(ques, MainActivity.class);
                                  startActivity(intent);
                              }
                          }
                  );
          AlertDialog dialog = builder.create();
          dialog.show();

      }
    public void showCorrect(String name){
        MainActivity.counter++;
        MainActivity.rightCounter++;
    AlertDialog.Builder builder = new AlertDialog.Builder(QuestionActivity.this, R.style.RightDialogTheme);
        builder.setMessage("Correct. Your progress score has increased to " + MainActivity.rightCounter)
            .setTitle("Ka Pai!")

                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface i, int j) {
                        Intent intent = new Intent(ques, MainActivity.class);
                        startActivity(intent);
                        //ques.finish();
                    }
                        }
                );
        AlertDialog dialog = builder.create();
        dialog.show();

    }
    }