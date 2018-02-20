package com.reo.lingo.Models;

import com.reo.lingo.Parceable.AnswerTile;
import com.reo.lingo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by patrick on 20/02/18.
 */

public class FourTileQuestion extends Question {

    private List<AnswerTile> tiles;

    public FourTileQuestion(String correctEnglish, String correctMaori,  String questionText, List<String> options){
        tiles = new ArrayList<>();
        this.correctEnglish = correctEnglish;
        this.correctMaori = correctMaori;
        this.questionText = questionText + "\"" + correctEnglish + "\"?";


        for(String s : options){
            tiles.add(makeTiles(s));
        }
    }

    public AnswerTile makeTiles(String opt)
    {
        switch (opt)
        {
            case "Kotiro":
                return new AnswerTile("Kotiro", R.mipmap.girl, R.raw.kotiro, true);
            case "Ngeru":
                return new AnswerTile("Ngeru", R.mipmap.cat, R.raw.ngeru, false);
            case "Tama":
                return new AnswerTile("Tama", R.mipmap.boy, R.raw.tama, false);
            case "Kuri":
                return new AnswerTile("Kuri", R.mipmap.dog, R.raw.kuri, false);
        }

        return null;
    }

    public AnswerTile getTile(int num){
        return tiles.get(num);
    }
}
