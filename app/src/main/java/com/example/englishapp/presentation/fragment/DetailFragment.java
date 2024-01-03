package com.example.englishapp.presentation.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.englishapp.databinding.FragmentDetailBinding;
import com.example.englishapp.presentation.adapters.QuestionResultAdapter;
import com.example.englishapp.presentation.viewmodel.QuizSharedViewModel;

public class DetailFragment extends Fragment {
    FragmentDetailBinding binding;
    QuizSharedViewModel quizSharedViewModel;
    public static DetailFragment newInstance() {
        return new DetailFragment();
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater, container, false);
        quizSharedViewModel = new ViewModelProvider(requireActivity()).get(QuizSharedViewModel.class);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        quizSharedViewModel.questionResults.observe(getViewLifecycleOwner(), questionResults -> {
            binding.questionResultRecyclerView.setAdapter(new QuestionResultAdapter(questionResults));
        });
        binding.questionResultRecyclerView.setVisibility(View.VISIBLE);
        binding.questionResultRecyclerView.setLayoutManager(layoutManager);
        Log.i("DetailFragment", "onCreateView: "+ quizSharedViewModel.questionResults.getValue().size());
        return binding.getRoot();
    }
}
