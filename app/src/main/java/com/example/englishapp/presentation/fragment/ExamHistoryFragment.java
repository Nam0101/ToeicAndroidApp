package com.example.englishapp.presentation.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.englishapp.R;
import com.example.englishapp.presentation.viewmodel.MainActivityViewModel;

public class ExamHistoryFragment extends Fragment {
    MainActivityViewModel mainActivityViewModel;
    RecyclerView recyclerView;

    public static ExamHistoryFragment newInstance() {
        return new ExamHistoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exam_history, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewExamHistory);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mainActivityViewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
        mainActivityViewModel.examResults.observe(getViewLifecycleOwner(), examResults -> {
            recyclerView.setAdapter(new com.example.englishapp.presentation.adapters.ExamHistoryAdapter(examResults));
        });
        return view;
    }
}