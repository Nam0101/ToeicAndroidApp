package com.example.englishapp.presentation.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.englishapp.data.model.Exam;
import com.example.englishapp.data.model.Function;
import com.example.englishapp.data.model.Part1QuizQuestion;
import com.example.englishapp.data.model.Part2QuizQuestion;
import com.example.englishapp.data.model.Part3QuizQuestion;
import com.example.englishapp.data.model.Part4QuizQuestion;
import com.example.englishapp.data.model.Part5QuizQuestion;
import com.example.englishapp.data.model.Part6QuizQuestion;
import com.example.englishapp.data.model.Part7QuizQuestion;
import com.example.englishapp.data.model.QuizQuestion;
import com.example.englishapp.databinding.ActivityThithuBinding;
import com.example.englishapp.domain.Functions;
import com.example.englishapp.presentation.adapters.ExamListAdapter;
import com.example.englishapp.presentation.adapters.FunctionAdapter;
import com.example.englishapp.presentation.viewmodel.ExamViewModel;
import com.example.englishapp.presentation.viewmodel.QuizSharedViewModel;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ExamActivity extends AppCompatActivity {
    private static final String TAG = "ExamActivity";
    private ArrayList<Function> functions;
    private ActivityThithuBinding binding;
    private ExamViewModel examViewModel;
    private QuizSharedViewModel quizSharedViewModel;

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        examViewModel = new ViewModelProvider(this).get(ExamViewModel.class);
        quizSharedViewModel = new ViewModelProvider(this).get(QuizSharedViewModel.class);
        initializeBinding();
        initializeRecyclerView();
        initializeDrawerToggle();
        observeExamList();
    }

    private void observeExamList() {
        examViewModel.examList.observe(this, exams -> {
            if (exams != null) {
                binding.examRecyclerview.setLayoutManager(new LinearLayoutManager(this));
                binding.examRecyclerview.setVisibility(View.VISIBLE);
                ExamListAdapter examListAdapter = new ExamListAdapter(exams, this::handleExamClick);
                binding.examRecyclerview.setAdapter(examListAdapter);
            }
        });
        examViewModel.errmsg.observe(this, s -> {
            if (s != null) {
                Toast.makeText(this, "No data found for this Test", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private int getPartNumber(QuizQuestion question) {
        if (question instanceof Part1QuizQuestion) {
            return 1;
        } else if (question instanceof Part2QuizQuestion) {
            return 2;
        } else if (question instanceof Part3QuizQuestion) {
            return 3;
        } else if (question instanceof Part4QuizQuestion) {
            return 4;
        } else if (question instanceof Part5QuizQuestion) {
            return 5;
        } else if (question instanceof Part6QuizQuestion) {
            return 6;
        } else if (question instanceof Part7QuizQuestion) {
            return 7;
        } else {
            return Integer.MAX_VALUE; // or throw an exception
        }
    }
    private void handleExamClick(Exam exam) {
        Log.i(TAG, "handleExamClick: " + exam.getTendethi());
        int id = Integer.parseInt(exam.getId());
        examViewModel.getQuestionByExamId(id);
        examViewModel.quizQuestions.observe(this, quizQuestions -> {
            if (quizQuestions != null ) {
                if (quizQuestions.isEmpty() || quizQuestions.size() < 170) return;
                quizQuestions.sort((q1, q2) -> {
                    int part1 = getPartNumber(q1);
                    int part2 = getPartNumber(q2);
                    return Integer.compare(part1, part2);
                });
                Intent intent = new Intent(ExamActivity.this, QuizActivity.class);
                intent.putExtra("quizQuestions", quizQuestions);
                intent.putExtra("time", 7200000);
                startActivity(intent);
            }
        });
    }


    private void initializeRecyclerView() {
        functions = Functions.getInstance().getFunctions();
        binding.functionRecycleview.setLayoutManager(new LinearLayoutManager(this));
        binding.functionRecycleview.setVisibility(View.VISIBLE);
        FunctionAdapter functionAdapter = new FunctionAdapter(functions, this::handleFunctionClick);
        binding.functionRecycleview.setAdapter(functionAdapter);
    }

    private void initializeBinding() {
        binding = ActivityThithuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void initializeDrawerToggle() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar, 0, 0);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void handleFunctionClick(Function function) {
        switch (function.getId()) {
            case 1:
                Intent intent = new Intent(ExamActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case 2:
                Intent intent1 = new Intent(ExamActivity.this, QuizActivity.class);
                startActivity(intent1);
                break;
            case 3:
                break;
            case 4:
                Intent intent2 = new Intent(ExamActivity.this, VocabularyActivity.class);
                startActivity(intent2);
                break;
            case 6:
                new AlertDialog.Builder(this)
                        .setTitle("Đăng xuất")
                        .setMessage("Bạn có chắc chắn muốn đăng xuất?")
                        .setNegativeButton("Không", null)
                        .setPositiveButton("Có", (dialog, which) -> {
                            Intent loginIntent = new Intent(ExamActivity.this, LoginActivity.class);
                            startActivity(loginIntent);
                            finish();
                        }).create().show();
                break;
        }
    }
}
