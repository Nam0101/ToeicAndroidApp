package com.example.englishapp.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.englishapp.data.entity.TopicEntity;
import com.example.englishapp.data.entity.UserEntity;
import com.example.englishapp.data.local.dao.TopicDao;
import com.example.englishapp.data.local.dao.UserDao;

@Database(entities = {UserEntity.class, TopicEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    //Singleton
    public abstract UserDao userDao();
    public abstract TopicDao topicDao();

    public static final String DATABASE_NAME = "english_app_db";
}
