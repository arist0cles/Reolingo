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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RelativeLayout tile1;
    private RelativeLayout tile2;
    private RelativeLayout tile3;
    private RelativeLayout tile4;

    private RelativeLayout highlighted;

    private AnswerTile a1;
    private AnswerTile a2;
    private AnswerTile a3;
    private AnswerTile a4;

    private boolean rightAnswer;
    private QuestionActivity ques = this;
    private ProgressBar progress;
    private Button check;

    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private ImageView image4;

    private TextView text1;
    private TextView text2;
    private TextView text3;
    private TextView text4;

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
        check = (Button) this.findViewById(R.id.check);
        check.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(highlighted.getId() == R.id.tile1){
                    if (a1.getCorrect()) {
                        rightAnswer = true;
                        MediaPlayer mp = MediaPlayer.create(QuestionActivity.this, R.raw.beep);
                        mp.start();
                    } else {
                        rightAnswer = false;
                    }
                }
                if(highlighted.getId() == R.id.tile2){
                    if (a2.getCorrect()) {
                        rightAnswer = true;
                        MediaPlayer mp = MediaPlayer.create(QuestionActivity.this, R.raw.beep);
                        mp.start();
                    } else {
                        rightAnswer = false;
                    }
                }
                if(highlighted.getId() == R.id.tile3){
                    if (a3.getCorrect()) {
                        rightAnswer = true;
                        MediaPlayer mp = MediaPlayer.create(QuestionActivity.this, R.raw.beep);
                        mp.start();
                    } else {
                        rightAnswer = false;
                    }
                }
                if(highlighted.getId() == R.id.tile4){
                    if (a4.getCorrect()) {
                        rightAnswer = true;
                        MediaPlayer mp = MediaPlayer.create(QuestionActivity.this, R.raw.beep);
                        mp.start();
                    } else {
                        rightAnswer = false;
                    }
                }
            buttonChosen(a1.getAnswer());
            }
        });

        image1 = (ImageView) this.findViewById(R.id.image1);
        image2 = (ImageView) this.findViewById(R.id.image2);
        image3 = (ImageView) this.findViewById(R.id.image3);
        image4 = (ImageView) this.findViewById(R.id.image4);

        tile1 = (RelativeLayout) this.findViewById(R.id.tile1);
        tile2 = (RelativeLayout) this.findViewById(R.id.tile2);
        tile3 = (RelativeLayout) this.findViewById(R.id.tile3);
        tile4 = (RelativeLayout) this.findViewById(R.id.tile4);

        tile1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                unhighlight();
                highlight(tile1);
//                if (a1.getCorrect()) {
//                    rightAnswer = true;
//                } else
//                    rightAnswer = false;
//                buttonChosen(a1.getAnswer());
                MediaPlayer mp = MediaPlayer.create(QuestionActivity.this, a1.getSound());
                mp.start();
            }
        });

        tile2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                unhighlight();
                highlight(tile2);
//                if (a2.getCorrect()) {
//                    rightAnswer = true;
//                } else
//                    rightAnswer = false;
//                buttonChosen(a2.getAnswer());
                MediaPlayer mp = MediaPlayer.create(QuestionActivity.this, a2.getSound());
                mp.start();
            }
        });

        tile3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                unhighlight();
                highlight(tile3);
//                if (a3.getCorrect()) {
//                    rightAnswer = true;
//                } else
//                    rightAnswer = false;
//                buttonChosen(a3.getAnswer());
                MediaPlayer mp = MediaPlayer.create(QuestionActivity.this, a3.getSound());
                mp.start();
            }
        });

        tile4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                unhighlight();
                highlight(tile4);
//                if (a4.getCorrect()) {
//                    rightAnswer = true;
//                } else
//                    rightAnswer = false;
//                buttonChosen(a4.getAnswer());
                MediaPlayer mp = MediaPlayer.create(QuestionActivity.this, a4.getSound());
                mp.start();
            }
        });

    }

    public void highlight(RelativeLayout tile){
        tile.setBackground(getResources().getDrawable(R.drawable.background_border));
        tile.setBackgroundColor(getResources().getColor(R.color.red));
        highlighted = tile;
    }

    public void unhighlight(){
        tile1.setBackgroundColor(getResources().getColor(R.color.white));
        tile2.setBackgroundColor(getResources().getColor(R.color.white));
        tile3.setBackgroundColor(getResources().getColor(R.color.white));
        tile4.setBackgroundColor(getResources().getColor(R.color.white));

        tile1.setBackground(getResources().getDrawable(R.drawable.background_border));
        tile2.setBackground(getResources().getDrawable(R.drawable.background_border));
        tile3.setBackground(getResources().getDrawable(R.drawable.background_border));
        tile4.setBackground(getResources().getDrawable(R.drawable.background_border));
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
        text1 = (TextView) this.findViewById(R.id.text1);
        text2 = (TextView) this.findViewById(R.id.text2);
        text3 = (TextView) this.findViewById(R.id.text3);
        text4 = (TextView) this.findViewById(R.id.text4);

        text1.setText(a1.getAnswer());
        text2.setText(a2.getAnswer());
        text3.setText(a3.getAnswer());
        text4.setText(a4.getAnswer());

        image1 = (ImageView) this.findViewById(R.id.image1);
        image2 = (ImageView) this.findViewById(R.id.image2);
        image3 = (ImageView) this.findViewById(R.id.image3);
        image4 = (ImageView) this.findViewById(R.id.image4);

        image1.setBackground(this.getResources().getDrawable(a1.getImage()));
        image2.setBackground(this.getResources().getDrawable(a2.getImage()));
        image3.setBackground(this.getResources().getDrawable(a3.getImage()));
        image4.setBackground(this.getResources().getDrawable(a4.getImage()));
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

                    }
                        }
                );
        AlertDialog dialog = builder.create();
        dialog.show();

    }
    }

