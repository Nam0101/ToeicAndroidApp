package com.example.englishapp.data.remote;

import com.example.englishapp.data.model.Part5QuizQuestionModel;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface QuestionService {

    @POST("getPart5QuizQuestion.php")
    @FormUrlEncoded
    Observable<Part5QuizQuestionModel> getPart5QuizQuestion(
            @Field("numOfQuestion") int numOfQuestion
    );
}
