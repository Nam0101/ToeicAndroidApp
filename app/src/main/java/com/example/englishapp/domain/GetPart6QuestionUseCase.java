package com.example.englishapp.domain;

import com.example.englishapp.data.model.QuizQuestionModel;
import com.example.englishapp.data.repository.QuestionRepository;

import io.reactivex.rxjava3.core.Observable;

public class GetPart6QuestionUseCase {

    QuestionRepository part6QuestionRepository;

    public GetPart6QuestionUseCase(QuestionRepository part6QuestionRepository) {
        this.part6QuestionRepository = part6QuestionRepository;
    }

    public Observable<QuizQuestionModel> execute(int numOfQuestion) {
        return part6QuestionRepository.getPart6QuizQuestion(numOfQuestion);
    }
}
