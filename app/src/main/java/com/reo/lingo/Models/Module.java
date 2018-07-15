package com.reo.lingo.Models;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import com.reo.lingo.Models.Questions.Question;

import java.util.List;

/**
 * Created by patrick on 1/07/18.
 */

public class Module implements Parcelable {
    private int level;
    private List<Question> questions;

    public Module(int level) {
        this.level = level;
    }

    public int getLevel() {
        return this.level;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    //METHODS NEEDED IMPLEMENT PARCELABLE - CAN BE IGNORED
    public Bundle getBundle() {
        Bundle thingsToPass = new Bundle();

        thingsToPass.putInt("level", level);

        return thingsToPass;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(level);
    }

    public static final Parcelable.Creator<Module> CREATOR = new Parcelable.Creator<Module>() {
        public Module createFromParcel(Parcel in) {
            return new Module(in);
        }

        public Module[] newArray(int size) {
            return new Module[size];
        }
    };

    private Module(Parcel in) {
        this.level = in.readInt();
    }
}
