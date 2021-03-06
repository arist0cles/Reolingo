package com.reo.lingo.Models.Questions;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Butlerslad on 19/02/18.
 */

public abstract class Question implements Parcelable {
    protected String correctEnglish;
    protected String correctMaori;
    protected String questionText;
    protected List<String> options = new ArrayList<>();
    protected boolean isMilestone;

    public String getQuestionText(){
        return questionText;
    }

    public String getCorrectEnglish(){
        return correctEnglish;
    }

    public String getCorrectMaori(){
        return correctMaori;
    }

    public boolean isMilestone(){
        return this.isMilestone;
    }

    public abstract Bundle getBundle();

    public abstract android.support.v4.app.Fragment getFragment();
}
