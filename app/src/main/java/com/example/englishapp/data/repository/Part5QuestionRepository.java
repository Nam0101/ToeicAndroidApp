package com.example.englishapp.data.repository;

import com.example.englishapp.data.model.Part5QuizQuestionModel;

import io.reactivex.rxjava3.core.Observable;

public interface Part5QuestionRepository {

    Observable<Part5QuizQuestionModel> getPart5QuizQuestion(int numOfQuestion);
}
