package com.example.englishapp.domain;

import com.example.englishapp.data.model.GetExamHistoryResponse;
import com.example.englishapp.data.repository.QuestionRepository;

import io.reactivex.rxjava3.core.Observable;

public class GetExamHistoryUseCase {
    private final QuestionRepository questionRepository;


    public GetExamHistoryUseCase(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }
    public Observable<GetExamHistoryResponse> execute(int userId){
        return questionRepository.getExamHistory(userId);
    }
}

