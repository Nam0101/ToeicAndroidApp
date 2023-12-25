package com.example.englishapp.di;

import com.example.englishapp.domain.ForgotPasswordUseCase;
import com.example.englishapp.domain.GetFunctionsUseCase;
import com.example.englishapp.domain.GetPart1QuestionUseCase;
import com.example.englishapp.domain.GetPart5QuestionUseCase;
import com.example.englishapp.domain.GetPart6QuestionUseCase;
import com.example.englishapp.domain.GetPart7QuestionUseCase;
import com.example.englishapp.domain.LoginUseCase;
import com.example.englishapp.domain.RegisterUseCase;
import com.example.englishapp.presentation.ViewModelFactory;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityRetainedComponent;

@Module
@InstallIn(ActivityRetainedComponent.class)
public class ViewModelModule {

    @Provides
    public ViewModelFactory provideViewModelFactory(LoginUseCase loginUseCase, RegisterUseCase registerUseCase, ForgotPasswordUseCase forgotPasswordUseCase, GetFunctionsUseCase getFunctionsUseCase, GetPart5QuestionUseCase getPart5QuestionUseCase, GetPart6QuestionUseCase getPart6QuestionUseCase, GetPart7QuestionUseCase getPart7QuestionUseCase, GetPart1QuestionUseCase getPart1QuestionUseCase) {
        return new ViewModelFactory(loginUseCase, registerUseCase, forgotPasswordUseCase, getFunctionsUseCase, getPart5QuestionUseCase, getPart6QuestionUseCase, getPart7QuestionUseCase, getPart1QuestionUseCase);
    }

}