package com.reo.lingo.Models.Questions;

import android.os.Bundle;
import android.os.Parcel;

import com.reo.lingo.Fragments.TypeQuestionFragment;

/**
 * Created by patrick on 20/02/18.
 *
 * Should represent a question that is passed a sentence with several blanks
 * and then has various options to fill in the blanks down the bottom
 *
 */

public class TypeQuestion extends Question {

    public TypeQuestion(String correctEnglish, String correctMaori, String questionText, boolean isMilestone){
        this.correctEnglish = correctEnglish;
        this.correctMaori = correctMaori;
        this.questionText = questionText;
        this.isMilestone = isMilestone;
    }

    public android.support.v4.app.Fragment getFragment(){
        return new TypeQuestionFragment();
    }

    //METHODS NEEDED IMPLEMENT PARCELABLE - CAN BE IGNORED
    public Bundle getBundle(){
        Bundle thingsToPass = new Bundle();

        thingsToPass.putString("questionText", questionText);
        thingsToPass.putString("correctEnglish", correctEnglish);
        thingsToPass.putString("correctMaori", correctMaori);
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
        if(isMilestone){
            out.writeInt(1);
        } else {
            out.writeInt(0);
        }
    }

    public static final Creator<TypeQuestion> CREATOR
            = new Creator<TypeQuestion>() {
        public TypeQuestion createFromParcel(Parcel in) {
            return new TypeQuestion(in);
        }

        public TypeQuestion[] newArray(int size) {
            return new TypeQuestion[size];
        }
    };

    private TypeQuestion(Parcel in) {
        this.questionText = in.readString();
        this.correctEnglish = in.readString();
        this.correctMaori = in.readString();
        this.isMilestone = (in.readInt() == 1) ? true : false;
    }
}
