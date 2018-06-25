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

}
