package com.example.englishapp.presentation;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.englishapp.domain.ForgotPasswordUseCase;
import com.example.englishapp.domain.GetFunctionsUseCase;
import com.example.englishapp.domain.GetPart1QuestionUseCase;
import com.example.englishapp.domain.GetPart5QuestionUseCase;
import com.example.englishapp.domain.GetPart6QuestionUseCase;
import com.example.englishapp.domain.GetPart7QuestionUseCase;
import com.example.englishapp.domain.LoginUseCase;
import com.example.englishapp.domain.RegisterUseCase;
import com.example.englishapp.presentation.viewmodel.ForgotPasswordViewModel;
import com.example.englishapp.presentation.viewmodel.LoginViewModel;
import com.example.englishapp.presentation.viewmodel.MainActivityViewModel;
import com.example.englishapp.presentation.viewmodel.QuizViewModel;
import com.example.englishapp.presentation.viewmodel.RegisterViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private final LoginUseCase loginUseCase;
    private final RegisterUseCase registerUseCase;
    private final ForgotPasswordUseCase forgotPasswordUseCase;
    private final GetFunctionsUseCase getFunctionsUseCase;
    private final GetPart5QuestionUseCase getPart5QuestionUseCase;
    private final GetPart6QuestionUseCase getPart6QuestionUseCase;
    private final GetPart7QuestionUseCase getPart7QuestionUseCase;
    private final GetPart1QuestionUseCase getPart1QuestionUseCase;

    public ViewModelFactory(LoginUseCase loginUseCase, RegisterUseCase registerUseCase, ForgotPasswordUseCase forgotPasswordUseCase, GetFunctionsUseCase getFunctionsUseCase, GetPart5QuestionUseCase getPart5QuestionUseCase, GetPart6QuestionUseCase getPart6QuestionUseCase, GetPart7QuestionUseCase getPart7QuestionUseCase, GetPart1QuestionUseCase getPart1QuestionUseCase) {
        this.loginUseCase = loginUseCase;
        this.registerUseCase = registerUseCase;
        this.forgotPasswordUseCase = forgotPasswordUseCase;
        this.getFunctionsUseCase = getFunctionsUseCase;
        this.getPart5QuestionUseCase = getPart5QuestionUseCase;
        this.getPart6QuestionUseCase = getPart6QuestionUseCase;
        this.getPart7QuestionUseCase = getPart7QuestionUseCase;
        this.getPart1QuestionUseCase = getPart1QuestionUseCase;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            // If it's the LoginViewModel class, create and return an instance
            return (T) new LoginViewModel(loginUseCase);
        }
        else if (modelClass.isAssignableFrom(RegisterViewModel.class)) {
            // If it's the RegisterViewmodel class, create and return an instance
            return (T) new RegisterViewModel(registerUseCase);
        }
        else if (modelClass.isAssignableFrom(ForgotPasswordViewModel.class)) {
            // If it's the ForgotPasswordViewModel class, create and return an instance
            return (T) new ForgotPasswordViewModel(forgotPasswordUseCase);
        }
        else if (modelClass.isAssignableFrom(MainActivityViewModel.class)) {
            // If it's the MainActivityViewModel class, create and return an instance
            return (T) new MainActivityViewModel(getFunctionsUseCase);
        }
        else if (modelClass.isAssignableFrom(QuizViewModel.class)) {
            // If it's the QuizViewModel class, create and return an instance
            return (T) new QuizViewModel(getPart5QuestionUseCase, getPart6QuestionUseCase, getPart7QuestionUseCase, getPart1QuestionUseCase);
        }

        // Handle other ViewModel classes if necessary
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}