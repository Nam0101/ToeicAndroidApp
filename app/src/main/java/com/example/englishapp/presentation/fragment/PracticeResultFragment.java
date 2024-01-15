package com.example.englishapp.presentation.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.englishapp.R;
import com.example.englishapp.databinding.FragmentPracticeResultBinding;
import com.example.englishapp.domain.CurrentUser;
import com.example.englishapp.presentation.viewmodel.QuizSharedViewModel;
import com.example.englishapp.presentation.viewmodel.QuizViewModel;

public class PracticeResultFragment extends Fragment {

    FragmentPracticeResultBinding binding;

    public QuizSharedViewModel quizSharedViewModel;
    public QuizViewModel quizViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
        binding = FragmentPracticeResultBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        quizSharedViewModel = new ViewModelProvider(requireActivity()).get(QuizSharedViewModel.class);
        quizViewModel = new ViewModelProvider(requireActivity()).get(QuizViewModel.class);
        Log.i("PracticeResultFragment", "onCreateView: ");
        binding.textViewScore.setText("Score: " + quizSharedViewModel.score.get() + "/" + quizSharedViewModel.numberOfQuestion.getValue());
        binding.textViewPoints.setText("Points: " + quizSharedViewModel.points.get());
        int correctAnswer = quizSharedViewModel.score.get() == null ? 0 : Integer.parseInt(quizSharedViewModel.score.get());
        int totalQuestion = quizSharedViewModel.numberOfQuestion.getValue() == null ? 0 : Integer.parseInt(quizSharedViewModel.numberOfQuestion.getValue().toString());
        int user_id = CurrentUser.getInstance().getUser().getId();
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        java.util.Date currentDate = calendar.getTime();
        String examDate = currentDate.toString();
        int exam_id = quizSharedViewModel.examId;
        if(exam_id !=0 )  quizSharedViewModel.insertExamHistory(user_id, exam_id, correctAnswer, totalQuestion, examDate);
        binding.buttonClose.setOnClickListener(v -> {
            requireActivity().finish();
        });
        binding.buttonDetail.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.result_fragment_container, DetailFragment.newInstance())
                    .addToBackStack(null)
                    .commit();
            Log.i("PracticeResultFragment", "onCreateView: ");
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}