package com.example.englishapp.data.repository.impl;

import com.example.englishapp.data.model.ExamHistoryResponse;
import com.example.englishapp.data.model.ExamResponse;
import com.example.englishapp.data.model.QuizQuestionModel;
import com.example.englishapp.data.remote.ExamService;
import com.example.englishapp.data.remote.QuestionService;
import com.example.englishapp.data.repository.QuestionRepository;

import io.reactivex.rxjava3.core.Observable;

public class QuestionRepositoryImpl implements QuestionRepository {
    private final QuestionService questionService;
    private final ExamService examService;

    public QuestionRepositoryImpl(QuestionService questionService, ExamService examService) {
        this.questionService = questionService;
        this.examService = examService;
    }

    @Override
    public Observable<QuizQuestionModel> getPart1QuizQuestion(int numOfQuestion) {
        return questionService.getPart1QuizQuestion(numOfQuestion);
    }
    @Override
    public Observable<QuizQuestionModel> getPart2QuizQuestion(int numOfQuestion) {
        return questionService.getPart2QuizQuestion(numOfQuestion);
    }
    @Override
    public Observable<QuizQuestionModel> getPart3QuizQuestion(int numOfQuestion) {
        return questionService.getPart3QuizQuestion(numOfQuestion);
    }
    @Override
    public Observable<QuizQuestionModel> getPart4QuizQuestion(int numOfQuestion) {
        return questionService.getPart4QuizQuestion(numOfQuestion);
    }

    @Override
    public Observable<QuizQuestionModel> getPart5QuizQuestion(int numOfQuestion) {
        return questionService.getPart5QuizQuestion(numOfQuestion);
    }

    @Override
    public Observable<QuizQuestionModel> getPart6QuizQuestion(int numOfQuestion) {
        return questionService.getPart6QuizQuestion(numOfQuestion);
    }

    @Override
    public Observable<QuizQuestionModel> getPart7QuizQuestion(int numOfQuestion) {
        return questionService.getPart7QuizQuestion(numOfQuestion);
    }

    @Override
    public Observable<ExamResponse> getExam() {
        return questionService.getExam();
    }
    @Override
    public Observable<QuizQuestionModel> getPart1QuizQuestionByExamId(int examId) {
        return questionService.getPart1QuizQuestionByExamId(examId);
    }
    @Override
    public Observable<QuizQuestionModel> getPart2QuizQuestionByExamId(int examId) {
        return questionService.getPart2QuizQuestionByExamId(examId);
    }
    @Override
    public Observable<QuizQuestionModel> getPart3QuizQuestionByExamId(int examId) {
        return questionService.getPart3QuizQuestionByExamId(examId);
    }
    @Override
    public Observable<QuizQuestionModel> getPart4QuizQuestionByExamId(int examId) {
        return questionService.getPart4QuizQuestionByExamId(examId);
    }
    @Override
    public Observable<QuizQuestionModel> getPart5QuizQuestionByExamId(int examId) {
        return questionService.getPart5QuizQuestionByExamId(examId);
    }

    @Override
    public Observable<QuizQuestionModel> getPart6QuizQuestionByExamId(int examId) {
        return questionService.getPart6QuizQuestionByExamId(examId);
    }

    @Override
    public Observable<QuizQuestionModel> getPart7QuizQuestionByExamId(int examId) {
        return questionService.getPart7QuizQuestionByExamId(examId);
    }

    @Override
    public Observable<ExamHistoryResponse> insertExamHistory(int userId, int examId, int correctAnswer, int totalQuestion, String timeStamp) {
        return examService.insertExamHistory(userId, examId, correctAnswer, totalQuestion, timeStamp);
    }

    @Override
    public Observable<ExamHistoryResponse> getExamHistory(int userId) {
        return null;
    }

}
