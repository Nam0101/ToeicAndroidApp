package com.example.englishapp.presentation.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
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
    LoginViewModel loginViewModel;
    ActivityLoginBinding binding;
    private SharedPreferences sharedPreferences;
    @Inject
    ViewModelFactory viewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setLifecycleOwner(this);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding.setLoginViewModel(loginViewModel);
        loginViewModel.loginSuccess.observe(this, isSuccess -> {
            if (isSuccess) {
                loginViewModel.user.observe(this, user -> {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    if (user != null) {
                        intent.putExtra("user", user);
                    }
                    startActivity(intent);
                    finish();
                });
            }
        });
        loginViewModel.navigateToSignUp.observe(this, aVoid -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
        
        loginViewModel.navigateToForgotPassword.observe(this, aVoid -> {
            Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
            startActivity(intent);
        });

        loginViewModel.loginErrorMessage.observe(this, errorMessage -> {
            if (errorMessage != null && !errorMessage.isEmpty()) {
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });

        loginViewModel.isLoading.observe(this, isLoading -> {
            if (isLoading) {
                binding.progressBar.setVisibility(android.view.View.VISIBLE);
            } else {
                binding.progressBar.setVisibility(android.view.View.GONE);
            }
        });

        // Do server không có token nên không thể lưu lại được thông tin đăng nhập
        // Nên mình sẽ lưu lại thông tin đăng nhập vào SharedPreferences
        // Khi mở app lên, mình sẽ lấy thông tin đăng nhập từ SharedPreferences
        sharedPreferences = getSharedPreferences("userPrefs", MODE_PRIVATE);
        String savedUsername = sharedPreferences.getString("username", "");
        String savedPassword = sharedPreferences.getString("password", "");

        if (!TextUtils.isEmpty(savedUsername) && !TextUtils.isEmpty(savedPassword)) {
            loginViewModel.email.set(savedUsername);
            loginViewModel.password.set(savedPassword);
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