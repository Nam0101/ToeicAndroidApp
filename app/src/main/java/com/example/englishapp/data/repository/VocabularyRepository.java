package com.example.englishapp.data.repository;

import com.example.englishapp.data.model.TopicModel;
import com.example.englishapp.data.model.VocabularyModel;

import io.reactivex.rxjava3.core.Observable;

public interface VocabularyRepository {
    Observable<TopicModel> getAllTopic();
    Observable<VocabularyModel> getVocabularyByTopic(int topic_id);
}
