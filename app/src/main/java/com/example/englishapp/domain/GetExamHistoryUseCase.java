package com.example.englishapp.domain;

import com.example.englishapp.data.repository.QuestionRepository;

public class GetExamHistoryUseCase {
    private final QuestionRepository questionRepository;


    public GetExamHistoryUseCase(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

}

