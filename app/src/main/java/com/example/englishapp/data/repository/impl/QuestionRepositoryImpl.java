package com.example.englishapp.data.repository.impl;

import com.example.englishapp.data.model.QuizQuestionModel;
import com.example.englishapp.data.remote.QuestionService;
import com.example.englishapp.data.repository.QuestionRepository;

import io.reactivex.rxjava3.core.Observable;

public class QuestionRepositoryImpl implements QuestionRepository {
    private final QuestionService questionService;

    public QuestionRepositoryImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Observable<QuizQuestionModel> getPart5QuizQuestion(int numOfQuestion) {
        return questionService.getPart5QuizQuestion(numOfQuestion);
    }

    @Override
    public Observable<QuizQuestionModel> getPart6QuizQuestion(int numOfQuestion) {
        return questionService.getPart6QuizQuestion(numOfQuestion);
    }

}
