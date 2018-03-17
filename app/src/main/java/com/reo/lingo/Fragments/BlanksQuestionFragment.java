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
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.reo.lingo.Parceable.AnswerTile;
import com.reo.lingo.R;

import java.util.List;

public class BlanksQuestionFragment extends Fragment {
    private Context currentContext;
    private boolean isMilestone;
    private String currentAnswer = "_____";
    private String correctMaori;
    private String correctEnglish;
    private List<String> options;
    private String questionText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.blanks_question_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        savedInstanceState = getArguments();

        this.correctMaori = savedInstanceState.getString("correctMaori");
        this.questionText = savedInstanceState.getString("questionText");

        this.correctEnglish = savedInstanceState.getString("correctEnglish");

        this.isMilestone = savedInstanceState.getBoolean("isMilestone");

        this.options = savedInstanceState.getStringArrayList("options");

        currentContext = view.getContext();

        setup();
    }

    public void setup(){
        final TextView q = (TextView) getView().findViewById(R.id.question);
        q.setText(blankify(questionText));

        TextView a1 = (TextView) getView().findViewById(R.id.answer1);
        a1.setText(options.get(0));
        final String a1Text = (String)a1.getText();
        a1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                q.setText(mergeSentence(a1Text));
                currentAnswer = a1Text;
                //int correctWordSound = getView().getResources().getIdentifier(a2Text.toLowerCase(), "raw", getActivity().getPackageName());
                //MediaPlayer mp = MediaPlayer.create(currentContext, correctWordSound);
                //mp.start();
            }
        });

        TextView a2 = (TextView) getView().findViewById(R.id.answer2);
        a2.setText(options.get(1));
        final String a2Text = (String)a2.getText();
        a2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                q.setText(mergeSentence(a2Text));
                currentAnswer = a2Text;
                //int correctWordSound = getView().getResources().getIdentifier(a2Text.toLowerCase(), "raw", getActivity().getPackageName());
                //MediaPlayer mp = MediaPlayer.create(currentContext, correctWordSound);
                //mp.start();
            }
        });

        TextView a3 = (TextView) getView().findViewById(R.id.answer3);
        a3.setText(options.get(2));
        final String a3Text = (String)a3.getText();
        a3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                q.setText(mergeSentence(a3Text));
                currentAnswer = a3Text;
                //int correctWordSound = getView().getResources().getIdentifier(a2Text.toLowerCase(), "raw", getActivity().getPackageName());
                //MediaPlayer mp = MediaPlayer.create(currentContext, correctWordSound);
                //mp.start();
            }
        });

        TextView a4 = (TextView) getView().findViewById(R.id.answer4);
        a4.setText(options.get(3));
        final String a4Text = (String)a4.getText();
        a4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                q.setText(mergeSentence(a4Text));
                currentAnswer = a4Text;
                //int correctWordSound = getView().getResources().getIdentifier(a2Text.toLowerCase(), "raw", getActivity().getPackageName());
                //MediaPlayer mp = MediaPlayer.create(currentContext, correctWordSound);
                //mp.start();
            }
        });
    }

    public String blankify(String text){
        String blanked = text.replace(correctMaori, "_____");
        return blanked;
    }

    public String mergeSentence(String clicked){
        TextView q = (TextView) getView().findViewById(R.id.question);
        String toBeReplaced = (String)q.getText();
        String wordAdded = toBeReplaced.replace(currentAnswer, clicked);
        return wordAdded;
    }

    public String getSelected(){
        return currentAnswer;
    }
}
