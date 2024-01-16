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
        //convert date to YYYY/MM/DD like 2021/05/20
        String[] date = examDate.split(" ");
        //convert date[1] to number
        String month = monthTextToNumber(date[1]);
        examDate = date[5] + "/" + month + "/" + date[2];
        int exam_id = quizSharedViewModel.examId;
        Log.i("PracticeResultFragment", "onCreateView: exam_id" + exam_id);
        Log.i("PracticeResultFragment", "onCreateView: user_id" + user_id);
        Log.i("PracticeResultFragment", "onCreateView: correctAnswer" + correctAnswer);
        Log.i("PracticeResultFragment", "onCreateView: totalQuestion" + totalQuestion);
        Log.i("PracticeResultFragment", "onCreateView: examDate" + examDate);
        if(exam_id !=0 )  quizSharedViewModel.insertExamHistory(user_id, exam_id, correctAnswer, totalQuestion, examDate);
        binding.buttonClose.setOnClickListener(v -> {
            requireActivity().finish();
        });
        binding.buttonDetail.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.result_fragment_container, DetailFragment.newInstance())
                    .addToBackStack(null)
                    .commit();

        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
    private String monthTextToNumber(String monthText){
        switch (monthText){
            case "Jan":
                return "01";
            case "Feb":
                return "02";
            case "Mar":
                return "03";
            case "Apr":
                return "04";
            case "May":
                return "05";
            case "Jun":
                return "06";
            case "Jul":
                return "07";
            case "Aug":
                return "08";
            case "Sep":
                return "09";
            case "Oct":
                return "10";
            case "Nov":
                return "11";
            case "Dec":
                return "12";
            default:
                return "00";
        }
    }
}