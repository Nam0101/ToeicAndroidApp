package com.example.englishapp.presentation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.englishapp.data.model.Function;
import com.example.englishapp.databinding.ActivityQuizBinding;
import com.example.englishapp.domain.Functions;
import com.example.englishapp.presentation.adapters.FunctionAdapter;
import com.example.englishapp.presentation.adapters.QuizPagerAdapter;
import com.example.englishapp.presentation.viewmodel.QuizSharedViewModel;
import com.example.englishapp.presentation.viewmodel.QuizViewModel;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class QuizActivity extends AppCompatActivity {
    private static final String TAG = "QuizActivity";
    private static final String SELECTED_PARTS_KEY = "selectedParts";
    private static final String FUNCTIONS_KEY = "functions";

    private ActivityQuizBinding binding;
    private QuizViewModel quizViewModel;
    private QuizSharedViewModel quizSharedViewModel;
    private ArrayList<Function> functions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeBinding();
        initializeViewModels();
        observeQuizQuestions();
        initializeDrawerToggle();
        initializeRecyclerView();
    }

    private void initializeBinding() {
        binding = ActivityQuizBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void initializeViewModels() {
        quizViewModel = new ViewModelProvider(this).get(QuizViewModel.class);
        quizSharedViewModel = new ViewModelProvider(this).get(QuizSharedViewModel.class);
        binding.setQuizViewModel(quizViewModel);
        quizSharedViewModel.getQuestionResults().observe(this, questionResults -> {
            if (questionResults == null || questionResults.isEmpty()) return;
            quizViewModel.isFragmentVisible.set(false);
            finish();

            Log.i(TAG, "Test: " + questionResults.size());
            for(int i = 0; i < questionResults.size(); i++){
                Log.i(TAG, "Test: " + questionResults.get(i).getSelectedAnswer());
            }
        });
    }

    private void observeQuizQuestions() {
        quizViewModel.part5QuizQuestions.observe(this, part5QuizQuestions -> {
            if (part5QuizQuestions == null || part5QuizQuestions.isEmpty()) return;
            Log.i(TAG, "onCreate: " + part5QuizQuestions.size());
            QuizPagerAdapter adapter = new QuizPagerAdapter(getSupportFragmentManager(), getLifecycle(), part5QuizQuestions);
            binding.viewPager.setAdapter(adapter);
            quizViewModel.isFragmentVisible.set(true);
        });
        binding.viewPager.setOffscreenPageLimit(5 - 1);
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
        functions = Functions.getInstance().getFunctions();
        binding.functionRecycleview.setLayoutManager(new LinearLayoutManager(this));
        binding.functionRecycleview.setVisibility(View.VISIBLE);
        FunctionAdapter functionAdapter = new FunctionAdapter(functions, this::handleFunctionClick);
        binding.functionRecycleview.setAdapter(functionAdapter);
    }

    private void handleFunctionClick(Function function) {
        switch (function.getId()){
            case 1:
                handleCase1();
                break;
            case 2:
                handleCase2();
                break;
            // Handle other cases...
        }
    }

    private void handleCase1() {
        if(Boolean.TRUE.equals(quizViewModel.isFragmentVisible.get())){
            new AlertDialog.Builder(this)
                    .setTitle("Thoát bài thi")
                    .setMessage("Bạn có chắc chắn muốn thoát bài thi?")
                    .setNegativeButton("Không", null)
                    .setPositiveButton("Có", (dialog, which) -> {
                        Intent intent = new Intent(QuizActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }).create().show();
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START);
    }

    private void handleCase2() {
        if(Boolean.TRUE.equals(quizViewModel.isFragmentVisible.get())){
            new AlertDialog.Builder(this)
                    .setTitle("Thoát bài thi")
                    .setMessage("Bạn có chắc chắn muốn thoát bài thi?")
                    .setNegativeButton("Không", null)
                    .setPositiveButton("Có", (dialog, which) -> {
                        Intent intent = new Intent(QuizActivity.this, MainActivity.class);
                        intent.putExtra("isChoosePartFragmentVisible", true);
                        startActivity(intent);
                        finish();
                    }).create().show();
        }
    }

    // Handle other cases...
}