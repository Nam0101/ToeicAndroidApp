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
import com.example.englishapp.presentation.viewmodel.QuizViewModel;

public class PracticeResultFragment extends Fragment {

    FragmentPracticeResultBinding binding;

    public QuizSharedViewModel quizSharedViewModel;
    private QuizViewModel quizViewModel;

    @Override
public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
    binding = FragmentPracticeResultBinding.inflate(inflater, container, false);
    View view = binding.getRoot();
    quizViewModel = new ViewModelProvider(requireActivity()).get(QuizViewModel.class);
    quizSharedViewModel = new ViewModelProvider(requireActivity()).get(QuizSharedViewModel.class);
        quizViewModel.isFragmentVisible.set(false);
        quizSharedViewModel.isResultFragmentVisiable.set(true);
    Log.i("PracticeResultFragment", "onCreateView: " + quizSharedViewModel.getQuestionResults().getValue().size());
    String score = String.valueOf(quizSharedViewModel.score.get());
    String points = String.valueOf(quizSharedViewModel.points.get());

    Log.i("PracticeResultFragment", "Score: " + score + ", Points: " + points);
    Log.i("PracticeResultFragment", "onCreateView: " + quizSharedViewModel.isResultFragmentVisiable.get());
//    Log.i("PracticeResultFragment", "onCreateView: " + quizViewModel.isFragmentVisible.get());
    binding.textViewResult.setText("Results"); // Set some text for textViewResult
    binding.textViewScore.setText(score);
    binding.textViewPoints.setText(points);
    binding.buttonClose.setOnClickListener(v -> {
        requireActivity().finish();
    });
    return view;
}

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        Log.i("PracticeResultFragment", "onDestroyView: ");
    }
}