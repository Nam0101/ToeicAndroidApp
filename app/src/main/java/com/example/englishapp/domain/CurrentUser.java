package com.example.englishapp.domain;

import com.example.englishapp.data.model.User;

public class CurrentUser {
    private static CurrentUser instance = null;
    private User user;

    private CurrentUser() {
    }

    public static CurrentUser getInstance() {
        if (instance == null) {
            instance = new CurrentUser();
        }
        return instance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}