package com.reo.lingo.Models.Questions;

import android.os.Bundle;
import android.os.Parcel;

import com.reo.lingo.Fragments.EnglishMaoriTranslateQuestionFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by patrick on 20/02/18.
 */

public class EnglishMaoriTranslateQuestion extends Question {

    public EnglishMaoriTranslateQuestion(String correctEnglish, String correctMaori, String questionText, List<String> options, boolean isMilestone){
        this.correctEnglish = correctEnglish;
        this.correctMaori = correctMaori;
        this.questionText = questionText;
        this.options = options;
        this.isMilestone = isMilestone;
    }

    public android.support.v4.app.Fragment getFragment(){
        return new EnglishMaoriTranslateQuestionFragment();
    }

    //METHODS NEEDED IMPLEMENT PARCELABLE - CAN BE IGNORED
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

    public static final Creator<EnglishMaoriTranslateQuestion> CREATOR
            = new Creator<EnglishMaoriTranslateQuestion>() {
        public EnglishMaoriTranslateQuestion createFromParcel(Parcel in) {
            return new EnglishMaoriTranslateQuestion(in);
        }

        public EnglishMaoriTranslateQuestion[] newArray(int size) {
            return new EnglishMaoriTranslateQuestion[size];
        }
    };

    private EnglishMaoriTranslateQuestion(Parcel in) {
        this.questionText = in.readString();
        this.correctEnglish = in.readString();
        this.correctMaori = in.readString();
        in.readList(this.options, Question.class.getClassLoader());
        this.isMilestone = (in.readInt() == 1) ? true : false;
    }
}
