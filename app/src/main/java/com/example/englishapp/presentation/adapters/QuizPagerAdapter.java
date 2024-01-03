package com.example.englishapp.presentation.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.englishapp.data.model.Part1QuizQuestion;
import com.example.englishapp.data.model.Part2QuizQuestion;
import com.example.englishapp.data.model.Part3QuizQuestion;
import com.example.englishapp.data.model.Part4QuizQuestion;
import com.example.englishapp.data.model.Part5QuizQuestion;
import com.example.englishapp.data.model.Part6QuizQuestion;
import com.example.englishapp.data.model.Part7QuizQuestion;
import com.example.englishapp.data.model.QuizQuestion;
import com.example.englishapp.presentation.fragment.QuizPart1Fragment;
import com.example.englishapp.presentation.fragment.QuizPart2Fragment;
import com.example.englishapp.presentation.fragment.QuizPart3Fragment;
import com.example.englishapp.presentation.fragment.QuizPart4Fragment;
import com.example.englishapp.presentation.fragment.QuizPart5Fragment;
import com.example.englishapp.presentation.fragment.QuizPart6Fragment;
import com.example.englishapp.presentation.fragment.QuizPart7Fragment;

import java.util.ArrayList;

public class QuizPagerAdapter extends FragmentStateAdapter {
    private final ArrayList<? extends QuizQuestion> questions;

    public QuizPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, ArrayList<? extends QuizQuestion> questions) {
        super(fragmentManager, lifecycle);
        this.questions = questions;
    }

    @NonNull
    @Override
   public Fragment createFragment(int position) {
    QuizQuestion question = questions.get(position);

        if (question instanceof Part1QuizQuestion) {
            return QuizPart1Fragment.newInstance(question, position);
        } else if (question instanceof Part2QuizQuestion) {
            return QuizPart2Fragment.newInstance(question, position);
        } else if (question instanceof Part3QuizQuestion) {
            return QuizPart3Fragment.newInstance(question, position);
        } else if ( question instanceof Part4QuizQuestion) {
            return QuizPart4Fragment.newInstance(question, position);
        } else if (question instanceof Part5QuizQuestion) {
            return QuizPart5Fragment.newInstance(question, position);
        } else if (question instanceof Part6QuizQuestion) {
            return QuizPart6Fragment.newInstance(question, position);
        } else if (question instanceof Part7QuizQuestion) {
            return QuizPart7Fragment.newInstance(question, position);
        } else {
            return null;
        }
}

    @Override
    public int getItemCount() {
        return questions.size();
    }
}