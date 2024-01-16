package com.example.englishapp.presentation.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.englishapp.R;
import com.example.englishapp.data.model.Function;
import com.example.englishapp.databinding.ActivityMainBinding;
import com.example.englishapp.domain.CurrentUser;
import com.example.englishapp.presentation.adapters.FunctionAdapter;
import com.example.englishapp.presentation.fragment.ChoosePartFragment;
import com.example.englishapp.presentation.fragment.ExamHistoryFragment;
import com.example.englishapp.presentation.viewmodel.MainActivityViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
        Intent intent = getIntent();
        if(intent.getBooleanExtra("isChoosePartFragmentVisible", false)){
            mainActivityViewModel.isFragmentVisible.set(true);
            ChoosePartFragment choosePartFragment = new ChoosePartFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, choosePartFragment)
                    .commit();
        }
    }

    private void initializeViewModel() {
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        if (mainActivityViewModel.user == null) {
            mainActivityViewModel.setUser(CurrentUser.getInstance().getUser());
        }
        binding.setMainActivityViewModel(mainActivityViewModel);
        binding.tvSelectedDate.setOnClickListener(view -> {
            Calendar calendar = Calendar.getInstance();
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);

            DatePickerDialog picker = new DatePickerDialog(MainActivity.this,
                    (view1, year1, monthOfYear, dayOfMonth) -> binding.tvSelectedDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1), year, month, day);
            picker.show();
            SimpleDateFormat input = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd");
            try {
                mainActivityViewModel.newDate.setValue(output.format(input.parse(binding.tvSelectedDate.getText().toString())));
                mainActivityViewModel.updateSelectedDate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        mainActivityViewModel.selectedDate.observe(this, selectedDate -> {
            if (selectedDate != null) {
                SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date date = inputFormat.parse(selectedDate);
                    String formattedDate = outputFormat.format(date);
                    binding.tvSelectedDate.setText(formattedDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        mainActivityViewModel.examResults.observe(
                this,
                examResults -> {
                    if (examResults != null && !examResults.isEmpty()) {
                        ExamHistoryFragment examHistoryFragment = new ExamHistoryFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, examHistoryFragment)
                                .commit();

                    }
                }
        );
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
        mainActivityViewModel.selectedParts.observe(this, selectedParts -> {
            if (selectedParts != null && !selectedParts.isEmpty()) {
                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                intent.putIntegerArrayListExtra("selectedParts", selectedParts);
                startActivity(intent);
            }
        });
    }

    private void handleFunctionClick(Function function) {
        switch (function.getId()){
            case 1:
                mainActivityViewModel.isFragmentVisible.set(false);
                binding.drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case 2:
                mainActivityViewModel.isFragmentVisible.set(true);
                ChoosePartFragment choosePartFragment = new ChoosePartFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, choosePartFragment)
                        .commit();
                binding.drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case 3:
                Intent intent1 = new Intent(MainActivity.this, ExamActivity.class);
                startActivity(intent1);
                break;
            case 4:
                Intent intent = new Intent(MainActivity.this, VocabularyActivity.class);
                startActivity(intent);
                break;
            case 5:
                // Handle case 5
                break;
            case 6:
                new AlertDialog.Builder(this)
                        .setTitle("Đăng xuất")
                        .setMessage("Bạn có chắc chắn muốn đăng xuất?")
                        .setNegativeButton("Không", null)
                        .setPositiveButton("Có", (dialog, which) -> {
                            Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(loginIntent);
                            finish();
                        }).create().show();
                break;
        }
    }
}