package com.example.englishapp.data.remote;

import com.example.englishapp.data.model.QuizQuestionModel;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface QuestionService {

    @POST("getPart1QuizQuestion.php")
    @FormUrlEncoded
    Observable<QuizQuestionModel> getPart1QuizQuestion(
            @Field("numOfQuestion") int numOfQuestion
    );
    @POST("getPart2QuizQuestion.php")
    @FormUrlEncoded
    Observable<QuizQuestionModel> getPart2QuizQuestion(
            @Field("numOfQuestion") int numOfQuestion
    );
    @POST("getPart3QuizQuestion.php")
    @FormUrlEncoded
    Observable<QuizQuestionModel> getPart3QuizQuestion(
            @Field("numOfQuestion") int numOfQuestion
    );
    @POST("getPart4QuizQuestion.php")
    @FormUrlEncoded
    Observable<QuizQuestionModel> getPart4QuizQuestion(
            @Field("numOfQuestion") int numOfQuestion
    );
    @POST("getPart5QuizQuestion.php")
    @FormUrlEncoded
    Observable<QuizQuestionModel> getPart5QuizQuestion(
            @Field("numOfQuestion") int numOfQuestion
    );

    @POST("getPart6QuizQuestion.php")
    @FormUrlEncoded
    Observable<QuizQuestionModel> getPart6QuizQuestion(
            @Field("numOfQuestion") int numOfQuestion
    );

    @POST("getPart7QuizQuestion.php")
    @FormUrlEncoded
    Observable<QuizQuestionModel> getPart7QuizQuestion(
            @Field("numOfQuestion") int numOfQuestion
    );

}
