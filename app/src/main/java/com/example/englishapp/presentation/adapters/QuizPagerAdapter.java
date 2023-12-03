package com.example.englishapp.presentation.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.example.englishapp.presentation.fragment.QuizFragment;

import com.example.englishapp.data.model.Part5QuizQuestion;

import java.util.ArrayList;

public class QuizPagerAdapter extends FragmentStateAdapter {
    private final ArrayList<Part5QuizQuestion> questions;

    public QuizPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, ArrayList<Part5QuizQuestion> questions) {
        super(fragmentManager, lifecycle);
        this.questions = questions;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Part5QuizQuestion question = questions.get(position);
        return QuizFragment.newInstance(question, position);
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }
}