package com.reo.lingo.Models;

import android.os.Bundle;
import android.os.Parcel;

import com.reo.lingo.Fragments.BlanksQuestionFragment;
import com.reo.lingo.Fragments.FourTileQuestionFragment;
import com.reo.lingo.Parceable.AnswerTile;
import com.reo.lingo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by patrick on 20/02/18.
 *
 * Should represent a question that is passed a sentence with several blanks
 * and then has various options to fill in the blanks down the bottom
 *
 */

public class BlanksQuestion extends Question {

    public BlanksQuestion(String correctEnglish, String correctMaori, String questionText, List<String> options, boolean isMilestone){
        this.correctEnglish = correctEnglish;
        this.correctMaori = correctMaori;
        this.questionText = questionText;
        this.isMilestone = isMilestone;
        this.options = options;
    }

    public android.support.v4.app.Fragment getFragment(){
        return new BlanksQuestionFragment();
    }

    public Bundle getBundle(){
        Bundle thingsToPass = new Bundle();
        ArrayList<String> options = new ArrayList<>(this.options);

        thingsToPass.putString("questionText", questionText);
        thingsToPass.putString("correctEnglish", correctEnglish);
        thingsToPass.putString("correctMaori", correctMaori);
        thingsToPass.putStringArrayList("options", options);
        thingsToPass.putBoolean("isMilestone", isMilestone);

        return thingsToPass;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(questionText);
        out.writeString(correctEnglish);
        out.writeString(correctMaori);
        out.writeList(options);
        if(isMilestone){
            out.writeInt(1);
        } else {
            out.writeInt(0);
        }
    }

    public static final Creator<BlanksQuestion> CREATOR
            = new Creator<BlanksQuestion>() {
        public BlanksQuestion createFromParcel(Parcel in) {
            return new BlanksQuestion(in);
        }

        public BlanksQuestion[] newArray(int size) {
            return new BlanksQuestion[size];
        }
    };

    private BlanksQuestion(Parcel in) {
        this.questionText = in.readString();
        this.correctEnglish = in.readString();
        this.correctMaori = in.readString();
        in.readList(this.options, Question.class.getClassLoader());
        this.isMilestone = (in.readInt() == 1) ? true : false;
    }
}
