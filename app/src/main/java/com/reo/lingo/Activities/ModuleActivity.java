package com.reo.lingo.Activities;
import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

import com.reo.lingo.Fragments.FourTileQuestionFragment;
import com.reo.lingo.Models.FourTileQuestion;
import com.reo.lingo.Models.Question;
import com.reo.lingo.R;

import java.util.List;

public class ModuleActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int QUESTION_PROGRESS_AMOUNT = 10;

    private ModuleActivity ques = this;
    private ProgressBar progress;
    private Button check;

    private List<Question> questions;

    private android.support.v4.app.Fragment currentQuestionFragment;
    private Question currentQuestion;
    private Context currentContext;

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
                currentQuestion = questions.get(0);
                start(questions.get(0));
            }

            setProgress();
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

    public void setupCheckButton(){
        check = (Button) this.findViewById(R.id.check);
        check.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                currentContext = v.getContext();
                if(isCorrect()){
                    showCorrect();
                } else {
                    showIncorrect();
                }
            }
        });
    }

    public boolean isCorrect(){
        //TODO: write this shit
        //Get the selected word
        //check if the selected word is the same as the question correct maori

        FourTileQuestionFragment f = (FourTileQuestionFragment) currentQuestionFragment;
        String selected = f.getSelected();
        //String selected =
        String correctAnswer = currentQuestion.getCorrectEnglish();
        if(correctAnswer.equals(selected)){
            return true;
        }

        return false;
    }

    public void showIncorrect() {
        final Context currentContext = this.currentContext;
        MainActivity.counter++;
        MainActivity.wrongCounter++;
//        TextView t = (TextView) highlighted.getChildAt(2);
//        String correctWord = "Nah";
//        if(a1.getCorrect((String)t.getText())){
//            correctWord = a1.getAnswer();
//        }
//        if(a2.getCorrect((String)t.getText())){
//            correctWord = a2.getAnswer();
//        }
//        if(a3.getCorrect((String)t.getText())){
//            correctWord = a3.getAnswer();
//        }
//        if(a4.getCorrect((String)t.getText())){
//            correctWord = a4.getAnswer();
//        }
        AlertDialog.Builder builder = new AlertDialog.Builder(currentContext, R.style.WrongDialogTheme);
        //TODO: Get 'You fucked up' sound to play

        MediaPlayer incorrect = MediaPlayer.create(ModuleActivity.this, R.raw.incorrect);
        incorrect.start();
        builder.setMessage("That was incorrect. The correct answer was ")
                .setTitle("Aue")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface i, int j) {


//                                Intent intent = new Intent(ques, MainActivity.class);
//                                startActivity(intent);
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
        //TODO: Get happy sound to play
        AlertDialog.Builder builder = new AlertDialog.Builder(currentContext, R.style.RightDialogTheme);
        MediaPlayer correct = MediaPlayer.create(ModuleActivity.this, R.raw.correct);
        correct.start();
        builder.setMessage("Correct. Your progress score has increased to " + MainActivity.rightCounter)
                .setTitle("Ka Pai!")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface i, int j) {

                                //TODO: Get happy sound to play



                                //TODO: Base this on the counter
                                currentQuestion = questions.get(MainActivity.counter);
                                start(questions.get(MainActivity.counter));

//                                Intent intent = new Intent(ques, MainActivity.class);
//                                startActivity(intent);

                            }
                        }
                );
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public void start(Question q){
        // Create new fragment and transaction
        currentQuestionFragment = q.getFragment();
        Bundle bundle = q.getBundle();

        //TODO: Attach answertiles to bundle
        currentQuestionFragment.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(R.id.fragment_placeholder, currentQuestionFragment);
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

  //  public void buttonChosen(String name) {
//        if (rightAnswer) {
//            showCorrect(name);
//        } else {
//            showIncorrect(name);
//        }
   // }
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

