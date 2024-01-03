package com.example.englishapp.domain;

import com.example.englishapp.data.model.QuizQuestionModel;
import com.example.englishapp.data.repository.QuestionRepository;

import io.reactivex.rxjava3.core.Observable;

public class GetPart5QuestionUseCase {
    QuestionRepository part5QuestionRepository;

    public GetPart5QuestionUseCase(QuestionRepository part5QuestionRepository) {
        this.part5QuestionRepository = part5QuestionRepository;
    }

    public Observable<QuizQuestionModel> execute(int numOfQuestion) {
        return part5QuestionRepository.getPart5QuizQuestion(numOfQuestion);
    }
    public Observable<QuizQuestionModel> executeByExamId(int examId) {
        return part5QuestionRepository.getPart5QuizQuestionByExamId(examId);
    }
}
