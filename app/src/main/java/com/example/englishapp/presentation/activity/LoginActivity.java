package com.example.englishapp.presentation.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.englishapp.R;
import com.example.englishapp.databinding.ActivityLoginBinding;
import com.example.englishapp.presentation.ViewModelFactory;
import com.example.englishapp.presentation.viewmodel.LoginViewModel;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginActivity extends AppCompatActivity {
    private LoginViewModel loginViewModel;
    private ActivityLoginBinding binding;
    private SharedPreferences sharedPreferences;
    @Inject
    ViewModelFactory viewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setLifecycleOwner(this);
        initializeViewModel();
        initializeSharedPreferences();
        observeViewModel();
    }

    private void initializeViewModel() {
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding.setLoginViewModel(loginViewModel);
    }

    private void initializeSharedPreferences() {
        sharedPreferences = getSharedPreferences("userPrefs", MODE_PRIVATE);
        String savedUsername = sharedPreferences.getString("username", "");
        String savedPassword = sharedPreferences.getString("password", "");

        if (!TextUtils.isEmpty(savedUsername) && !TextUtils.isEmpty(savedPassword)) {
            loginViewModel.email.set(savedUsername);
            loginViewModel.password.set(savedPassword);
        }
    }

    private void observeViewModel() {
        loginViewModel.loginSuccess.observe(this, this::handleLoginSuccess);
        loginViewModel.navigateToSignUp.observe(this, aVoid -> navigateToSignUp());
        loginViewModel.navigateToForgotPassword.observe(this, aVoid -> navigateToForgotPassword());
        loginViewModel.loginErrorMessage.observe(this, this::showErrorMessage);
        loginViewModel.isLoading.observe(this, this::handleLoadingState);
    }

    private void handleLoginSuccess(Boolean isSuccess) {
        if (isSuccess) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void navigateToSignUp() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    private void navigateToForgotPassword() {
        Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
        startActivity(intent);
    }

    private void showErrorMessage(String errorMessage) {
        if (errorMessage != null && !errorMessage.isEmpty()) {
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
        }
    }

    private void handleLoadingState(Boolean isLoading) {
        if (isLoading) {
            binding.progressBar.setVisibility(View.VISIBLE);
        } else {
            binding.progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", loginViewModel.savedUsername.get());
        editor.putString("password", loginViewModel.savedPassword.get());
        editor.apply();
    }
}