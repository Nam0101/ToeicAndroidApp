package com.example.englishapp.domain;

import com.example.englishapp.data.model.ExamHistoryResponse;
import com.example.englishapp.data.repository.QuestionRepository;

import io.reactivex.rxjava3.core.Observable;

public class InsertExamHistoryUseCase {
    private final QuestionRepository questionRepository;

    public InsertExamHistoryUseCase(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Observable<ExamHistoryResponse> execute(int user_id, int exam_id, int correct_answer, int total_question, String exam_date) {
        return questionRepository.insertExamHistory(user_id, exam_id, correct_answer, total_question, exam_date);
    }

}
