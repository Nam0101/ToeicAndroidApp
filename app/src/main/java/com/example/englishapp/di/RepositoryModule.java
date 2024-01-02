package com.example.englishapp.di;

import com.example.englishapp.data.local.dao.TopicDao;
import com.example.englishapp.data.local.dao.UserDao;
import com.example.englishapp.data.remote.QuestionService;
import com.example.englishapp.data.remote.UserService;
import com.example.englishapp.data.remote.VocabularyService;
import com.example.englishapp.data.repository.FunctionRepository;
import com.example.englishapp.data.repository.QuestionRepository;
import com.example.englishapp.data.repository.UserRepository;
import com.example.englishapp.data.repository.VocabularyRepository;
import com.example.englishapp.data.repository.impl.FunctionRepositoryImpl;
import com.example.englishapp.data.repository.impl.QuestionRepositoryImpl;
import com.example.englishapp.data.repository.impl.UserRepositoryImpl;
import com.example.englishapp.data.repository.impl.VocabularyRepositoryImpl;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class RepositoryModule {

    @Provides
    public static UserRepository provideUserRepository(UserDao userDao, UserService userService) {
        return new UserRepositoryImpl(userDao, userService);
    }
    @Provides
    public static FunctionRepository provideFunctionRepository() {
        return new FunctionRepositoryImpl();
    }
    @Provides
    public static QuestionRepository providePart5QuestionRepository(QuestionService questionService){
        return new QuestionRepositoryImpl( questionService);
    }
    @Provides
    public static VocabularyRepository provideVocabularyRepository(VocabularyService vocabularyService, TopicDao topicDao){
        return new VocabularyRepositoryImpl( vocabularyService, topicDao);
    }
}