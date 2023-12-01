package com.example.englishapp.di;

import com.example.englishapp.data.repository.UserRepository;
import com.example.englishapp.domain.LoginUseCase;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class UseCaseModule {

    @Provides
    public static LoginUseCase provideLoginUseCase(UserRepository userRepository) {
        return new LoginUseCase(userRepository);
    }
}