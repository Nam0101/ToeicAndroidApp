package com.example.englishapp.presentation.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.englishapp.databinding.ActivityForgotPasswordBinding;
import com.example.englishapp.presentation.viewmodel.ForgotPasswordViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ForgotPasswordActivity extends AppCompatActivity {

    private static final String TAG = "ForgotPasswordActivity";
    private ActivityForgotPasswordBinding binding;
    private ForgotPasswordViewModel forgotPasswordViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initializeViewModel();
        observeViewModel();
    }

    private void initializeViewModel() {
        forgotPasswordViewModel = new ViewModelProvider(this).get(ForgotPasswordViewModel.class);
        binding.setForgotPasswordViewModel(forgotPasswordViewModel);
    }

    private void observeViewModel() {
        forgotPasswordViewModel.isSucess.observe(this, this::handleSuccess);
        forgotPasswordViewModel.message.observe(this, this::showMessage);
        forgotPasswordViewModel.isLoading.observe(this, this::handleLoadingState);
    }

    private void handleSuccess(Boolean isSuccess) {
        if (isSuccess){
            finish();
        }
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