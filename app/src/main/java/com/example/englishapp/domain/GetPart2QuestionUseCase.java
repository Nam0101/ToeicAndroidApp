package com.example.englishapp.domain;

import com.example.englishapp.data.model.QuizQuestionModel;
import com.example.englishapp.data.repository.QuestionRepository;

import io.reactivex.rxjava3.core.Observable;

public class GetPart2QuestionUseCase {
    QuestionRepository part2QuestionRepository;

    public GetPart2QuestionUseCase(QuestionRepository part2QuestionRepository) {
        this.part2QuestionRepository = part2QuestionRepository;
    }

    public Observable<QuizQuestionModel> execute(int numOfQuestion) {
        return part2QuestionRepository.getPart2QuizQuestion(numOfQuestion);
    }
}
