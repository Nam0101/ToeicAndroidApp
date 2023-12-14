package com.example.englishapp.data.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class QuizQuestionModelDeserializer implements JsonDeserializer<QuizQuestionModel> {
    @Override
    public QuizQuestionModel deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        boolean success = jsonObject.get("success").getAsBoolean();
        QuizType type = QuizType.valueOf(jsonObject.get("type").getAsString().toUpperCase());
        Type resultType;
        switch (type){
            case PART5:
                resultType = new TypeToken<ArrayList<Part5QuizQuestion>>(){}.getType();
                break;
            case PART6:
                resultType = new TypeToken<ArrayList<Part6QuizQuestion>>(){}.getType();
                break;
            default:
                throw new JsonParseException("Unexpected type of quiz question: " + type);
        }
        ArrayList<? extends QuizQuestion> result = context.deserialize(jsonObject.get("result"), resultType);

        return new QuizQuestionModel(success, result, type);
    }
}
