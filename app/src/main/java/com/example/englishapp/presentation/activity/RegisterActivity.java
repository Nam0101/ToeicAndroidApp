package com.example.englishapp.presentation.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.englishapp.databinding.ActivityRegisterBinding;
import com.example.englishapp.presentation.ViewModelFactory;
import com.example.englishapp.presentation.viewmodel.RegisterViewModel;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RegisterActivity  extends AppCompatActivity{
    private static final String TAG = "RegisterActivity";

    @Inject
    ViewModelFactory viewModelFactory;
    ActivityRegisterBinding binding;
    RegisterViewModel registerViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        binding.setRegiterViewModel(registerViewModel);
        registerViewModel.getNavigateToLogin().observe(this, aVoid -> {
            // Navigate to login screen
            finish();

        });
        }


}
