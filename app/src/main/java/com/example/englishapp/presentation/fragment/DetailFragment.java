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
import com.example.englishapp.presentation.activity.QuizActivity;
import com.example.englishapp.presentation.adapters.QuestionResultAdapter;
import com.example.englishapp.presentation.viewmodel.QuizSharedViewModel;
import com.example.englishapp.presentation.viewmodel.QuizViewModel;

public class DetailFragment extends Fragment {
    FragmentDetailBinding binding;
    QuizSharedViewModel quizSharedViewModel;
    QuizViewModel quizViewModel;
    public static DetailFragment newInstance() {
        return new DetailFragment();
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater, container, false);
        quizSharedViewModel = new ViewModelProvider(requireActivity()).get(QuizSharedViewModel.class);
        quizViewModel = new ViewModelProvider(requireActivity()).get(QuizViewModel.class);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        quizSharedViewModel.questionResults.observe(getViewLifecycleOwner(), questionResults -> {
            binding.questionResultRecyclerView.setAdapter(new QuestionResultAdapter(questionResults, this::handleClickItem));
        });
        binding.questionResultRecyclerView.setVisibility(View.VISIBLE);
        binding.questionResultRecyclerView.setLayoutManager(layoutManager);
        return binding.getRoot();
    }
    public void handleClickItem(int position){
        Log.i("DetailFragment", "handleClickItem: " + position);
        ((QuizActivity) requireActivity()).setCurrentItem(position);
    }
}
