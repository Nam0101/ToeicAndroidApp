package com.example.englishapp.domain;

import com.example.englishapp.data.model.ExamDateResponse;
import com.example.englishapp.data.repository.UserRepository;

import io.reactivex.rxjava3.core.Observable;

public class GetExamDateUseCase {
    private final UserRepository userRepository;

    public GetExamDateUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Observable<ExamDateResponse> execute(int user_id) {
        return userRepository.getExamDate(user_id);
    }
}
