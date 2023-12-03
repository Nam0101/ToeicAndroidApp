package com.example.englishapp.data.repository.impl;

import com.example.englishapp.data.model.Part5QuizQuestionModel;
import com.example.englishapp.data.remote.QuestionService;
import com.example.englishapp.data.repository.Part5QuestionRepository;

import io.reactivex.rxjava3.core.Observable;

public class Part5QuestionRepositoryImpl implements Part5QuestionRepository {
    private final QuestionService questionService;

    public Part5QuestionRepositoryImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Observable<Part5QuizQuestionModel> getPart5QuizQuestion(int numOfQuestion) {
        return questionService.getPart5QuizQuestion(numOfQuestion);
    }
}
