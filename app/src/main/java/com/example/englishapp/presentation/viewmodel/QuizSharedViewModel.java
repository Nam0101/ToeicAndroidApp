package com.example.englishapp.presentation.viewmodel;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.englishapp.data.model.QuestionResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class QuizSharedViewModel extends ViewModel {
    public final MutableLiveData<List<QuestionResult>> questionResults = new MutableLiveData<>(new ArrayList<>());
    public final MutableLiveData<Integer> numberOfQuestion = new MutableLiveData<>(0);
    public ObservableField<String> score = new ObservableField<>();
    public ObservableField<String> points = new ObservableField<>();

    public MutableLiveData<List<QuestionResult>> getQuestionResults() {
        return questionResults;
    }

    public void addQuestionResult(QuestionResult result) {
        List<QuestionResult> currentResults = questionResults.getValue();
        assert currentResults != null;
        currentResults.add(result);
        questionResults.setValue(currentResults);
    }

    public void calculateScore() {
        int score = 0;
        int points = 0;
        for (QuestionResult result : Objects.requireNonNull(questionResults.getValue())) {
            if(result.isCorrect()){
                score++;
                points += 10;
            }
        }
        this.score.set(String.valueOf(score));
        this.points.set(String.valueOf(points));
    }

    public void updateQuestionResult(int position, QuestionResult newResult) {
        if (position >= 0 && position < questionResults.getValue().size()) {
            questionResults.getValue().set(position, newResult);
            questionResults.setValue(questionResults.getValue()); // Trigger observers
        }
    }
}