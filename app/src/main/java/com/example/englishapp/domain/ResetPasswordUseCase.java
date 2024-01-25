package com.example.englishapp.domain;

import com.example.englishapp.data.model.UserModel;
import com.example.englishapp.data.repository.UserRepository;

import io.reactivex.rxjava3.core.Observable;

public class ResetPasswordUseCase {

    private final UserRepository userRepository;


    public ResetPasswordUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Observable<UserModel> execute(String email, String mobile, String code, String pass){
        return userRepository.doimatkhau(email, mobile, code, pass);
    }
}
