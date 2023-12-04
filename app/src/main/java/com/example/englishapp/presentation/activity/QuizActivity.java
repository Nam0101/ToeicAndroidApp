package com.example.englishapp.presentation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.englishapp.data.model.Function;
import com.example.englishapp.databinding.ActivityQuizBinding;
import com.example.englishapp.presentation.adapters.FunctionAdapter;
import com.example.englishapp.presentation.adapters.QuizPagerAdapter;
import com.example.englishapp.presentation.viewmodel.QuizViewModel;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class QuizActivity extends AppCompatActivity {
    ActivityQuizBinding binding;
    QuizViewModel quizViewModel;
    private ArrayList<Function> functions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if(intent == null) return;
        ArrayList<Integer> parts = intent.getIntegerArrayListExtra("selectedParts");
        functions = intent.getParcelableArrayListExtra("functions");
        binding = ActivityQuizBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        quizViewModel = new ViewModelProvider(this).get(QuizViewModel.class);
        binding.setQuizViewModel(quizViewModel);

        quizViewModel.part5QuizQuestions.observe(this, part5QuizQuestions -> {
            if(part5QuizQuestions == null || part5QuizQuestions.isEmpty()) return;
            QuizPagerAdapter adapter = new QuizPagerAdapter(getSupportFragmentManager(), getLifecycle(), part5QuizQuestions);
            binding.viewPager.setAdapter(adapter);
            quizViewModel.isFragmentVisible.set(true);
        });
        initializeDrawerToggle();
        initializeRecyclerView();
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
    private void initializeDrawerToggle() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar, 0, 0);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void initializeRecyclerView() {
        binding.functionRecycleview.setLayoutManager(new LinearLayoutManager(this));
        binding.functionRecycleview.setVisibility(View.VISIBLE);
        FunctionAdapter functionAdapter = new FunctionAdapter(functions, this::handleFunctionClick);
        binding.functionRecycleview.setAdapter(functionAdapter);

    }

    private void handleFunctionClick(Function function) {
        Log.i("TAG", "handleFunctionClick: " + function.getName());
    }
}
