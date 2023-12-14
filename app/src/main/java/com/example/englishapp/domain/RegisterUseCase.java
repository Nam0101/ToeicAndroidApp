package com.example.englishapp.domain;

import com.example.englishapp.data.model.UserModel;
import com.example.englishapp.data.repository.UserRepository;

import io.reactivex.rxjava3.core.Observable;

public class RegisterUseCase {

    private final UserRepository userRepository;

    public RegisterUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public Observable<UserModel> execute(String username, String password, String email, String mobile) {
        return userRepository.register(username, password, email, mobile);
    }
}
