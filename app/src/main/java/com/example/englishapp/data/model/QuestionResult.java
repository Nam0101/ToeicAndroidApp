package com.example.englishapp.data.model;

public class QuestionResult {
    private final boolean isCorrect;
    private final int selectedAnswer;

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