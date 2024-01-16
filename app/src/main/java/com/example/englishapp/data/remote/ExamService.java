package com.example.englishapp.data.remote;

import com.example.englishapp.data.model.ExamHistoryResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ExamService {
    @POST("examHistory.php")
    @FormUrlEncoded
    Observable<ExamHistoryResponse> insertExamHistory(
            @Field("user_id") int userId,
            @Field("exam_id") int examId,
            @Field("correct_answer") int correctAnswer,
            @Field("total_question") int totalQuestion,
            @Field("time_stamp") String timeStamp
    );


}
