package com.reo.lingo.Fragments;

/**
 * Created by patrick on 22/02/18.
 */

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.reo.lingo.R;

import java.util.List;

public class MaoriEnglishTranslateQuestionFragment extends Fragment {

    private Context currentContext;
    private boolean isMilestone;
    private String currentAnswer;
    private String correctMaori;
    private String correctEnglish;
    private List<String> options;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.maori_english_translate_question_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        savedInstanceState = getArguments();

        setQuestionText(savedInstanceState.getString("questionTitle"));
        this.correctMaori = savedInstanceState.getString("correctMaori");
        this.correctEnglish = savedInstanceState.getString("correctEnglish");

        this.isMilestone = savedInstanceState.getBoolean("isMilestone");

        this.options = savedInstanceState.getStringArrayList("options");

        setupButtons();

        currentContext = view.getContext();
        setupButtons();
    }

    public void setupButtons(){
        final TextView blankAnswer = (TextView) getView().findViewById(R.id.blankAnswer);

        TextView word = (TextView) getView().findViewById(R.id.word);
        word.setText(correctMaori);

        TextView a1 = (TextView) getView().findViewById(R.id.answer1);
        a1.setText(options.get(0));
        final String a1Text = (String)a1.getText();
        a1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                blankAnswer.setText(a1Text);
                currentAnswer = a1Text;
            }
        });

        TextView a2 = (TextView) getView().findViewById(R.id.answer2);
        a2.setText(options.get(1));
        final String a2Text = (String)a2.getText();
        a2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                blankAnswer.setText(a2Text);
                currentAnswer = a2Text;
            }
        });

        TextView a3 = (TextView) getView().findViewById(R.id.answer3);
        a3.setText(options.get(2));
        final String a3Text = (String)a3.getText();
        a3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                blankAnswer.setText(a3Text);
                currentAnswer = a3Text;
            }
        });

        ImageButton soundButton = (ImageButton) getView().findViewById(R.id.soundButton);
        soundButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int correctWordSound = getView().getResources().getIdentifier(correctMaori.toLowerCase(), "raw", getActivity().getPackageName());
                MediaPlayer mp = MediaPlayer.create(currentContext, correctWordSound);
                mp.start();
            }
        });
    }

    public String getSelected(){
        return currentAnswer;
    }

    public void setQuestionText(String text){
        TextView t = (TextView) getView().findViewById(R.id.questionTitle);
        t.setText(text);
    }
}
