package com.example.englishapp.presentation;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.englishapp.domain.LoginUseCase;
import com.example.englishapp.presentation.viewmodel.LoginViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private final LoginUseCase loginUseCase;

    public ViewModelFactory(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            // If it's the LoginViewModel class, create and return an instance
            return (T) new LoginViewModel(loginUseCase);
        }
        // Handle other ViewModel classes if necessary
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}