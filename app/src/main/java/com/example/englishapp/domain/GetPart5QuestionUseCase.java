package com.example.englishapp.domain;

import com.example.englishapp.data.model.Part5QuizQuestionModel;
import com.example.englishapp.data.repository.Part5QuestionRepository;

import io.reactivex.rxjava3.core.Observable;

public class GetPart5QuestionUseCase {
    Part5QuestionRepository part5QuestionRepository;

    public GetPart5QuestionUseCase(Part5QuestionRepository part5QuestionRepository) {
        this.part5QuestionRepository = part5QuestionRepository;
    }

    public Observable<Part5QuizQuestionModel> execute(int numOfQuestion) {
        return part5QuestionRepository.getPart5QuizQuestion(numOfQuestion);
    }
}
