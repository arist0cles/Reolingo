package com.reo.lingo.Activities;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
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
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.reo.lingo.Fragments.BlanksQuestionFragment;
import com.reo.lingo.Fragments.EnglishMaoriTranslateQuestionFragment;
import com.reo.lingo.Fragments.FourTileQuestionFragment;
import com.reo.lingo.Fragments.MaoriEnglishTranslateQuestionFragment;
import com.reo.lingo.Fragments.ModuleFragment;
import com.reo.lingo.Models.Question;
import com.reo.lingo.R;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ModuleActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int QUESTION_PROGRESS_AMOUNT = 10;

    private ModuleActivity ques = this;
    private ProgressBar progress;
    private Button check;

    private List<Question> questions;

    private android.support.v4.app.Fragment currentFragment;
    private Question currentQuestion;
    private Context currentContext;
    private long startMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startMillis = System.currentTimeMillis();

        setContentView(R.layout.question_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            questions = getIntent().getParcelableArrayListExtra("questions");

            if(questions != null){
                currentQuestion = questions.get(0);
                start(questions.get(0));
                //setupModuleButtons();
            }

            setupCheckButton();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void setupModuleButtons() {
        RelativeLayout button1 = (RelativeLayout) currentFragment.getView().findViewById(R.id.module1);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //questions = moduleOne;
                currentQuestion = questions.get(0);
                start(questions.get(0));
            }
        });

        RelativeLayout button2 = (RelativeLayout) this.findViewById(R.id.module2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //questions = moduleOne;
                currentQuestion = questions.get(0);
                start(questions.get(0));
            }
        });
    }

    public void setupCheckButton(){
        check = (Button) this.findViewById(R.id.check);
        check.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                currentContext = v.getContext();
                if(isCorrect()){
                    if(isMilestone()){
                        showCorrectMilestone();
                    }else {
                        showCorrect();
                    }
                } else {
                    showIncorrect();
                }
            }
        });
    }

    public boolean isCorrect(){
        //TODO: Ah this needs to be rewritten for new Question types
        //Fuck you Zach, I'm going to use InstanceOf
        String selected;
        String correctAnswer;
        if(currentFragment.getClass() == FourTileQuestionFragment.class){
            FourTileQuestionFragment f = (FourTileQuestionFragment) currentFragment;
            selected = f.getSelected();
            correctAnswer = currentQuestion.getCorrectEnglish();
        } else if (currentFragment.getClass() == MaoriEnglishTranslateQuestionFragment.class){
            MaoriEnglishTranslateQuestionFragment f = (MaoriEnglishTranslateQuestionFragment) currentFragment;
            selected = f.getSelected();
            correctAnswer = currentQuestion.getCorrectEnglish();
        } else if (currentFragment.getClass() == EnglishMaoriTranslateQuestionFragment.class){
            EnglishMaoriTranslateQuestionFragment f = (EnglishMaoriTranslateQuestionFragment) currentFragment;
            selected = f.getSelected();
            correctAnswer = currentQuestion.getCorrectMaori();
        } else if (currentFragment.getClass() == BlanksQuestionFragment.class){
            BlanksQuestionFragment f = (BlanksQuestionFragment) currentFragment;
            selected = f.getSelected();
            correctAnswer = currentQuestion.getCorrectMaori();
        }
        else {
            return false;
        }

        if(correctAnswer.equalsIgnoreCase(selected)){
            return true;
        }

        return false;
    }

    public boolean isMilestone(){
        if(currentQuestion.isMilestone()){
            return true;
        }

        return false;
    }

    public void showIncorrect() {
        final Context currentContext = this.currentContext;
        MainActivity.counter++;
        MainActivity.wrongCounter++;

        MediaPlayer incorrect = MediaPlayer.create(ModuleActivity.this, R.raw.incorrect);
        incorrect.start();

        AlertDialog.Builder builder = new AlertDialog.Builder(currentContext, R.style.WrongDialogTheme);
        builder.setMessage("That was incorrect. The correct answer was ")
                .setTitle("Aue")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface i, int j) {
                                setProgressBar(Color.RED);

                                currentQuestion = questions.get(MainActivity.counter);
                                start(questions.get(MainActivity.counter));
                            }
                        }
                );
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void showCorrectMilestone(){
        final Context currentContext = this.currentContext;
        MainActivity.counter++;
        MainActivity.rightCounter++;

        MediaPlayer correct = MediaPlayer.create(ModuleActivity.this, R.raw.correct);
        correct.start();

        AlertDialog.Builder builder = new AlertDialog.Builder(currentContext, R.style.RightDialogTheme);
        //Pop up figure here
        builder.setMessage("Great job, you're halfway there!!!")
                .setTitle("Ka Mau Te WEHI!")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface i, int j) {
                                setProgressBar(Color.GREEN);
                                currentQuestion = questions.get(MainActivity.counter);
                                start(questions.get(MainActivity.counter));
                            }
                        }
                );
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void showFinal(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ModuleActivity.this);
                double math = (double) MainActivity.rightCounter/ (double) MainActivity.counter*100;
                int divide = (int) math;
                long millis = System.currentTimeMillis();
                long timeTaken = (millis - startMillis);

                builder.setMessage("Your overall score was "+ divide +"%, you took " +
                        String.format("%02d min, %02d sec",
                                TimeUnit.MILLISECONDS.toMinutes(timeTaken),
                                TimeUnit.MILLISECONDS.toSeconds(timeTaken) -
                                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeTaken))
                        ))
                        .setTitle("Ka Mau Te WEHI!")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface i, int j) {
                                        MainActivity.counter = 0;
                                        MainActivity.wrongCounter = 0;
                                        MainActivity.rightCounter = 0;
                                        setProgressBar(Color.BLACK);
                                        start(questions.get(0));
                                    }
                                }
                        );
                AlertDialog dialog = builder.create();
                dialog.show();
    }

    public void showCorrect(){
        final Context currentContext = this.currentContext;
        MainActivity.counter++;
        MainActivity.rightCounter++;

        MediaPlayer correct = MediaPlayer.create(ModuleActivity.this, R.raw.correct);
        correct.start();

        AlertDialog.Builder builder = new AlertDialog.Builder(currentContext, R.style.RightDialogTheme);
        builder.setMessage("Correct. Your progress score has increased to " + MainActivity.rightCounter)
                .setTitle("Ka Pai!")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface i, int j) {
                                setProgressBar(Color.GREEN);
                                Question nextQuestion = questions.get(MainActivity.counter);
                                currentQuestion = nextQuestion;
                                start(nextQuestion);
                            }
                        }
                );
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void start(Question q){
        // Create new fragment and transaction
        if(MainActivity.counter == 10){
            showFinal();
            return;
        }

        currentFragment = q.getFragment();
        Bundle bundle = q.getBundle();

        currentFragment.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(R.id.fragment_placeholder, currentFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }

    public void showModules(){
        // Create new fragment and transaction
        Fragment f = new ModuleFragment();
        ModuleFragment mf = (ModuleFragment) f;
        mf.setParent(this);
        Bundle bundle = new Bundle();

        f.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        currentFragment = f;

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(R.id.fragment_placeholder, f);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }

    public void setProgressBar(int colour){
        progress = (ProgressBar) this.findViewById(R.id.progress);

        progress.getProgressDrawable().setColorFilter(
                colour, android.graphics.PorterDuff.Mode.SRC_IN);
        if (android.os.Build.VERSION.SDK_INT >= 24) {
            progress.setProgress(MainActivity.counter*QUESTION_PROGRESS_AMOUNT, true);
        } else {
            progress.setProgress(MainActivity.counter*QUESTION_PROGRESS_AMOUNT);
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
    //@Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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

