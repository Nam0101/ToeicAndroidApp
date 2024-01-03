package com.example.englishapp.domain;

import com.example.englishapp.data.model.QuizQuestionModel;
import com.example.englishapp.data.repository.QuestionRepository;

import io.reactivex.rxjava3.core.Observable;

public class GetPart4QuestionUseCase {
    QuestionRepository part4QuestionRepository;

    public GetPart4QuestionUseCase(QuestionRepository part4QuestionRepository) {
        this.part4QuestionRepository = part4QuestionRepository;
    }

    public Observable<QuizQuestionModel> execute(int numOfQuestion) {
        return part4QuestionRepository.getPart4QuizQuestion(numOfQuestion);
    }
    public Observable<QuizQuestionModel> executeByExamId(int examId) {
        return part4QuestionRepository.getPart4QuizQuestionByExamId(examId);
    }
}
