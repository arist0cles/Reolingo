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
import android.widget.TextView;

import com.reo.lingo.Activities.ModuleActivity;
import com.reo.lingo.R;

import java.util.List;

public class ModuleFragment extends Fragment {

    private Context currentContext;
    private ModuleActivity parent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.module_fragment_layout, container, false);
        return v;
    }

    public void setParent(ModuleActivity m){
        this.parent = m;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        savedInstanceState = getArguments();

        //setQuestionText(savedInstanceState.getString("questionTitle"));
        //setupButtons();

        currentContext = view.getContext();
        //setupButtons();
        parent.setupModuleButtons();
    }

    public void setupButtons(){
//        final TextView blankAnswer = (TextView) getView().findViewById(R.id.blankAnswer);
//
//        TextView word = (TextView) getView().findViewById(R.id.word);
//        word.setText(correctEnglish);
//
//        TextView a1 = (TextView) getView().findViewById(R.id.answer1);
//        a1.setText(options.get(0));
//        final String a1Text = (String)a1.getText();
//        a1.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                blankAnswer.setText(a1Text);
//                currentAnswer = a1Text;
//                int correctWordSound = getView().getResources().getIdentifier(a1Text.toLowerCase(), "raw", getActivity().getPackageName());
//                MediaPlayer mp = MediaPlayer.create(currentContext, correctWordSound);
//                mp.start();
//            }
//        });
//
    }
}
