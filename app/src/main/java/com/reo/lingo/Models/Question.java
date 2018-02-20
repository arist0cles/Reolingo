package com.reo.lingo.Models;

import java.util.List;

/**
 * Created by Butlerslad on 19/02/18.
 */

public abstract class Question {
    protected String correctEnglish;
    protected String correctMaori;
    protected String questionText;

    public String getQuestionText(){
        return questionText;
    }
}
