package com.example.englishapp.di;

import com.example.englishapp.data.remote.QuestionService;
import com.example.englishapp.data.remote.RetrofitClient;
import com.example.englishapp.data.remote.UserService;
import com.example.englishapp.data.remote.VocabularyService;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {

    @Provides
    public static UserService provideUserService() {
        return RetrofitClient.getInstance().create(UserService.class);
    }
    @Provides
    public static QuestionService provideQuestionService() {
        return RetrofitClient.getInstance().create(QuestionService.class);
    }
    @Provides
    public static VocabularyService provideVocabularyService() {
        return RetrofitClient.getInstance().create(VocabularyService.class);
    }
}