package com.reo.lingo;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.content.Context;
import android.os.Bundle;
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
import android.widget.ProgressBar;
import android.widget.Toast;

public class QuestionActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Button tamaButton;
    private Button kotiroButton; //the right answer
    private Button ngeruButton;
    private Button kuriButton;
    private boolean rightAnswer;
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
            String value = extras.getString("questionNum");
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
    }

    public void setupButtons() {
        tamaButton = (Button) this.findViewById(R.id.tama);
        kotiroButton = (Button) this.findViewById(R.id.kotiro);
        ngeruButton = (Button) this.findViewById(R.id.ngeru);
        kuriButton = (Button) this.findViewById(R.id.kuri);

        tamaButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                rightAnswer = false;
                buttonChosen("Tama");
                MediaPlayer tama = MediaPlayer.create(QuestionActivity.this,R.raw.tama);
                tama.start();
            }
        });

        kotiroButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    rightAnswer = true;
                    progressCounter = progressCounter + 1;
                    MediaPlayer kotiro = MediaPlayer.create(QuestionActivity.this,R.raw.kotiro);
                    kotiro.start();
                    buttonChosen("Kotiro");

            }
        }
        );

        ngeruButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                rightAnswer = false;
                MediaPlayer ngeru = MediaPlayer.create(QuestionActivity.this,R.raw.ngeru);
                ngeru.start();
                buttonChosen("Ngeru");
            }
        });

        kuriButton.setOnClickListener(new View.OnClickListener() {
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
                                    //Intent intent = new Intent(main, QuestionActivity.class);
                                    // startActivity(intent);
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
}
