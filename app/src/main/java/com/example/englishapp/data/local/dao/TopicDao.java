package com.example.englishapp.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.englishapp.data.entity.TopicEntity;

import java.util.List;

@Dao
public interface TopicDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTopic(TopicEntity topic);
    @Query("SELECT * FROM topic")
    List<TopicEntity> getAllTopic();
}
