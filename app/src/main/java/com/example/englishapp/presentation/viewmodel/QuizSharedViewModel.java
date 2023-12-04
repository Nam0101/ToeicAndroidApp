package com.example.englishapp.presentation.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.englishapp.data.model.QuestionResult;

import java.util.ArrayList;
import java.util.List;

public class QuizSharedViewModel extends ViewModel {
    private final MutableLiveData<List<QuestionResult>> questionResults = new MutableLiveData<>(new ArrayList<>());

    public MutableLiveData<List<QuestionResult>> getQuestionResults() {
        return questionResults;
    }

    public void addQuestionResult(QuestionResult result) {
        Log.i("QuizSharedViewModel", "addQuestionResult: " + result.isCorrect());
        List<QuestionResult> results = questionResults.getValue();
        results.add(result);
        questionResults.setValue(results);
    }
}