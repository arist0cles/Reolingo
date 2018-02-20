package com.reo.lingo.Parceable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by patrick/louie on 6/02/18.
 */

public class AnswerTile implements Parcelable {

    private String answer;
    private int image;
    private int sound;
    private boolean correct;

    public AnswerTile(String answer, int image, int sound, boolean correct) {
        this.answer = answer;
        this.image = image;
        this.sound = sound;
        this.correct = correct;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(answer);
        out.writeInt(image);
        out.writeInt(sound);
        if(this.correct){
            out.writeInt(1);
        } else {
            out.writeInt(0);
        }
    }

    public static final Parcelable.Creator<AnswerTile> CREATOR
            = new Parcelable.Creator<AnswerTile>() {
        public AnswerTile createFromParcel(Parcel in) {
            return new AnswerTile(in);
        }

        public AnswerTile[] newArray(int size) {
            return new AnswerTile[size];
        }
    };

    private AnswerTile(Parcel in) {
        this.answer = in.readString();
        this.image = in.readInt();
        this.sound = in.readInt();
        this.correct = (in.readInt() == 1) ? true: false;
    }

    public String getAnswer(){
        return answer;
    }

    public int getImage(){
        return image;
    }

    public int getSound(){
        return sound;
    }

    public boolean getCorrect(){
        return correct;
    }
}
