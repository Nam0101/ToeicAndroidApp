package com.example.englishapp;

import android.app.Application;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class ToeicApplication extends Application {
    // This class is needed for Hilt
    // https://developer.android.com/training/dependency-injection/hilt-android#application-class
}
// Compare this snippet from app/src/main/java/com/example/englishapp/data/local/dao/TopicDao.java: