package com.example.englishapp.presentation.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.englishapp.R;
import com.example.englishapp.data.model.Part5QuizQuestion;
import com.example.englishapp.data.model.QuestionResult;
import com.example.englishapp.databinding.FragmentPart5QuizBinding;
import com.example.englishapp.presentation.viewmodel.QuizSharedViewModel;
import com.example.englishapp.presentation.viewmodel.QuizViewModel;

public class QuizFragment extends Fragment {

    private FragmentPart5QuizBinding binding;
    private Part5QuizQuestion question;
    private int position;
    private int selectedAnswer;
    public QuizSharedViewModel quizSharedViewModel;
    private QuizViewModel quizViewModel;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPart5QuizBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        quizSharedViewModel = new ViewModelProvider(requireActivity()).get(QuizSharedViewModel.class);
        quizViewModel = new ViewModelProvider(requireActivity()).get(QuizViewModel.class);

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
        });

        Bundle args = getArguments();
        if (args != null) {
            question = args.getParcelable("question");
            position = args.getInt("position");
        }

        if(question == null){
            binding.textViewParagraph.setText("Question not found");
        }
        else {
            if(position == 0){
                binding.buttonBack.setVisibility(View.INVISIBLE);
            }
            if(position == 4){
                binding.buttonNext.setText("Finish");
                binding.buttonNext.setOnClickListener(v-> {
                    quizSharedViewModel.calculateScore();
//                   // Open result fragment
//                    PracticeResultFragment practiceResultFragment = new PracticeResultFragment();
//                    requireActivity().getSupportFragmentManager().beginTransaction()
//                            .replace(R.id.result_fragment_container, practiceResultFragment)
//                            .commit();
                }
                );
            }
            binding.textViewParagraph.setText(question.getCauhoi());
            binding.radioButtonOptionA.setText(question.getA());
            binding.radioButtonOptionB.setText(question.getB());
            binding.radioButtonOptionC.setText(question.getC());
            binding.radioButtonOptionD.setText(question.getD());
            binding.textViewInfo.setText("Part 5 - Question " + (position + 1) + "/" + 5);
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

@Override
public void onPause() {
    super.onPause();
    Log.i("QuizFragment", "onPause: " + position);
    int trueAnswer = Integer.parseInt(question.getDapan());
    boolean result = selectedAnswer == trueAnswer;
    QuestionResult questionResult = new QuestionResult(result, selectedAnswer);
    quizSharedViewModel.addQuestionResult(questionResult);
    if(position == 4){
        quizSharedViewModel.calculateScore();
    }
}

    public static QuizFragment newInstance(Part5QuizQuestion question, int position) {
        QuizFragment fragment = new QuizFragment();
        Bundle args = new Bundle();
        args.putParcelable("question", question);
        args.putInt("position", position);
        fragment.setArguments(args);
        return fragment;
    }

}