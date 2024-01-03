package com.example.englishapp.domain;

import com.example.englishapp.data.model.ExamResponse;
import com.example.englishapp.data.repository.QuestionRepository;

import io.reactivex.rxjava3.core.Observable;

public class GetListExamUseCase {
    private final QuestionRepository questionRepository;

    public GetListExamUseCase(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }
    public Observable<ExamResponse> execute() {
        return questionRepository.getExam();
    }
}
