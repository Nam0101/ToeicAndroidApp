package com.example.englishapp.presentation;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.englishapp.domain.ForgotPasswordUseCase;
import com.example.englishapp.domain.GetFunctionsUseCase;
import com.example.englishapp.domain.LoginUseCase;
import com.example.englishapp.domain.RegisterUseCase;
import com.example.englishapp.presentation.viewmodel.ForgotPasswordViewModel;
import com.example.englishapp.presentation.viewmodel.LoginViewModel;
import com.example.englishapp.presentation.viewmodel.MainActivityViewModel;
import com.example.englishapp.presentation.viewmodel.RegisterViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private final LoginUseCase loginUseCase;
    private final RegisterUseCase registerUseCase;
    private final ForgotPasswordUseCase forgotPasswordUseCase;
    private final GetFunctionsUseCase getFunctionsUseCase;
    public ViewModelFactory(LoginUseCase loginUseCase, RegisterUseCase registerUseCase, ForgotPasswordUseCase forgotPasswordUseCase, GetFunctionsUseCase getFunctionsUseCase) {
        this.loginUseCase = loginUseCase;
        this.registerUseCase = registerUseCase;
        this.forgotPasswordUseCase = forgotPasswordUseCase;
        this.getFunctionsUseCase = getFunctionsUseCase;
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
        // Handle other ViewModel classes if necessary
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}