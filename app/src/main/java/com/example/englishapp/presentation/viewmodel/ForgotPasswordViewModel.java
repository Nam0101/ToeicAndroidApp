package com.example.englishapp.presentation.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.englishapp.domain.ForgotPasswordUseCase;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ForgotPasswordViewModel  extends ViewModel {

    private final ForgotPasswordUseCase forgotPasswordUseCase;

    @Inject
    public ForgotPasswordViewModel(ForgotPasswordUseCase forgotPasswordUseCase) {
        this.forgotPasswordUseCase = forgotPasswordUseCase;
    }

    public void onclickSearch(){
    }

    public void onClickSignIn(){
    }
}

