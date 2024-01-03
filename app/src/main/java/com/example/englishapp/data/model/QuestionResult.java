package com.example.englishapp.data.model;

public class QuestionResult {
    private final boolean isCorrect;
    private final int selectedAnswer;
    private final int answer;
    private final boolean isAnswered; // new field
    private final int position;

    public QuestionResult(boolean isCorrect, int selectedAnswer , boolean isAnswered , int position, int answer) {
        this.isCorrect = isCorrect;
        this.selectedAnswer = selectedAnswer;
        this.isAnswered = isAnswered;
        this.position = position;
        this.answer = answer;
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
    public int getAnswer() {
        return answer;
    }
    public String toString() {
        return "QuestionResult(isCorrect=" + this.isCorrect() + ", selectedAnswer=" + this.getSelectedAnswer() + ", isAnswered=" + this.isAnswered() + ", position=" + this.getPosition() + ", answer=" + this.getAnswer() + ")";
    }
}