package com.reo.lingo;

/**
 * Created by patrick on 9/06/18.
 */

public class ImageHelper {
    public static int findImageIdByWord(String word) {
        switch (word)
        {
            case "Kotiro":
                return R.mipmap.girl;
            case "Ngeru":
                return R.mipmap.cat;
            case "Tama":
                return R.mipmap.boy;
            case "Kuri":
                return R.mipmap.dog;
            case "Inu":
                return R.drawable.coffee;
            case "Kai":
                return R.drawable.bread;
            case "Wa te moenga":
                return R.drawable.bedroom;
            case "Wa te parakūihi":
                return R.drawable.bread;
            case "Wai maori":
                return R.drawable.water;
            case "Perēti":
                return R.drawable.plate;
            case "Tohi":
                return R.drawable.toast;
            case "He pereti mawhero":
                return R.drawable.plate;
            case "He kapu whero":
                return R.drawable.cup;
            default:
                return R.drawable.question_mark;
        }
    }
}
