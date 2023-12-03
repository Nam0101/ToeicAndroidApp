package com.example.englishapp.presentation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.englishapp.databinding.ActivityQuizBinding;
import com.example.englishapp.presentation.adapters.QuizPagerAdapter;
import com.example.englishapp.presentation.viewmodel.QuizViewModel;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class QuizActivity extends AppCompatActivity {
    ActivityQuizBinding binding;
    QuizViewModel quizViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if(intent == null) return;
        ArrayList<Integer> parts = intent.getIntegerArrayListExtra("selectedParts");
        for(int i = 0; i < parts.size(); i++){
            Log.i("QuizActivity", "Part " + parts.get(i));
        }
        binding = ActivityQuizBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        quizViewModel = new ViewModelProvider(this).get(QuizViewModel.class);
        binding.setQuizViewModel(quizViewModel);

        quizViewModel.part5QuizQuestions.observe(this, part5QuizQuestions -> {
            if(part5QuizQuestions == null || part5QuizQuestions.isEmpty()) return;
            QuizPagerAdapter adapter = new QuizPagerAdapter(getSupportFragmentManager(), getLifecycle(), part5QuizQuestions);
            binding.viewPager.setAdapter(adapter);
        });
    }

    public void onNextButtonClick(View view) {
        int currentItem = binding.viewPager.getCurrentItem();
        if (currentItem < quizViewModel.part5QuizQuestions.getValue().size() - 1) {
            binding.viewPager.setCurrentItem(currentItem + 1);
        }
    }
    public void onBackButtonClick(View view) {
        int currentItem = binding.viewPager.getCurrentItem();
        if (currentItem > 0) {
            binding.viewPager.setCurrentItem(currentItem - 1);
        }
    }
}
