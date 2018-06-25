package com.reo.lingo.Fragments;

/**
 * Created by patrick on 22/02/18.
 */

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.reo.lingo.Activities.MainActivity;
import com.reo.lingo.Activities.ModuleActivity;
import com.reo.lingo.AudioHelper;
import com.reo.lingo.Parceable.AnswerTile;
import com.reo.lingo.R;

import java.util.Objects;

public class FourTileQuestionFragment extends Fragment {
    private Context currentContext;

    private boolean isMilestone;

    private MediaPlayer mp;

    private RelativeLayout tile1;
    private RelativeLayout tile2;
    private RelativeLayout tile3;
    private RelativeLayout tile4;

    private RelativeLayout highlighted;

    private AnswerTile a1;
    private AnswerTile a2;
    private AnswerTile a3;
    private AnswerTile a4;

    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private ImageView image4;

    private TextView text1;
    private TextView text2;
    private TextView text3;
    private TextView text4;

    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private RadioButton radioButton4;

    private String selected;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.four_tile_question_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        savedInstanceState = getArguments();

        setQuestionText(savedInstanceState.getString("questionTitle"));
        savedInstanceState.getString("correctMaori");

        a1 = savedInstanceState.getParcelable("tile1");
        a2 = savedInstanceState.getParcelable("tile2");
        a3 = savedInstanceState.getParcelable("tile3");
        a4 = savedInstanceState.getParcelable("tile4");

        this.isMilestone = savedInstanceState.getBoolean("isMilestone");

        setupTiles(a1, a2, a3, a4);
        setupButtons();

        currentContext = view.getContext();
        setupButtons();
        setupTiles(a1, a2, a3, a4);
    }

    public void setQuestionText(String text){
        TextView t = (TextView) getView().findViewById(R.id.questionTitle);
        t.setText(text);
    }

    public void setupButtons() {
        final Context currentContext = this.currentContext;

        image1 = (ImageView) getView().findViewById(R.id.image1);
        image2 = (ImageView) getView().findViewById(R.id.image2);
        image3 = (ImageView) getView().findViewById(R.id.image3);
        image4 = (ImageView) getView().findViewById(R.id.image4);

        tile1 = (RelativeLayout) getView().findViewById(R.id.tile1);
        tile2 = (RelativeLayout) getView().findViewById(R.id.tile2);
        tile3 = (RelativeLayout) getView().findViewById(R.id.tile3);
        tile4 = (RelativeLayout) getView().findViewById(R.id.tile4);

        radioButton1 = (RadioButton) getView().findViewById(R.id.radio1);
        radioButton2 = (RadioButton) getView().findViewById(R.id.radio2);
        radioButton3 = (RadioButton) getView().findViewById(R.id.radio3);
        radioButton4 = (RadioButton) getView().findViewById(R.id.radio4);

        //Tile Listeners
        tile1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selected = a1.getAnswer();
                unhighlight();
                highlight(tile1);
                playSound(a1.getAnswer());
                radioButton1.setChecked(true);
            }
        });

        tile2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selected = a2.getAnswer();
                unhighlight();
                highlight(tile2);
                playSound(a2.getAnswer());
                radioButton2.setChecked(true);
            }
        });

        tile3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selected = a3.getAnswer();
                unhighlight();
                highlight(tile3);
                playSound(a3.getAnswer());
                radioButton3.setChecked(true);
            }
        });

        tile4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selected = a4.getAnswer();
                unhighlight();
                highlight(tile4);
                playSound(a4.getAnswer());
                radioButton4.setChecked(true);
            }
        });

        radioButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                unhighlight();
                highlight(tile1);
                radioButton1.setChecked(true);
            }
        });

        radioButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                unhighlight();
                highlight(tile2);
                radioButton2.setChecked(true);
            }
        });

        radioButton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                unhighlight();
                highlight(tile3);
                radioButton3.setChecked(true);
            }
        });

        radioButton4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                unhighlight();
                highlight(tile4);
                radioButton4.setChecked(true);
            }
        });

    }

    private void playSound(String word){
        mp = MediaPlayer.create(currentContext, AudioHelper.findAudioIdByWord(word));
        mp.start();
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                mp.reset();
                //if reset doesnt give you what you need use mp.release() instead
                //but dont forget to call MediaPlayer.create
                //before next mediaPlayer.start() method
            }
        });
    }

    public void highlight(RelativeLayout tile){
        tile.setBackground(getResources().getDrawable(R.drawable.background_border));
        tile.setBackgroundColor(getResources().getColor(R.color.purple));
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

        radioButton1.setChecked(false);
        radioButton2.setChecked(false);
        radioButton3.setChecked(false);
        radioButton4.setChecked(false);
    }

    private void setupTiles(AnswerTile a1, AnswerTile a2, AnswerTile a3, AnswerTile a4){
        text1 = (TextView) getView().findViewById(R.id.text1);
        text2 = (TextView) getView().findViewById(R.id.text2);
        text3 = (TextView) getView().findViewById(R.id.text3);
        text4 = (TextView) getView().findViewById(R.id.text4);

        text1.setText(a1.getAnswer());
        text2.setText(a2.getAnswer());
        text3.setText(a3.getAnswer());
        text4.setText(a4.getAnswer());

        image1 = (ImageView) getView().findViewById(R.id.image1);
        image2 = (ImageView) getView().findViewById(R.id.image2);
        image3 = (ImageView) getView().findViewById(R.id.image3);
        image4 = (ImageView) getView().findViewById(R.id.image4);

        image1.setBackground(this.getResources().getDrawable(a1.getImage()));
        image2.setBackground(this.getResources().getDrawable(a2.getImage()));
        image3.setBackground(this.getResources().getDrawable(a3.getImage()));
        image4.setBackground(this.getResources().getDrawable(a4.getImage()));
    }

    public String getSelected(){
        return selected;
    }
}
