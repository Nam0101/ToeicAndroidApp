package com.example.englishapp.data.repository;

import com.example.englishapp.data.entity.UserEntity;
import com.example.englishapp.data.model.ExamDateResponse;
import com.example.englishapp.data.model.GetExamHistoryResponse;
import com.example.englishapp.data.model.UserModel;

import io.reactivex.rxjava3.core.Observable;

public interface UserRepository {
    void insertUser(UserEntity userEntity);

    Observable<UserModel> getUser(String username, String password);

    Observable<UserModel> register(String username, String password, String email, String mobile);

    Observable<UserModel> forgotPassword(String email, String mobile);

    Observable<ExamDateResponse> updateExamDate(int user_id, String exam_date);

    Observable<ExamDateResponse> getExamDate(int user_id);
    Observable<GetExamHistoryResponse> getExamHistory(int userId);
    Observable<UserModel> xacthucma(String email, String code);
    Observable<UserModel> doimatkhau(String email, String mobile, String code, String pass);

}
