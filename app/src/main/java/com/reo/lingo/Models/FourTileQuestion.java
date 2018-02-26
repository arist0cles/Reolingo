package com.reo.lingo.Models;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import com.reo.lingo.Fragments.FourTileQuestionFragment;
import com.reo.lingo.Parceable.AnswerTile;
import com.reo.lingo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by patrick on 20/02/18.
 */

public class FourTileQuestion extends Question {

    private List<AnswerTile> tiles = new ArrayList<>();

    public FourTileQuestion(String correctEnglish, String correctMaori,  String questionText, List<String> options, boolean isMilestone){
        this.correctEnglish = correctEnglish;
        this.correctMaori = correctMaori;
        this.questionText = questionText + "\"" + correctEnglish + "\"?";
        this.isMilestone = isMilestone;

        for(String s : options){
            tiles.add(makeTile(s));
        }
    }

    public android.support.v4.app.Fragment getFragment(){
        return new FourTileQuestionFragment();
    }

    public Bundle getBundle(){
        Bundle thingsToPass = new Bundle();

        thingsToPass.putString("questionTitle", questionText);
        thingsToPass.putString("correctEnglish", correctEnglish);
        thingsToPass.putString("correctMaori", correctMaori);
        thingsToPass.putParcelable("tile1", getTile(0));
        thingsToPass.putParcelable("tile2", getTile(1));
        thingsToPass.putParcelable("tile3", getTile(2));
        thingsToPass.putParcelable("tile4", getTile(3));
        thingsToPass.putBoolean("isMilestone", isMilestone);

        return thingsToPass;
    }

    public AnswerTile makeTile(String opt)
    {
        switch (opt)
        {
            case "Kotiro":
                return new AnswerTile("Kotiro", R.mipmap.girl, R.raw.kotiro);
            case "Ngeru":
                return new AnswerTile("Ngeru", R.mipmap.cat, R.raw.ngeru);
            case "Tama":
                return new AnswerTile("Tama", R.mipmap.boy, R.raw.tama);
            case "Kuri":
                return new AnswerTile("Kuri", R.mipmap.dog, R.raw.kuri);
        }

        return null;
    }

    public AnswerTile getTile(int num){
        return tiles.get(num);
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
        out.writeList(tiles);
        if(isMilestone){
            out.writeInt(1);
        } else {
            out.writeInt(0);
        }
    }

    public static final Parcelable.Creator<FourTileQuestion> CREATOR
            = new Parcelable.Creator<FourTileQuestion>() {
        public FourTileQuestion createFromParcel(Parcel in) {
            return new FourTileQuestion(in);
        }

        public FourTileQuestion[] newArray(int size) {
            return new FourTileQuestion[size];
        }
    };

    private FourTileQuestion(Parcel in) {
        this.questionText = in.readString();
        this.correctEnglish = in.readString();
        this.correctEnglish = in.readString();
        in.readList(this.tiles, Question.class.getClassLoader());
        this.isMilestone = (in.readInt() == 1) ? true : false;
    }
}
