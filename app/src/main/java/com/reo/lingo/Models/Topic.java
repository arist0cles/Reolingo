package com.reo.lingo.Models;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by patrick on 1/07/18.
 */

public class Topic implements Parcelable {

    private String name;

    public Topic(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    //METHODS NEEDED IMPLEMENT PARCELABLE - CAN BE IGNORED
    public Bundle getBundle() {
        Bundle thingsToPass = new Bundle();

        thingsToPass.putString("name", name);

        return thingsToPass;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(name);
    }

    public static final Parcelable.Creator<Topic> CREATOR = new Parcelable.Creator<Topic>() {
        public Topic createFromParcel(Parcel in) {
            return new Topic(in);
        }

        public Topic[] newArray(int size) {
            return new Topic[size];
        }
    };

    private Topic(Parcel in) {
        this.name = in.readString();
    }
}
