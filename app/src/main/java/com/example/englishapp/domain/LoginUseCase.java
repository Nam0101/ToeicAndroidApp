package com.example.englishapp.domain;

import com.example.englishapp.data.model.UserModel;
import com.example.englishapp.data.repository.UserRepository;

import io.reactivex.rxjava3.core.Observable;

public class LoginUseCase {
    private final UserRepository userRepository;

    public LoginUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Observable<UserModel> execute(String username, String password) {
        return userRepository.getUser(username, password);
    }
}