package com.example.englishapp.domain;

import com.example.englishapp.data.model.ExamDateResponse;
import com.example.englishapp.data.repository.UserRepository;

import io.reactivex.rxjava3.core.Observable;

public class UpdateExamDateUseCase {
    private final UserRepository userRepository;

    public UpdateExamDateUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Observable<ExamDateResponse> execute(int user_id, String exam_date) {
        return userRepository.updateExamDate(user_id, exam_date);
    }
}
