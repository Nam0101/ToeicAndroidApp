package com.example.englishapp.data.model;

public class QuestionResult {
    private boolean isCorrect;
    private int selectedAnswer;

    public QuestionResult(boolean isCorrect, int selectedAnswer) {
        this.isCorrect = isCorrect;
        this.selectedAnswer = selectedAnswer;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public int getSelectedAnswer() {
        return selectedAnswer;
    }
}