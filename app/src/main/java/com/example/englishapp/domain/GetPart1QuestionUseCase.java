package com.example.englishapp.domain;

import com.example.englishapp.data.model.QuizQuestionModel;
import com.example.englishapp.data.repository.QuestionRepository;

import io.reactivex.rxjava3.core.Observable;

public class GetPart1QuestionUseCase {
    QuestionRepository part1QuestionRepository;

    public GetPart1QuestionUseCase(QuestionRepository part1QuestionRepository) {
        this.part1QuestionRepository = part1QuestionRepository;
    }

    public Observable<QuizQuestionModel> execute(int numOfQuestion) {
        return part1QuestionRepository.getPart1QuizQuestion(numOfQuestion);
    }
    public Observable<QuizQuestionModel> executeByExamId(int examId) {
        return part1QuestionRepository.getPart1QuizQuestionByExamId(examId);
    }
}
