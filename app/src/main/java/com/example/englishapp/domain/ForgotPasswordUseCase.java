package com.example.englishapp.domain;

import com.example.englishapp.data.repository.UserRepository;

public class ForgotPasswordUseCase {

    private final UserRepository userRepository;

    public ForgotPasswordUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(String email, String mobile) {
        userRepository.forgotPassword(email, mobile);
    }
}
