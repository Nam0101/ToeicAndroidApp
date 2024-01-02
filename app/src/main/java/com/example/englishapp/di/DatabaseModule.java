package com.example.englishapp.di;

import android.content.Context;

import androidx.room.Room;

import com.example.englishapp.data.local.AppDatabase;
import com.example.englishapp.data.local.dao.TopicDao;
import com.example.englishapp.data.local.dao.UserDao;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DatabaseModule {

    @Provides
    public static AppDatabase provideAppDatabase(@ApplicationContext Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, AppDatabase.DATABASE_NAME).build();
    }

    @Provides
    public static UserDao provideUserDao(AppDatabase appDatabase) {
        return appDatabase.userDao();
    }
    @Provides
    public static TopicDao provideTopicDao(AppDatabase appDatabase) {
        return appDatabase.topicDao();
    }
}