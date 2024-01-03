package com.example.englishapp.data.repository.impl;

import com.example.englishapp.data.model.ExamResponse;
import com.example.englishapp.data.model.QuizQuestionModel;
import com.example.englishapp.data.remote.QuestionService;
import com.example.englishapp.data.repository.QuestionRepository;

import io.reactivex.rxjava3.core.Observable;

public class QuestionRepositoryImpl implements QuestionRepository {
    private final QuestionService questionService;

    public QuestionRepositoryImpl(QuestionService questionService) {
        this.questionService = questionService;
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

}
