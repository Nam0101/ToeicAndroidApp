package com.example.englishapp.presentation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.englishapp.databinding.ActivityMainBinding;
import com.example.englishapp.presentation.adapters.FunctionAdapter;
import com.example.englishapp.presentation.viewmodel.MainActivityViewModel;

import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    ActivityMainBinding binding;
    MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent loginIntent = getIntent();
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        if (loginIntent != null) {
            mainActivityViewModel.setUser(Objects.requireNonNull(loginIntent.getParcelableExtra("user")));
        }
        binding.setMainActivityViewModel(mainActivityViewModel);
        binding.functionRecycleview.setLayoutManager(new LinearLayoutManager(this));
        mainActivityViewModel.functions.observe(this, functions -> {
            if (functions != null && !functions.isEmpty()) {
                binding.functionRecycleview.setVisibility(View.VISIBLE);
                FunctionAdapter functionAdapter = new FunctionAdapter(functions, function -> {
                    switch (function.getId()){
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        case 4:
                            break;
                            case 5:
                            break;
                        case 6:
                            break;
                        case 7:
                            break;
                        case 8:
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                            break;
                    }
                });
                binding.functionRecycleview.setAdapter(functionAdapter);
            }
        });
    }



}