package com.example.englishapp.data.remote;

import com.example.englishapp.data.model.TopicModel;
import com.example.englishapp.data.model.VocabularyModel;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface VocabularyService {
    @POST("getAllTopic.php")
    Observable<TopicModel> getAllTopic(
    );
    @POST("getVocabularyByTopic.php")
    @FormUrlEncoded
    Observable<VocabularyModel> getVocabularyByTopic(
            @Field("topic_id") int topic_id
    );
}
