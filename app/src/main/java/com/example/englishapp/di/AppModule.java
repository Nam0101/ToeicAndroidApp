package com.example.englishapp.di;

import android.app.Application;

import com.example.englishapp.ToeicApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Provides
    @Singleton
    public Application provideToeicApplication() {
        return new ToeicApplication();
    }

}
