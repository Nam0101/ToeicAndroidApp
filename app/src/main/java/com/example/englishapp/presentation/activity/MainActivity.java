package com.example.englishapp.presentation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.englishapp.data.model.Function;
import com.example.englishapp.databinding.ActivityMainBinding;
import com.example.englishapp.presentation.adapters.FunctionAdapter;
import com.example.englishapp.presentation.viewmodel.MainActivityViewModel;

import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ActivityMainBinding binding;
    private MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initializeViewModel();
        initializeDrawerToggle();
        initializeRecyclerView();
    }

    private void initializeViewModel() {
        Intent loginIntent = getIntent();
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        if (loginIntent != null) {
            mainActivityViewModel.setUser(Objects.requireNonNull(loginIntent.getParcelableExtra("user")));
        }
        binding.setMainActivityViewModel(mainActivityViewModel);
    }

    private void initializeDrawerToggle() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar, 0, 0);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void initializeRecyclerView() {
        binding.functionRecycleview.setLayoutManager(new LinearLayoutManager(this));
        mainActivityViewModel.functions.observe(this, functions -> {
            if (functions != null && !functions.isEmpty()) {
                binding.functionRecycleview.setVisibility(View.VISIBLE);
                FunctionAdapter functionAdapter = new FunctionAdapter(functions, this::handleFunctionClick);
                binding.functionRecycleview.setAdapter(functionAdapter);
            }
        });
    }

    private void handleFunctionClick(Function function) {
        switch (function.getId()){
            case 1:
                // Handle case 1
                break;
            case 2:
                // Handle case 2
                break;
            case 3:
                // Handle case 3
                break;
            case 4:
                // Handle case 4
                break;
            case 5:
                // Handle case 5
                break;
            case 6:
                // Handle case 6
                break;
            case 7:
                // Handle case 7
                break;
            case 8:
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}