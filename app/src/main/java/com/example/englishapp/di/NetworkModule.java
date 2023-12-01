package com.example.englishapp.di;

import com.example.englishapp.data.remote.RetrofitClient;
import com.example.englishapp.data.remote.UserService;

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
}