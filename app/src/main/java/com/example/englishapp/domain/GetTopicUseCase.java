package com.example.englishapp.domain;


import com.example.englishapp.data.model.TopicModel;
import com.example.englishapp.data.repository.VocabularyRepository;

import io.reactivex.rxjava3.core.Observable;

public class GetTopicUseCase {
    private final VocabularyRepository vocabularyRepository;

    public GetTopicUseCase(VocabularyRepository vocabularyRepository) {
        this.vocabularyRepository = vocabularyRepository;
    }
    public Observable<TopicModel> execute() {
       return vocabularyRepository.getAllTopic();
    }
}
