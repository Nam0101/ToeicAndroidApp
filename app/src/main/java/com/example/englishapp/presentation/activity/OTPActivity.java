package com.example.englishapp.presentation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.englishapp.databinding.ActivityOtpactivityBinding;
import com.example.englishapp.presentation.viewmodel.OTPActivityViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class OTPActivity extends AppCompatActivity {
    ActivityOtpactivityBinding binding;
    OTPActivityViewModel otpActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOtpactivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //get intent
        String email = getIntent().getStringExtra("email");
        String mobile = getIntent().getStringExtra("mobile");
        otpActivityViewModel = new ViewModelProvider(this).get(OTPActivityViewModel.class);
        binding.setOtpActivityViewModel(otpActivityViewModel);
        otpActivityViewModel.email.set(email);
        otpActivityViewModel.mobile.set(mobile);
        otpActivityViewModel.message.observe(this, this::showMessage);
        otpActivityViewModel.isSucess.observe(this, this::handleSuccess);
        binding.otpET1.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    binding.otpET2.requestFocus();
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
        binding.otpET2.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    binding.otpET3.requestFocus();
                }
                else if (s.length() == 0) {
                    binding.otpET1.requestFocus();
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });
        binding.otpET3.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    binding.otpET4.requestFocus();
                }
                else if (s.length() == 0) {
                    binding.otpET2.requestFocus();
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });
        binding.otpET4.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    binding.otpET3.requestFocus();
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });
    }

    private void showMessage(String message) {
        if (message != null && !message.isEmpty()) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    private void handleSuccess(Boolean isSuccess) {
        if (isSuccess){
            Intent intent = new Intent(this, NewPasswordActivity.class);
            intent.putExtra("email", otpActivityViewModel.email.get());
            intent.putExtra("mobile", otpActivityViewModel.mobile.get());
            intent.putExtra("code", binding.otpET1.getText().toString() + binding.otpET2.getText().toString() + binding.otpET3.getText().toString() + binding.otpET4.getText().toString());
            startActivity(intent);
        }
    }
}
