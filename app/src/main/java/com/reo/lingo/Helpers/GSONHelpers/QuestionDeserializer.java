package com.reo.lingo.Helpers.GSONHelpers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.reo.lingo.Models.Questions.BlanksQuestion;
import com.reo.lingo.Models.Questions.EnglishMaoriTranslateQuestion;
import com.reo.lingo.Models.Questions.FourTileQuestion;
import com.reo.lingo.Models.Questions.MaoriEnglishTranslateQuestion;
import com.reo.lingo.Models.Questions.TypeQuestion;

import java.lang.reflect.Type;

/**
 * Created by patrick on 2/07/18.
 */

public class QuestionDeserializer implements JsonDeserializer<Object> {

    @Override
    public Object deserialize(JsonElement jsonElement, Type type,
                              JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonElement questionType = jsonObject.get("type");
        if (type != null) {
            switch (questionType.getAsString()) {
                case "FOUR_TILE_QUESTION":
                    return context.deserialize(jsonObject,
                            FourTileQuestion.class);
                case "BLANKS_QUESTION":
                    return context.deserialize(jsonObject,
                            BlanksQuestion.class);
                case "ENGLISH_MAORI_TRANSLATE_QUESTION":
                    return context.deserialize(jsonObject,
                            EnglishMaoriTranslateQuestion.class);
                case "TYPE_QUESTION":
                    return context.deserialize(jsonObject,
                            TypeQuestion.class);
                case "MAORI_ENGLISH_TRANSLATE_QUESTION":
                    return context.deserialize(jsonObject,
                            MaoriEnglishTranslateQuestion.class);
            }
        }
        return null;
    }
}

