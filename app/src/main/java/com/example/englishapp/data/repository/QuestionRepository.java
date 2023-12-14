package com.example.englishapp.data.repository;

import com.example.englishapp.data.model.QuizQuestionModel;

import io.reactivex.rxjava3.core.Observable;

public interface QuestionRepository {

    Observable<QuizQuestionModel> getPart5QuizQuestion(int numOfQuestion);
    Observable<QuizQuestionModel> getPart6QuizQuestion(int numOfQuestion);

}
