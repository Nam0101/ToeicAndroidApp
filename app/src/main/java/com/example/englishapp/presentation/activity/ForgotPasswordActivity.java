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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityForgotPasswordBinding binding = ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ForgotPasswordViewModel forgotPasswordViewModel = new ViewModelProvider(this).get(ForgotPasswordViewModel.class);
        binding.setForgotPasswordViewModel(forgotPasswordViewModel);
        forgotPasswordViewModel.isSucess.observe(this, isSuccess -> {
            if (isSuccess){
                finish();
            }
        });
        forgotPasswordViewModel.message.observe(this, message -> {
            if (message != null && !message.isEmpty()) {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }
        });
        forgotPasswordViewModel.isLoading.observe(this, isLoading -> {
            if (isLoading) {
                binding.progressBar.setVisibility(View.VISIBLE);
            } else {
                binding.progressBar.setVisibility(View.GONE);
            }
        });

    }

}