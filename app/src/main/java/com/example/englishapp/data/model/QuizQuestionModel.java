package com.example.englishapp.data.model;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class QuizQuestionModel {
    private final boolean success;
    private final ArrayList<? extends QuizQuestion> result;
    private final QuizType type;

    public QuizQuestionModel(boolean success, ArrayList<? extends QuizQuestion> result, QuizType type) {
        this.success = success;
        this.result = result;
        this.type = type;
    }

    public boolean isSuccess() {
        return success;
    }

    public QuizType getType() {
        return type;
    }

    public ArrayList<? extends QuizQuestion> getResult() {
        return result;
    }
    @NonNull
    @Override
    public String toString() {
        return "QuizQuestionModel{" +
                "success=" + success +
                ", result=" + result +
                 "type=" + type +
                '}';
    }
}