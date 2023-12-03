package com.example.englishapp.presentation.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.englishapp.databinding.ActivityQuizBinding;

public class QuizActivity extends AppCompatActivity {
    ActivityQuizBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuizBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

}
