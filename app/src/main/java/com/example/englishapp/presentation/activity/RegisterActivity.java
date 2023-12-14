package com.example.englishapp.presentation.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.englishapp.databinding.ActivityRegisterBinding;
import com.example.englishapp.presentation.ViewModelFactory;
import com.example.englishapp.presentation.viewmodel.RegisterViewModel;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";

    @Inject
    ViewModelFactory viewModelFactory;
    private ActivityRegisterBinding binding;
    private RegisterViewModel registerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initializeViewModel();
        observeViewModel();
    }

    private void initializeViewModel() {
        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        binding.setRegiterViewModel(registerViewModel);
    }

    private void observeViewModel() {
        registerViewModel.getNavigateToLogin().observe(this, aVoid -> navigateToLogin());
        registerViewModel.message.observe(this, this::showMessage);
        registerViewModel.isLoading.observe(this, this::handleLoadingState);
    }

    private void navigateToLogin() {
        Toast.makeText(this, "Register success", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void showMessage(String message) {
        if (message != null && !message.isEmpty()) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    private void handleLoadingState(Boolean isLoading) {
        if (isLoading) {
            binding.progressBar.setVisibility(View.VISIBLE);
        } else {
            binding.progressBar.setVisibility(View.GONE);
        }
    }
}