package com.example.englishapp.data.remote;

import com.example.englishapp.data.model.ExamDateResponse;
import com.example.englishapp.data.model.GetExamHistoryResponse;
import com.example.englishapp.data.model.UserModel;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserService {
    @POST("dangki.php")
    @FormUrlEncoded
    Observable<UserModel> register(
            @Field("email") String email,
            @Field("pass") String pass,
            @Field("username") String username,
            @Field("mobile") String mobile
    );
    @POST("dangnhap.php")
    @FormUrlEncoded
    Observable<UserModel> login(
            @Field("email") String email,
            @Field("pass") String pass
    );

    @POST("quenmatkhau.php")
    @FormUrlEncoded
    Observable<UserModel> forgotPassword(
            @Field("email") String email,
            @Field("mobile") String mobile
    );

    @POST("xacthucma.php")
    @FormUrlEncoded
    Observable<UserModel> xacthucma(
            @Field("email") String email,
            @Field("code") String code
    );

    @POST("doimatkhau.php")
    @FormUrlEncoded
    Observable<UserModel> doimatkhau(
            @Field("email") String email,
            @Field("mobile") String mobile,
            @Field("code") String code,
            @Field("pass") String pass
    );

    @POST("examDate.php")
    @FormUrlEncoded
    Observable<ExamDateResponse> updateExamDate(
            @Field("user_id") int user_id,
            @Field("exam_date") String exam_date
    );

    @POST("getExamDate.php")
    @FormUrlEncoded
    Observable<ExamDateResponse> getExamDate(
            @Field("user_id") int user_id
    );

    @POST("getExamHistory.php")
    @FormUrlEncoded
    Observable<GetExamHistoryResponse> getExamHistory(
            @Field("user_id") int userId
    );

}
