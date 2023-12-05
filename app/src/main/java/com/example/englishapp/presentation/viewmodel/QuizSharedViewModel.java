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
        List<QuestionResult> currentResults = questionResults.getValue();
        currentResults.add(result);
        questionResults.setValue(currentResults);
        Log.i("QuizSharedViewModel", "addQuestionResultSize: " + questionResults.getValue().size());
    }
}