package com.reo.lingo.Helpers;

/**
 * Created by patrick on 5/07/18.
 */

public class TextHelper {

    public static String getCorrectName(String s) {
        switch(s) {
            case "getting_dressed":
                return "Getting Dressed";
            case "mealtime":
                return "Mealtime";
        }
        throw new IllegalArgumentException("Provided string does not map to anything - TextHelper");
    }
}
