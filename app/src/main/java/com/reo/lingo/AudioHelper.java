package com.reo.lingo;

/**
 * Created by patrick on 9/06/18.
 */

public class AudioHelper {
    public static int findAudioIdByWord(String word){
        switch (word)
        {
            case "Kotiro":
                return  R.raw.kotiro;
            case "Ngeru":
                return R.raw.ngeru;
            case "Tama":
                return R.raw.tama;
            case "Kuri":
                return R.raw.kuri;
            case "Inu":
                return R.raw.inu;
            case "Oma":
                return R.raw.seventytwo;
            case "Mahi":
                return R.raw.seventy;
            case "Kai":
                return R.raw.seventythree;
            case "Wa te horoi":
                return R.raw.sixtyseven;
            case "Wa te moenga":
                return R.raw.sixtyeight;
            case "Wa te pūrakau":
                return R.raw.sixtynine;
            case "Wa te parakūihi":
                return R.raw.sixtysix;
            case "Wai maori":
                return R.raw.eightyeight;
            case "Wītī pīki":
                return R.raw.eightynine;
            case "Perēti":
                return R.raw.ninety;
            case "Tohi":
                return R.raw.ninetyone;
            case "He pereti mawhero":
                return R.raw.ninetysix;
            case "Ngā kapu whero":
                return R.raw.ninetyseven;
            case "He toka kowhai":
                return R.raw.ninetyeight;
            case "He kapu whero":
                return R.raw.ninetynine;
            case "Heihei":
                return R.raw.seventyeight;
            case "Wera":
                return R.raw.seventynine;
            case "Moenga":
                return R.raw.eighty;
            case "Hu":
                return R.raw.seventyfour;
            case "Kawhe":
                return R.raw.seventyfive;
            case "Nga kapu":
                return R.raw.seventysix;
            case "Tino reka":
                return R.raw.seventyseven;
            case "Kaua e noho":
                return R.raw.eightyone;
            case "Kaua e inu":
                return R.raw.eightytwo;
            case "Kaua e korero":
                return R.raw.eightythree;
            case "Kaua e kai":
                return R.raw.eightyfour;
            case "Makariri":
                return R.raw.eightyfive;
            case "Mahana":
                return R.raw.eightysix;
            case "Makuku":
                return R.raw.eightyseven;
            case "Correct":
                return R.raw.correct;
            case "Incorrect":
                return R.raw.incorrect;
        }

        throw new IllegalArgumentException("Word not recognised trying to find audio");
    }
}