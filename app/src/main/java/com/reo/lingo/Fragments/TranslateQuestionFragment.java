package com.reo.lingo.Fragments;

/**
 * Created by patrick on 22/02/18.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.reo.lingo.R;

public class TranslateQuestionFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.translate_question_fragment_layout, container, false);
    }
}
