package com.example.englishapp.domain;

import com.example.englishapp.data.model.QuizQuestionModel;
import com.example.englishapp.data.repository.QuestionRepository;

import io.reactivex.rxjava3.core.Observable;

public class GetPart7QuestionUseCase {
    QuestionRepository questionRepository;

    public GetPart7QuestionUseCase(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Observable<QuizQuestionModel> execute(int numOfQuestion) {
        return questionRepository.getPart7QuizQuestion(numOfQuestion);
    }
    public Observable<QuizQuestionModel> executeByExamId(int examId) {
        return questionRepository.getPart7QuizQuestionByExamId(examId);
    }
}
