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
import android.widget.EditText;
import android.widget.TextView;

import com.reo.lingo.Helpers.AudioHelper;
import com.reo.lingo.R;

public class TypeQuestionFragment extends Fragment {
    private Context currentContext;
    private boolean isMilestone;
    private String currentAnswer;
    private String correctMaori;
    private String correctEnglish;
    private String questionText;

    private MediaPlayer mp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.type_question_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        savedInstanceState = getArguments();

        this.correctMaori = savedInstanceState.getString("correctMaori");
        this.questionText = savedInstanceState.getString("questionText");
        this.correctEnglish = savedInstanceState.getString("correctEnglish");
        this.isMilestone = savedInstanceState.getBoolean("isMilestone");

        currentContext = view.getContext();

        setup();
    }

    public void setup(){
        final TextView blankAnswer = (TextView) getView().findViewById(R.id.blankAnswer);
        TextView q = (TextView) getView().findViewById(R.id.question);
        q.setText(questionText);
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

    public String getSelected(){
        EditText t = (EditText)getView().findViewById(R.id.blankAnswer);
        return t.getText().toString();
    }
}
