package com.example.englishapp.data.repository.impl;


import android.util.Log;

import com.example.englishapp.data.entity.TopicEntity;
import com.example.englishapp.data.local.dao.TopicDao;
import com.example.englishapp.data.model.Topic;
import com.example.englishapp.data.model.TopicModel;
import com.example.englishapp.data.model.VocabularyModel;
import com.example.englishapp.data.remote.VocabularyService;
import com.example.englishapp.data.repository.VocabularyRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class VocabularyRepositoryImpl implements VocabularyRepository {
    private final VocabularyService vocabularyService;
    private final TopicDao topicDao;

    public VocabularyRepositoryImpl(VocabularyService vocabularyService, TopicDao topicDao) {
        this.vocabularyService = vocabularyService;
        this.topicDao = topicDao;
    }
    public Observable<TopicModel> getAllTopic() {
    return Observable.fromCallable(() -> {
        List<TopicEntity> topics = topicDao.getAllTopic();
        if (!topics.isEmpty()) {
            ArrayList<Topic> topicList = new ArrayList<>();
            for (TopicEntity topicEntity : topics) {
                topicList.add(new Topic(topicEntity.getId(), topicEntity.getName()));
            }
            return new TopicModel(true, topicList);
        } else {
            TopicModel topicModel = vocabularyService.getAllTopic().blockingFirst();
            if (topicModel.isSuccess()) {
                for (Topic topic : topicModel.getResult()) {
                    Log.i("TAG", "Inserted: " + topic.getTopic());
                    topicDao.insertTopic(new TopicEntity(topic.getId(), topic.getTopic()));
                }
            }
            return topicModel;
        }
    });
}

    @Override
    public Observable<VocabularyModel> getVocabularyByTopic(int topic_id) {
        return vocabularyService.getVocabularyByTopic(topic_id);
    }
}
