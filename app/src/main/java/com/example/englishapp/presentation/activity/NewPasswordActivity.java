package com.example.englishapp.presentation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.englishapp.databinding.ActivityNewPassWordBinding;
import com.example.englishapp.presentation.viewmodel.NewPasswordActivityViewModel;

import dagger.hilt.android.AndroidEntryPoint;
@AndroidEntryPoint
public class NewPasswordActivity extends AppCompatActivity {
    ActivityNewPassWordBinding binding;
    NewPasswordActivityViewModel newPasswordActivityViewModel;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewPassWordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        newPasswordActivityViewModel = new ViewModelProvider(this).get(NewPasswordActivityViewModel.class);
        //get email from intent
        String email = getIntent().getStringExtra("email");
        newPasswordActivityViewModel.email.set(email);
        //get mobile from intent
        String mobile = getIntent().getStringExtra("mobile");
        newPasswordActivityViewModel.mobile.set(mobile);
        String code = getIntent().getStringExtra("code");
        newPasswordActivityViewModel.code.set(code);
        binding.setNewPasswordActivityViewModel(newPasswordActivityViewModel);
        newPasswordActivityViewModel.message.observe(this, this::showMessage);
        newPasswordActivityViewModel.isNavigateToSignIn.observe(this, this::handleSuccess);
    }
    public void showMessage(String message){
        if(message != null){
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }
    public void handleSuccess(Boolean isSuccess){
        if(isSuccess){
            Toast.makeText(this, "Change password successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }
}
