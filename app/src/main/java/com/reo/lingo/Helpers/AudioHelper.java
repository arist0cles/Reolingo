package com.reo.lingo.Helpers;

import com.reo.lingo.R;

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
            case "Whakapaitia tō ruma moe":
                return R.raw.thirtytwo;
            case "Kameta":
                return R.raw.thirtythree;
            case "Hatete":
                return R.raw.thirtyfour;
            case "Raina Kākahu":
                return R.raw.thirtyfive;
            case "Pūrere horoi kākahu":
                return R.raw.thirtysix;
            case "Rau atu tō tīhāte nui ki te wātaropa":
                return R.raw.thirtyseven;
            case "Tangohia":
                return R.raw.thirtyeight;
            case "Anei ō tokena kākāriki":
                return R.raw.fortyone;
            case "Kei runga tō tīhāte i ngā hautō":
                return R.raw.fortytwo;
            case "Raro":
                return R.raw.fortythree;
            case "Te":
                return R.raw.fortyfour;
            case "Roto":
                return R.raw.fortyfive;
            case "Runga":
                return R.raw.fortysix;
            case "Ngā":
                return R.raw.fortyseven;
            case "Kei whea te hakete":
                return R.raw.fortyeight;
            case "Aporo":
                return R.raw.fortynine;
            case "Kura":
                return R.raw.fifty;
            case "Horoi":
                return R.raw.fiftyone;
            case "Tuatahi, whātuia ngā kākahu":
                return R.raw.fiftytwo;
            case "Rawe! I whēa tō pōtae?":
                return R.raw.fiftythree;
            case "Rawe! Kei whēa te pōtae?":
                return R.raw.fiftyfour;
            case "i whea ō pōtae?":
                return R.raw.fiftyfive;
            case "Rawe! I whea ngā pōtae?":
                return R.raw.fiftysix;
            case "Whakamau to tīhāte whero":
                return R.raw.fiftyeight;
            case "Tēna":
                return R.raw.fiftynine;
            case "Ko":
                return R.raw.sixty;
            case "Kei te kuhu kākahu matengi koe?":
                return R.raw.sixtyone;
            case "Me panoni koe i ō Kākahu":
                return R.raw.sixtytwo;
            case "Kia tere":
                return R.raw.sixtythree;
            case "Kua mutu":
                return R.raw.sixtyfour;
            case "E noho":
                return R.raw.sixtyfive;
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
            case "Tū":
                return R.raw.thirtynine;
            case "Waka":
                return R.raw.forty;
            case "Tīhāte":
                return R.raw.eleven;


            //These ones don't have a word
            case "Tuatahi":
                return R.raw.incorrect;
            case "Whakapaitia":
                return R.raw.incorrect;

        }
        throw new IllegalArgumentException("Word not recognised trying to find audio");
    }
}