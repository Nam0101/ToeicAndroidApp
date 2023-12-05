package com.example.englishapp.presentation.viewmodel;

import android.util.Log;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.englishapp.data.model.QuestionResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class QuizSharedViewModel extends ViewModel {
    public final MutableLiveData<List<QuestionResult>> questionResults = new MutableLiveData<>(new ArrayList<>());
    public ObservableField<String> score = new ObservableField<>();
    public ObservableField<String> points = new ObservableField<>();
    public ObservableField<Boolean> isResultFragmentVisiable = new ObservableField<>(false);

    public MutableLiveData<List<QuestionResult>> getQuestionResults() {
        return questionResults;
    }

    public void addQuestionResult(QuestionResult result) {
        List<QuestionResult> currentResults = questionResults.getValue();
        assert currentResults != null;
        currentResults.add(result);
        questionResults.setValue(currentResults);
        Log.i("QuizSharedViewModel", "addQuestionResultSize: " + questionResults.getValue().size());
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
        Log.i("QuizSharedViewModel", "calculateScore: " + score);
        Log.i("QuizSharedViewModel", "calculateScore: " + points);
    }

    public void submitQuiz() {
        calculateScore();
    }
}