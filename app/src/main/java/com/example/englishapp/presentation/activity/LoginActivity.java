package com.example.englishapp.presentation.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.englishapp.R;
import com.example.englishapp.databinding.ActivityLoginBinding;
import com.example.englishapp.presentation.viewmodel.LoginViewModel;

import dagger.hilt.android.AndroidEntryPoint;
@AndroidEntryPoint
public class LoginActivity extends AppCompatActivity {
    LoginViewModel loginViewModel;
    ActivityLoginBinding binding;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setLifecycleOwner(this);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding.setLoginViewModel(loginViewModel);


        sharedPreferences = getSharedPreferences("userPrefs", MODE_PRIVATE);

        String savedUsername = sharedPreferences.getString("username", "");
        String savedPassword = sharedPreferences.getString("password", "");

        if (!TextUtils.isEmpty(savedUsername) && !TextUtils.isEmpty(savedPassword)) {
            loginViewModel.email.set(savedUsername);
            loginViewModel.password.set(savedPassword);
            loginViewModel.login(savedUsername, savedPassword);
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