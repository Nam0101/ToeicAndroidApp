package com.example.englishapp.di;

import com.example.englishapp.data.local.dao.UserDao;
import com.example.englishapp.data.remote.UserService;
import com.example.englishapp.data.repository.UserRepository;
import com.example.englishapp.data.repository.impl.UserRepositoryImpl;

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
}