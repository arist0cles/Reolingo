package com.reo.lingo.Models;

import android.os.Bundle;
import android.os.Parcel;

import com.reo.lingo.Fragments.MaoriEnglishTranslateQuestionFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by patrick on 20/02/18.
 */

public class MaoriEnglishTranslateQuestion extends Question {

    public MaoriEnglishTranslateQuestion(String correctEnglish, String correctMaori, String questionText, List<String> options, boolean isMilestone){
        this.correctEnglish = correctEnglish;
        this.correctMaori = correctMaori;
        this.questionText = questionText;
        this.options = options;
        this.isMilestone = isMilestone;
    }

    public android.support.v4.app.Fragment getFragment(){
        return new MaoriEnglishTranslateQuestionFragment();
    }

    public Bundle getBundle(){
        Bundle thingsToPass = new Bundle();
        ArrayList<String> options = new ArrayList<>(this.options);

        thingsToPass.putString("questionTitle", questionText);
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

    public static final Creator<MaoriEnglishTranslateQuestion> CREATOR
            = new Creator<MaoriEnglishTranslateQuestion>() {
        public MaoriEnglishTranslateQuestion createFromParcel(Parcel in) {
            return new MaoriEnglishTranslateQuestion(in);
        }

        public MaoriEnglishTranslateQuestion[] newArray(int size) {
            return new MaoriEnglishTranslateQuestion[size];
        }
    };

    private MaoriEnglishTranslateQuestion(Parcel in) {
        this.questionText = in.readString();
        this.correctEnglish = in.readString();
        this.correctMaori = in.readString();
        in.readList(this.options, Question.class.getClassLoader());
        this.isMilestone = (in.readInt() == 1) ? true : false;
    }
}
