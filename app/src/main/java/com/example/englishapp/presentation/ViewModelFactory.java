package com.example.englishapp.presentation;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.englishapp.data.repository.UserRepository;
import com.example.englishapp.presentation.viewmodel.LoginViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private final UserRepository userRepository;

    public ViewModelFactory(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            // If it's the LoginViewModel class, create and return an instance
            return (T) new LoginViewModel(userRepository);
        }
        // Handle other ViewModel classes if necessary
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}