package com.example.englishapp.presentation.activity;

import androidx.appcompat.app.AppCompatActivity;

import com.example.englishapp.presentation.viewmodel.ForgotPasswordViewModel;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ForgotPasswordActivity extends AppCompatActivity {

    private final ForgotPasswordViewModel forgotPasswordViewModel;

    @Inject
    public ForgotPasswordActivity(ForgotPasswordViewModel forgotPasswordViewModel) {
        this.forgotPasswordViewModel = forgotPasswordViewModel;
    }
}
