package com.example.englishapp.domain;

import com.example.englishapp.data.model.UserModel;
import com.example.englishapp.data.repository.UserRepository;

import io.reactivex.rxjava3.core.Observable;

public class OTPVerifyUseCase {
  private final  UserRepository userRepository;

    public OTPVerifyUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public Observable<UserModel> execute(String email, String code){
        return userRepository.xacthucma(email, code);
    }
}
