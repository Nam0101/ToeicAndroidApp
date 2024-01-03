package com.example.englishapp.data.model;

public class QuestionResult {
    private final boolean isCorrect;
    private final int selectedAnswer;
    private final boolean isAnswered; // new field
    private final int position;

    public QuestionResult(boolean isCorrect, int selectedAnswer , boolean isAnswered , int position) {
        this.isCorrect = isCorrect;
        this.selectedAnswer = selectedAnswer;
        this.isAnswered = isAnswered;
        this.position = position;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public int getSelectedAnswer() {
        return selectedAnswer;
    }
    public boolean isAnswered() {
        return isAnswered;
    }
    public int getPosition() {
        return position;
    }

}