package com.example.englishapp.presentation.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.englishapp.databinding.FragmentPracticeResultBinding;
import com.example.englishapp.presentation.viewmodel.QuizSharedViewModel;

public class PracticeResultFragment extends Fragment {

    FragmentPracticeResultBinding binding;

    public QuizSharedViewModel quizSharedViewModel;

    @Override
public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
        binding = FragmentPracticeResultBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        quizSharedViewModel = new ViewModelProvider(requireActivity()).get(QuizSharedViewModel.class);
        Log.i("PracticeResultFragment", "onCreateView: ");
        binding.textViewScore.setText("Score: " + quizSharedViewModel.score.get() + "/" + quizSharedViewModel.numberOfQuestion.getValue());
        binding.textViewPoints.setText("Points: " + quizSharedViewModel.points.get());
        binding.buttonClose.setOnClickListener(v -> {
            requireActivity().finish();
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}