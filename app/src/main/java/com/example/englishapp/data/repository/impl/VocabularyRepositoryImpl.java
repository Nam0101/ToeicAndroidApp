package com.example.englishapp.data.repository.impl;


import com.example.englishapp.data.model.TopicModel;
import com.example.englishapp.data.model.VocabularyModel;
import com.example.englishapp.data.remote.VocabularyService;
import com.example.englishapp.data.repository.VocabularyRepository;

import io.reactivex.rxjava3.core.Observable;

public class VocabularyRepositoryImpl implements VocabularyRepository {
    private final VocabularyService vocabularyService;

    public VocabularyRepositoryImpl(VocabularyService vocabularyService) {
        this.vocabularyService = vocabularyService;
    }
    public Observable<TopicModel> getAllTopic() {
        return vocabularyService.getAllTopic();
    }

    @Override
    public Observable<VocabularyModel> getVocabularyByTopic(int topic_id) {
        return vocabularyService.getVocabularyByTopic(topic_id);
    }
}
