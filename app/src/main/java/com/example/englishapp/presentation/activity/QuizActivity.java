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
import androidx.viewpager2.widget.ViewPager2;

import com.example.englishapp.data.model.Function;
import com.example.englishapp.data.model.QuizQuestion;
import com.example.englishapp.databinding.ActivityQuizBinding;
import com.example.englishapp.domain.Functions;
import com.example.englishapp.presentation.adapters.FunctionAdapter;
import com.example.englishapp.presentation.adapters.QuestionListAdapter;
import com.example.englishapp.presentation.adapters.QuizPagerAdapter;
import com.example.englishapp.presentation.fragment.PracticeResultFragment;
import com.example.englishapp.presentation.viewmodel.QuizSharedViewModel;
import com.example.englishapp.presentation.viewmodel.QuizViewModel;

import java.util.ArrayList;
import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class QuizActivity extends AppCompatActivity {
    private static final String TAG = "QuizActivity";

    private ActivityQuizBinding binding;
    private QuizViewModel quizViewModel;
    private QuizSharedViewModel quizSharedViewModel;
    private ArrayList<Function> functions;
    private ArrayList<Integer> selectedParts;
    ArrayList<? extends QuizQuestion> quizQuestions;
    int time = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        initializeBinding();
        initializeViewModels();
        selectedParts = intent.getIntegerArrayListExtra("selectedParts");
        try{
            quizQuestions = intent.getParcelableArrayListExtra("quizQuestions");
            time = intent.getIntExtra("time", 0);
            quizViewModel.quizQuestions.setValue(quizQuestions);
            Log.i(TAG, "onCreate: " + Objects.requireNonNull(quizViewModel.quizQuestions.getValue()).size());
        }
        catch (Exception e){
            Log.i(TAG, "onCreate: " + e.getMessage());
        }
        if ((selectedParts == null || selectedParts.isEmpty()) && quizViewModel.quizQuestions.getValue() == null) {
            Intent mainIntent = new Intent(QuizActivity.this, MainActivity.class);
            startActivity(mainIntent);
            finish();
        }

        initializeDrawerToggle();
        initializeRecyclerView();
        observeQuizQuestions();
    }

    private void initializeBinding() {
        binding = ActivityQuizBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void initializeViewModels() {
        quizViewModel = new ViewModelProvider(this).get(QuizViewModel.class);
        quizSharedViewModel = new ViewModelProvider(this).get(QuizSharedViewModel.class);
        binding.setQuizViewModel(quizViewModel);
    }

    private void observeQuizQuestions() {
        quizViewModel.quizQuestions.observe(this, quizQuestions -> {
            if (quizQuestions == null || quizQuestions.isEmpty()) return;
            quizViewModel.startTimer(time);

            QuizPagerAdapter adapter = new QuizPagerAdapter(getSupportFragmentManager(), getLifecycle(), quizQuestions);
            binding.viewPager.setAdapter(adapter);
            binding.viewPager.setOffscreenPageLimit(1);

            quizViewModel.isFragmentVisible.set(true);
            quizSharedViewModel.numberOfQuestion.setValue(quizQuestions.size());
            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            binding.questionListRecyclerView.setLayoutManager(layoutManager);
            binding.questionListRecyclerView.setVisibility(View.VISIBLE);
            QuestionListAdapter questionListAdapter = new QuestionListAdapter(quizViewModel.quizQuestions.getValue(), quizSharedViewModel, position -> {
                binding.viewPager.setCurrentItem(position);
                binding.drawerLayout.closeDrawer(GravityCompat.START);
            }, binding.questionListRecyclerView);
            binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageSelected(int position) {
                    super.onPageSelected(position);
                    questionListAdapter.setSelectedPosition(position);
                }
            });
            binding.questionListRecyclerView.setAdapter(questionListAdapter);
        });
        quizViewModel.isQuizTimmerFinished.observe(this, isFinished -> {
            if(isFinished == null || !isFinished) return;
            quizSharedViewModel.calculateScore();
            quizViewModel.isFragmentVisible.set(false);
            PracticeResultFragment practiceResultFragment = new PracticeResultFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(binding.resultFragmentContainer.getId(), practiceResultFragment)
                    .commit();

        });
        if (selectedParts == null || selectedParts.isEmpty()) return;
        for(Integer part : selectedParts){
            quizViewModel.getQuizQuestions(part);
        }

    }

    public void onNextButtonClick(View view) {
        int currentItem = binding.viewPager.getCurrentItem();
        if (currentItem < Objects.requireNonNull(quizViewModel.quizQuestions.getValue()).size() - 1) {
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
            case 3:
                Intent mainIntent = new Intent(QuizActivity.this, ExamActivity.class);
                startActivity(mainIntent);
                finish();
                break;
            case 4:
                Intent intent = new Intent(QuizActivity.this, VocabularyActivity.class);
                startActivity(intent);
                break;
            case 6:
                new AlertDialog.Builder(this)
                        .setTitle("Đăng xuất")
                        .setMessage("Bạn có chắc chắn muốn đăng xuất?")
                        .setNegativeButton("Không", null)
                        .setPositiveButton("Có", (dialog, which) -> {
                            Intent loginIntent = new Intent(QuizActivity.this, LoginActivity.class);
                            startActivity(loginIntent);
                            finish();
                        }).create().show();
                break;
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

}