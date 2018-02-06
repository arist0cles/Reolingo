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
    private boolean rightAnswer;
    private AnswerTile a1;
    private AnswerTile a2;
    private AnswerTile a3;
    private AnswerTile a4;
    private QuestionActivity ques = this;

    public int progressCounter = 0;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_question);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            a1 = (AnswerTile)getIntent().getParcelableExtra("tile1");
            a2 = (AnswerTile)getIntent().getParcelableExtra("tile2");
            a3 = (AnswerTile)getIntent().getParcelableExtra("tile3");
            a4 = (AnswerTile)getIntent().getParcelableExtra("tile4");


            String value = extras.getString("questionNum");

            if(value.equals("0")){
                TextView t = (TextView) this.findViewById(R.id.questionTitle);
                t.setText("Which of these is 'the girl'?");
            }
            if(value.equals("1")){
                TextView t = (TextView) this.findViewById(R.id.questionTitle);
                t.setText("Which of these is 'the boy'?");
            }

            progress = (ProgressBar) this.findViewById(R.id.progress);
            if(android.os.Build.VERSION.SDK_INT >= 24){
                progress.setProgress(Integer.parseInt(value), true);
            } else {
                progress.setProgress(Integer.parseInt(value));
            }

            //Just for testing, delete later on
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, value, duration);
            toast.show();
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
                rightAnswer = false;
                buttonChosen("Tama");
                MediaPlayer tama = MediaPlayer.create(QuestionActivity.this,R.raw.tama);
                tama.start();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    rightAnswer = true;
                    progressCounter = progressCounter + 1;
                    MediaPlayer kotiro = MediaPlayer.create(QuestionActivity.this,R.raw.kotiro);
                    kotiro.start();
                    buttonChosen("Kotiro");

            }
        }
        );

        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                rightAnswer = false;
                MediaPlayer ngeru = MediaPlayer.create(QuestionActivity.this,R.raw.ngeru);
                ngeru.start();
                buttonChosen("Ngeru");
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                rightAnswer = false;
                MediaPlayer kuri = MediaPlayer.create(QuestionActivity.this,R.raw.kuri);
                kuri.start();
                buttonChosen("Kuri");
            }
        });
    }



    public void buttonChosen(String name) {

        if(rightAnswer == false) {
            AlertDialog.Builder builder = new AlertDialog.Builder(QuestionActivity.this);
            builder.setMessage(name + " is incorrect. The correct answer was Kotiro")
                    .setTitle("Aue")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface i, int j) {
                                    AnswerTile tile4 = new AnswerTile("Wa", R.mipmap.boy, R.raw.beep, false);
                                    AnswerTile tile2 = new AnswerTile("Louie", R.mipmap.girl, R.raw.beep, false);
                                    AnswerTile tile3 = new AnswerTile("Blah", R.mipmap.dog, R.raw.beep, false);
                                    AnswerTile tile1 = new AnswerTile("Test", R.mipmap.girl, R.raw.beep, true);

                                    Intent intent = new Intent(ques, QuestionActivity.class);
                                    startActivity(intent);
                                    intent.putExtra("questionNum", "1");
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

        if (rightAnswer == true) {

            AlertDialog.Builder builder = new AlertDialog.Builder(QuestionActivity.this);
            builder.setMessage(name + " is Correct. Your progress score has increased to " + progressCounter)
                    .setTitle("Ka Pai!")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface i, int j) {
                                    //Intent intent = new Intent(main, QuestionActivity.class);
                                    // startActivity(intent);
                                }
                            }
                    );
            AlertDialog dialog = builder.create();
            dialog.show();
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
}
