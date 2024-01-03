package com.example.englishapp.presentation;

import com.example.englishapp.domain.ForgotPasswordUseCase;
import com.example.englishapp.domain.GetFunctionsUseCase;
import com.example.englishapp.domain.GetListExamUseCase;
import com.example.englishapp.domain.GetPart1QuestionUseCase;
import com.example.englishapp.domain.GetPart2QuestionUseCase;
import com.example.englishapp.domain.GetPart3QuestionUseCase;
import com.example.englishapp.domain.GetPart4QuestionUseCase;
import com.example.englishapp.domain.GetPart5QuestionUseCase;
import com.example.englishapp.domain.GetPart6QuestionUseCase;
import com.example.englishapp.domain.GetPart7QuestionUseCase;
import com.example.englishapp.domain.GetTopicUseCase;
import com.example.englishapp.domain.GetVocabularyByTopicUseCase;
import com.example.englishapp.domain.LoginUseCase;
import com.example.englishapp.domain.RegisterUseCase;

import javax.inject.Inject;

public class ViewModelFactory {
    @Inject
    LoginUseCase loginUseCase;
    @Inject
    RegisterUseCase registerUseCase;
    @Inject
    ForgotPasswordUseCase forgotPasswordUseCase;
    @Inject
    GetFunctionsUseCase getFunctionsUseCase;
    @Inject
    GetPart2QuestionUseCase getPart2QuestionUseCase;
    @Inject
    GetPart3QuestionUseCase getPart3QuestionUseCase;
    @Inject
    GetPart4QuestionUseCase getPart4QuestionUseCase;
    @Inject
    GetPart5QuestionUseCase getPart5QuestionUseCase;
    @Inject
    GetPart6QuestionUseCase getPart6QuestionUseCase;
    @Inject
    GetPart7QuestionUseCase getPart7QuestionUseCase;
    @Inject
    GetPart1QuestionUseCase getPart1QuestionUseCase;
    @Inject
    GetTopicUseCase getTopicUseCase;
    @Inject
    GetVocabularyByTopicUseCase getVocabularyByTopicUseCase;
    @Inject
    GetListExamUseCase getListExamUseCase;
    @Inject
    public ViewModelFactory() {
        // Hilt will automatically inject the UseCases
    }

}