package com.example.englishapp.domain;

import com.example.englishapp.data.model.QuizQuestionModel;
import com.example.englishapp.data.repository.QuestionRepository;

import io.reactivex.rxjava3.core.Observable;

public class GetPart3QuestionUseCase {
    QuestionRepository part3QuestionRepository;

    public GetPart3QuestionUseCase(QuestionRepository part3QuestionRepository) {
        this.part3QuestionRepository = part3QuestionRepository;
    }

    public Observable<QuizQuestionModel> execute(int numOfQuestion) {
        return part3QuestionRepository.getPart3QuizQuestion(numOfQuestion);
    }
    public Observable<QuizQuestionModel> executeByExamId(int examId) {
        return part3QuestionRepository.getPart3QuizQuestionByExamId(examId);
    }
}
