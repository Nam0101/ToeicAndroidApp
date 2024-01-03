package com.example.englishapp.presentation.fragment;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.englishapp.R;
import com.example.englishapp.data.model.QuestionResult;
import com.example.englishapp.data.model.QuizQuestion;
import com.example.englishapp.databinding.FragmentPart5QuizBinding;
import com.example.englishapp.presentation.viewmodel.QuizSharedViewModel;
import com.example.englishapp.presentation.viewmodel.QuizViewModel;

import java.util.Objects;

public class QuizPart5Fragment extends Fragment {

    public QuizSharedViewModel quizSharedViewModel;
    private QuizQuestion question;
    private int position;
    private int selectedAnswer;
    private QuizViewModel quizViewModel;
    private int trueAnswer=0;

    public static QuizPart5Fragment newInstance(QuizQuestion question, int position) {
        QuizPart5Fragment fragment = new QuizPart5Fragment();
        Bundle args = new Bundle();
        args.putParcelable("question", (Parcelable) question);
        args.putInt("position", position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle args = getArguments();
        View view;
        quizSharedViewModel = new ViewModelProvider(requireActivity()).get(QuizSharedViewModel.class);
        quizViewModel = new ViewModelProvider(requireActivity()).get(QuizViewModel.class);
        if (args != null) {
            question = args.getParcelable("question");
            position = args.getInt("position");
        }
        trueAnswer = Integer.parseInt(question.getDapan());
        FragmentPart5QuizBinding binding;
        binding = FragmentPart5QuizBinding.inflate(inflater, container, false);
        binding.radioGroupOptions.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton checkedRadioButton = group.findViewById(checkedId);
            int selectID = checkedRadioButton.getId();
            if (selectID == R.id.radioButtonOptionA) {
                selectedAnswer = 1;
            } else if (selectID == R.id.radioButtonOptionB) {
                selectedAnswer = 2;
            } else if (selectID == R.id.radioButtonOptionC) {
                selectedAnswer = 3;
            } else if (selectID == R.id.radioButtonOptionD) {
                selectedAnswer = 4;
            }
            boolean result = selectedAnswer == trueAnswer;
            QuestionResult questionResult = new QuestionResult(result, selectedAnswer,true,position,trueAnswer);
            if (position < Objects.requireNonNull(quizSharedViewModel.questionResults.getValue()).size()) {
                quizSharedViewModel.updateQuestionResult(position, questionResult);
            } else {
                quizSharedViewModel.addQuestionResult(questionResult);
            }
            Log.i("QuizFragment", "onRadio: " + position);
            Log.i("QuizFragment", "onRadioSize: " + quizSharedViewModel.questionResults.getValue().size());
            Objects.requireNonNull(quizSharedViewModel.answeredQuestions.getValue()).add(position);
        });
        binding.textViewParagraph.setText(question.getCauhoi());
        binding.radioButtonOptionA.setText(question.getA());
        binding.radioButtonOptionB.setText(question.getB());
        binding.radioButtonOptionC.setText(question.getC());
        binding.radioButtonOptionD.setText(question.getD());
        binding.textViewInfo.setText("Part 5 - Question " + (position + 1) + "/" + quizViewModel.quizQuestions.getValue().size());


        if (position == 0) {
            binding.buttonBack.setVisibility(View.INVISIBLE);
        }
        if (position == quizViewModel.quizQuestions.getValue().size() - 1) {
            binding.buttonNext.setText("Finish");
            binding.buttonNext.setOnClickListener(v -> {
                        quizSharedViewModel.calculateScore();
                        quizViewModel.isFragmentVisible.set(false);
                        PracticeResultFragment practiceResultFragment = new PracticeResultFragment();
                        requireActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.result_fragment_container, practiceResultFragment)
                                .commit();
                    }
            );
        }
        view = binding.getRoot();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
