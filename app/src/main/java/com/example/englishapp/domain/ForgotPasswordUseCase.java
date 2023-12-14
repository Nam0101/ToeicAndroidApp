package com.example.englishapp.domain;

import com.example.englishapp.data.model.UserModel;
import com.example.englishapp.data.repository.UserRepository;

import io.reactivex.rxjava3.core.Observable;

public class ForgotPasswordUseCase {

    private final UserRepository userRepository;

    public ForgotPasswordUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Observable<UserModel> execute(String email, String mobile) {
        return userRepository.forgotPassword(email, mobile);
    }
}
