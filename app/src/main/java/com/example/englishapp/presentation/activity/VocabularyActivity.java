package com.example.englishapp.presentation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.englishapp.data.model.Function;
import com.example.englishapp.databinding.ActivityVocabularyBinding;
import com.example.englishapp.domain.Functions;
import com.example.englishapp.presentation.adapters.FunctionAdapter;
import com.example.englishapp.presentation.adapters.TopicAdapter;
import com.example.englishapp.presentation.adapters.VocabularyPagerAdapter;
import com.example.englishapp.presentation.viewmodel.VocabularyViewModel;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class VocabularyActivity extends AppCompatActivity {
    ActivityVocabularyBinding binding;
    VocabularyViewModel vocabularyViewModel;
    private ArrayList<Function> functions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVocabularyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initializeDrawerToggle();
        initializeRecyclerView();
        initializeViewModels();
        topicListObserver();
        vocabulariesObserver();
    }

    private void topicListObserver() {
        vocabularyViewModel.topics.observe(this, topics -> {
            if (topics != null) {
                binding.topicRecyclerview.setVisibility(View.VISIBLE);
                TopicAdapter topicAdapter = new TopicAdapter(topics, topic -> {
                    binding.topicRecyclerview.setVisibility(View.GONE);
                    int topic_id = topic.getId();
                    vocabularyViewModel.getVocabularyByTopic(topic_id);
                });
                LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                binding.topicRecyclerview.setAdapter(topicAdapter);
                binding.topicRecyclerview.setLayoutManager(layoutManager);

            }
        });
    }

    private void vocabulariesObserver() {
        vocabularyViewModel.vocabularies.observe(this, vocabularies -> {
            if (vocabularies != null) {
                binding.topicRecyclerview.setVisibility(View.GONE);
                binding.viewPagerVocabulary.setVisibility(View.VISIBLE);
                VocabularyPagerAdapter vocabularyPagerAdapter = new VocabularyPagerAdapter(this, vocabularies);
                binding.viewPagerVocabulary.setAdapter(vocabularyPagerAdapter);
                Log.i("VocabularyActivity", "vocabulariesObserver: " + vocabularies.size());
                binding.viewPagerVocabulary.setPageTransformer((page, position) -> {
                    page.setCameraDistance(20000);
                    if (position < -1) {
                        page.setAlpha(0);

                    } else if (position <= 0) {
                        page.setAlpha(1);
                        page.setTranslationX(0);
                        page.setScaleX(1);
                        page.setScaleY(1);

                    } else if (position <= 1) {
                        page.setAlpha(1 - position);

                        page.setTranslationX(page.getWidth() * -position);

                        float scaleFactor = 0.75f
                                + (1 - 0.75f) * (1 - Math.abs(position));
                        page.setScaleX(scaleFactor);
                        page.setScaleY(scaleFactor);

                    } else {
                        page.setAlpha(0);
                    }
                });
            }
        });
    }

    private void initializeViewModels() {
        vocabularyViewModel = new ViewModelProvider(this).get(VocabularyViewModel.class);
        binding.setVocabularyViewModel(vocabularyViewModel);
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
        switch (function.getId()) {
            case 1:
                Intent intent = new Intent(VocabularyActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case 2:
                Intent intent1 = new Intent(VocabularyActivity.this, QuizActivity.class);
                startActivity(intent1);
                break;
            case 3:
                Intent intent2 = new Intent(VocabularyActivity.this, ExamActivity.class);
                startActivity(intent2);
                finish();
                break;
            case 6:
                new AlertDialog.Builder(this)
                        .setTitle("Đăng xuất")
                        .setMessage("Bạn có chắc chắn muốn đăng xuất?")
                        .setNegativeButton("Không", null)
                        .setPositiveButton("Có", (dialog, which) -> {
                            Intent loginIntent = new Intent(VocabularyActivity.this, LoginActivity.class);
                            startActivity(loginIntent);
                            finish();
                        }).create().show();
                break;
        }
    }

}
