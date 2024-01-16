package com.example.englishapp.data.repository;

import com.example.englishapp.data.model.ExamHistoryResponse;
import com.example.englishapp.data.model.ExamResponse;
import com.example.englishapp.data.model.GetExamHistoryResponse;
import com.example.englishapp.data.model.QuizQuestionModel;

import io.reactivex.rxjava3.core.Observable;

public interface QuestionRepository {
    Observable<QuizQuestionModel> getPart1QuizQuestion(int numOfQuestion);
    Observable<QuizQuestionModel> getPart2QuizQuestion(int numOfQuestion);
    Observable<QuizQuestionModel> getPart3QuizQuestion(int numOfQuestion);
    Observable<QuizQuestionModel> getPart4QuizQuestion(int numOfQuestion);
    Observable<QuizQuestionModel> getPart5QuizQuestion(int numOfQuestion);

    Observable<QuizQuestionModel> getPart6QuizQuestion(int numOfQuestion);

    Observable<QuizQuestionModel> getPart7QuizQuestion(int numOfQuestion);

    Observable<ExamResponse> getExam();

    Observable<QuizQuestionModel> getPart1QuizQuestionByExamId(int examId);

    Observable<QuizQuestionModel> getPart2QuizQuestionByExamId(int examId);

    Observable<QuizQuestionModel> getPart3QuizQuestionByExamId(int examId);

    Observable<QuizQuestionModel> getPart4QuizQuestionByExamId(int examId);

    Observable<QuizQuestionModel> getPart5QuizQuestionByExamId(int examId);

    Observable<QuizQuestionModel> getPart6QuizQuestionByExamId(int examId);

    Observable<QuizQuestionModel> getPart7QuizQuestionByExamId(int examId);

    Observable<ExamHistoryResponse> insertExamHistory(int userId, int examId, int correctAnswer, int totalQuestion, String timeStamp);

    Observable<GetExamHistoryResponse> getExamHistory(int userId);

}
