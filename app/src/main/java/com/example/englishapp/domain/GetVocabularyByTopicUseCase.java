package com.example.englishapp.domain;


import com.example.englishapp.data.model.VocabularyModel;
import com.example.englishapp.data.repository.VocabularyRepository;

import io.reactivex.rxjava3.core.Observable;

public class GetVocabularyByTopicUseCase {
    private final VocabularyRepository vocabularyRepository;

    public GetVocabularyByTopicUseCase(VocabularyRepository vocabularyRepository) {
        this.vocabularyRepository = vocabularyRepository;
    }
    public Observable<VocabularyModel> execute(int topic_id) {
        return vocabularyRepository.getVocabularyByTopic(topic_id);
    }
}
