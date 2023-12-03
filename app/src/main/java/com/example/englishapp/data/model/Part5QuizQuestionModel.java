package com.example.englishapp.data.model;

import java.util.ArrayList;

public class Part5QuizQuestionModel {
    private boolean success;
    private ArrayList<Part5QuizQuestion> result;

    public Part5QuizQuestionModel(boolean success, ArrayList<Part5QuizQuestion> result) {
        this.success = success;
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public ArrayList<Part5QuizQuestion> getResult() {
        return result;
    }
}